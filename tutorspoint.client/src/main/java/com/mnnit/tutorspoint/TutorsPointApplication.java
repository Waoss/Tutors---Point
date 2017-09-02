package com.mnnit.tutorspoint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class TutorsPointApplication extends Application {
    /**
     * The start method which starts the javafx application<br>
     * Simply creates a scene by loading the main layout and displaying it.
     *
     * @param primaryStage
     *         The primary stage is supplied to every javafx application by the javafx runtime.
     * @throws Exception
     *         Throwing of {@link Exception} is a part of the signature of the start method.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/layout/MainLayout.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Portal");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
