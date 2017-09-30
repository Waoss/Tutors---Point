package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.todo.Todo;
import com.mnnit.tutorspoint.core.video.Tag;
import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.*;
import com.mnnit.tutorspoint.util.ExceptionDialog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@FXMLController
public class VideoLayoutController implements Initializable {
    public static final Logger LOGGER = Logger.getLogger(VideoLayoutController.class.getName());
    private static final double MIN_CHANGE = 0.5;
    public MediaView mediaView;
    public Label videoNameLabel;
    public Label likesLabel;
    public Button playButton;
    public Button pauseButton;
    public Button stopButton;
    public Label timeLabel;
    public Slider slider;
    public Button toWatch;
    public Button addTag;
    public Button deleteVideo;
    /**
     * Represents the url of the server from where the video can be retrieved.
     * For example, "http://localhost:8000/",so that + 33(assumed video ID) would give "http://localhost:8000/33.vid".
     * That video is then streamed if the video is being played
     */
    private SimpleStringProperty url = new SimpleStringProperty(this, "url");
    private SimpleObjectProperty<Video> video = new SimpleObjectProperty<>(this, "video");
    private boolean hasInitializedMediaPlayerToBindWithSlider = false;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        url.addListener(
                (observable, oldValue, newValue) -> mediaView.setMediaPlayer(new MediaPlayer(new Media(newValue))));


