package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

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

    public Video getVideo() {
        return video.get();
    }

    public void setVideo(final Video video) {
        this.video.set(video);
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
}
