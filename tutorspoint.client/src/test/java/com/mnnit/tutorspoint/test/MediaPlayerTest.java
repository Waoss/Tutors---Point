package com.mnnit.tutorspoint.test;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.media.*;
import javafx.stage.*;
import org.junit.Test;

public class MediaPlayerTest extends Application {

    @Test
    public void test() throws Exception {
        launch(getClass());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Media media = new Media(
                "https://redirector.googlevideo.com/videoplayback?lmt=1500699431565008&requiressl=yes&dur=201.340&ip=93.158.200.189&itag=22&mv=u&source=youtube&ms=au&mime=video/mp4&mt=1504289731&expire=1504311818&mn=sn-5hnedne6&mm=31&pl=26&ratebypass=yes&id=o-AA0_IvA2qM3enIIBzUJ4ZMvKbkeAEoMZr8-srIl-A5ny&sparams=dur,ei,id,ip,ipbits,itag,lmt,mime,mm,mn,ms,mv,pl,ratebypass,requiressl,source,expire&key=yt6&ei=qqWpWbrIIImH1gKmwp0g&ipbits=0&signature=5B6ADC56FE68DE41828F00E75D56496F1BA47314.3AC391011C3986A7E78CC5C15007471C983049A4&title=OneRepublic-Seeb---Rich-Love-Lyric-Video");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        primaryStage.setScene(new Scene(new Group(mediaView)));
        primaryStage.setTitle("Hello Media!");
        primaryStage.show();
        mediaPlayer.play();
    }
}