        video.addListener((observable, oldValue, newValue) -> {
            final StringBuffer url = new StringBuffer(getUrl());
            url.append(newValue.getVideoId());
            url.append(".vid");
            setUrl(url.toString());
            videoNameLabel.setText(newValue.getName());
            likesLabel.setText(String.valueOf(newValue.getLikes().size()));
        });


    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(final String url) {
        this.url.set(url);
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public SimpleObjectProperty<Video> videoProperty() {
        return video;
    }

    public void playButtonOnAction(ActionEvent actionEvent) {
        initializeMediaPlayer();
        mediaView.getMediaPlayer().play();
    }

    private void initializeMediaPlayer() {
        if (!hasInitializedMediaPlayerToBindWithSlider) {
            slider.valueChangingProperty().addListener((observableValue, wasChanging, isChanging) -> {
                if (!isChanging) {
                    mediaView.getMediaPlayer().seek(Duration.seconds(slider.getValue()));
                }
            });

            slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
                if (!slider.isValueChanging()) {
                    double currentTime = mediaView.getMediaPlayer().getCurrentTime().toSeconds();
                    if (Math.abs(currentTime - newValue.doubleValue()) > MIN_CHANGE) {
                        mediaView.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
                    }
                }
            });
            mediaView.getMediaPlayer().totalDurationProperty()
                    .addListener((observableValue, oldDuration, newDuration) -> {
                        slider.setMax(newDuration.toSeconds());
                        slider.setPrefWidth(newDuration.toSeconds());
                    });
            mediaView.getMediaPlayer().currentTimeProperty().addListener(((observable, oldValue, newValue) -> {
                Duration currentTime = mediaView.getMediaPlayer().getCurrentTime();
                if (!slider.isValueChanging()) {
                    slider.setValue(newValue.toSeconds());
                }
                timeLabel.setText(
                        format(currentTime.toMillis()) + " / " +
                                format(mediaView.getMediaPlayer().getMedia().getDuration().toMillis()));
            }));
            hasInitializedMediaPlayerToBindWithSlider = true;
        }
    }

    private String format(final double milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        return hours + " : " + minutes + " : " + seconds;
    }

    public void pauseButtonOnAction(ActionEvent actionEvent) {
        initializeMediaPlayer();
        mediaView.getMediaPlayer().pause();
    }

    public void stopButtonOnAction(ActionEvent actionEvent) {
        initializeMediaPlayer();
        mediaView.getMediaPlayer().stop();
    }

    public void toWatchOnAction(ActionEvent actionEvent)
            throws InterruptedException {
        AddToWatchTask addToWatchTask = null;
        try {
            addToWatchTask = new AddToWatchTask(new Todo(System.getProperty("com.mnnit.tutorspoint.client.username"),
                    "Watch-Video-" + video.get().getVideoId()));
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "The URL was malformed: this may be due to wrong server", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Some I/O error occurred", e);
        }
        LOGGER.info("Trying to send request to server for todo");
        LOGGER.info("Sending http request @ URL = \n" + addToWatchTask.getUrl().toString());
        new Thread(addToWatchTask).start();
        while (addToWatchTask.isRunning()) {
            try {
                LOGGER.info("Waiting for request to be sent");
                wait();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "This thread was interrupted", e);
            }
        }
        try {
            if (addToWatchTask.get()) {
                LOGGER.info("The request could be sent and the todo was added.");
                new Alert(Alert.AlertType.INFORMATION, "Your todo was added successfully").showAndWait();
            } else {
                LOGGER.log(Level.SEVERE, "Could not connect to server or the connection was malformed");
                new Alert(Alert.AlertType.ERROR, "Could not connect to server or the connection was malformed")
                        .showAndWait();
            }
        } catch (ExecutionException e) {
            LOGGER.log(Level.SEVERE, "Some error occurred during the execution of the request-sending task", e);
        }
    }

    public void addTagOnAction(ActionEvent actionEvent) throws Throwable {
        final TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Input Tags");
        textInputDialog.setHeaderText(null);
        textInputDialog.setContentText("Enter a tag for the video");
        final Tag tag = new Tag();
        textInputDialog.showAndWait().ifPresent(tag::setName);
        if (tag.getName() == null) {
            new Alert(Alert.AlertType.ERROR, "Please enter a suitable name for the tag!").showAndWait();
            textInputDialog.showAndWait().ifPresent(tag::setName);
        }

        tag.setVideoId(video.get().getVideoId());
        AddTagTask addTagTask = new AddTagTask(tag);
        LOGGER.info("Sending request @ URL = \n" + addTagTask.getUrl());
        new Thread(addTagTask).start();
        while (addTagTask.isRunning()) {
            wait();
            LOGGER.info("Waiting for the AddTagTask to complete execution");
        }

        if (addTagTask.get()) {
            LOGGER.info("Tags were inserted for the video");
            new Alert(Alert.AlertType.INFORMATION, "Tags inserted").showAndWait();
        } else {
            LOGGER.log(Level.SEVERE, "Could not connect to server or some error occurred");
            new Alert(Alert.AlertType.ERROR, "Some Error occurred!!").showAndWait();
        }

    }

    public void deleteVideoOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this video?").showAndWait()
                .ifPresent(buttonType -> {
                    if (buttonType == ButtonType.OK) {
                        new Alert(Alert.AlertType.INFORMATION, "Deleting " + getVideo().getName()).showAndWait();
                        try {
                            final DeleteVideoTask deleteVideoTask = new DeleteVideoTask(getVideo().getVideoId());
                            new Thread(deleteVideoTask).start();
                            while (deleteVideoTask.isRunning()) {
                                LOGGER.info("Waiting for deletion.");
                                LOGGER.warning(
                                        "Execution reaching here means that it is taking too much time for the other thread to send request to server." +
                                                "This might be due to a bad internet connection");
                            }
                            if (deleteVideoTask.get()) {
                                new Alert(Alert.AlertType.INFORMATION, "Video deleted successfully").showAndWait();
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Internal server error").showAndWait();
                            }
                        } catch (IOException | InterruptedException | ExecutionException e) {
                            LOGGER.log(Level.SEVERE, "Some error occured", e);
                            new ExceptionDialog(e).showAndWait();
                        }
                    }
                });

    }

    public Video getVideo() {
        return video.get();
    }

    public void setVideo(final Video video) {
        this.video.set(video);
    }
}
