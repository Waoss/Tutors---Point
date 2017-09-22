package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VideoLayoutController implements Initializable {

    public MediaView mediaView;
    public Label videoNameLabel;
    public Label likesLabel;
    public Button playButton;
    public Button pauseButton;
    public Button stopButton;
    public Slider slider;
    /**
     * Represents the url of the server from where the video can be retrieved.
     * For example, "http://localhost:8000/",so that + 33(assumed video ID) would give "http://localhost:8000/33.vid".
     * That video is then streamed if the video is being played
     */
    private SimpleStringProperty url = new SimpleStringProperty(this, "url");
    private SimpleObjectProperty<Video> video = new SimpleObjectProperty<>(this, "video");

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
        slider.valueProperty().addListener((observable, oldValue, newValue) -> mediaView.getMediaPlayer()
                .seek(mediaView.getMediaPlayer().getMedia().getDuration().multiply(newValue.doubleValue() / 100)));
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
        mediaView.getMediaPlayer().play();
    }

    public void pauseButtonOnAction(ActionEvent actionEvent) {
        mediaView.getMediaPlayer().pause();
    }

    public void stopButtonOnAction(ActionEvent actionEvent) {
        mediaView.getMediaPlayer().stop();
    }
}
