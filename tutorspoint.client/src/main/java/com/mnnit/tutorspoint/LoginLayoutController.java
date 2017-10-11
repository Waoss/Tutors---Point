package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.net.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * The controller of the LoginLayout.
 * <p>The login layout consists of a basic login UI with two text fields and a login button</p>
 */
@FXMLController
public final class LoginLayoutController implements Initializable {

    public static final Logger LOGGER = Logger.getLogger(LoginLayoutController.class.getName());
    public Button youtubeButton;
    /**
     * The login button
     */
    @FXML
    private Button loginButton;
    /**
     * This text field gives us the password as typed by the user
     */
    @FXML
    private PasswordField passwordTextField;
    /**
     * This text field gives us the username as typed by the user
     */
    @FXML
    private TextField usernameTextField;
    /**
     * This property tells us the type of the user who is logging in.<br>It has to be supplied by the parent controller.
     * <br>This can be observed in {@link MainLayoutController#initialize(URL, ResourceBundle)}.
     */
    private SimpleObjectProperty<UserType> userType = new SimpleObjectProperty<>(this, "userType");

    /**
     * The property that contains the user type.
     *
     * @return The property that contains the user type.
     */
    public SimpleObjectProperty<UserType> userTypeProperty() {
        return userType;
    }

    /**
     * This method is called when the {@link #loginButton} is clicked.
     *
     * @param actionEvent
     *         The event object every event handler gets
     */
    @FXML
    public void loginButtonOnAction(ActionEvent actionEvent) {
        User user = new UserBuilder()
                .setUsername(usernameTextField.getText())
                .setPassword(passwordTextField.getText())
                .setUserType(getUserType())
                .createUser();

        try {
            GetUserTask getUserTask = new GetUserTask(user.getUsername());
            Thread getUserThread = new Thread(getUserTask);
            getUserThread.setDaemon(true);
            getUserThread.start();

            User[] users = getUserTask.get();
            List<User> list = Arrays.asList(users);
            if (list.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "You don't have an account. Please make sure" +
                        " you have an account before logging on Tutors Point.").showAndWait();
                return;
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.setProperty("com.mnnit.tutorspoint.client.username", user.getUsername());
        System.setProperty("com.mnnit.tutorspoint.client.usertype", user.getUserType().toString());
        System.setProperty("com.mnnit.tutorspoint.client.password", user.getPassword());
        try {
            Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
            Scene scene = new Scene(loadUserDetailsLayout(user));
            onResizableStage(stage, stage1 -> {
                stage1.setWidth(scene.getRoot().prefWidth(0));
                stage1.setHeight(scene.getRoot().prefHeight(0));
            });
            stage.setMaximized(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("User %s logged in with type %s", user.getUsername(), user.getUserType()));

        try {
            NotificationTask notificationTask = new NotificationTask(
                    System.getProperty("com.mnnit.tutorspoint.client.username"));

            Thread notificationThread = new Thread(notificationTask);
            notificationThread.setDaemon(true);
            notificationThread.start();

            Platform.runLater(() -> {
                Notification[] notifications = notificationTask.getNotifications();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < notifications.length; i++) {
                    Notification notification = notifications[i];
                    if (!notification.isSent()) {
                        stringBuilder.append(notification.getMessage()).append("\n");
                        notification.setSent(true);
                    }
                }
                if (!stringBuilder.toString().isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, stringBuilder.toString()).showAndWait();
                }
            });

            UpdateIsSentTask updateIsSentTask = new UpdateIsSentTask(
                    System.getProperty("com.mnnit.tutorspoint.client.username"));

            Thread updateIsSentThread = new Thread(updateIsSentTask);
            updateIsSentThread.setDaemon(true);
            updateIsSentThread.start();

            Platform.runLater(() -> {
                if (updateIsSentTask.isSent()) {
                    LOGGER.info("Everything went well!");
                } else {
                    LOGGER.info("Something went wrong!");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the user type of the login layout
     *
     * @return the user type of the login layout
     */
    public UserType getUserType() {
        return userType.get();
    }

    /**
     * Sets the user type to some new value
     *
     * @param userType
     *         the new value
     */
    public void setUserType(UserType userType) {
        this.userType.set(userType);
    }

    /**
     * Loads a Parent of the user details layout as defined in the fxml.
     * Also sets the user property of the controller {@link UserDetailsLayoutController#user} so the controller
     * knows what user has logged in and also gets all meta data for the user for later use.
     *
     * @param user
     *         The user
     * @return The layout
     * @throws IOException
     *         If the resources could not be loaded
     */
    private AnchorPane loadUserDetailsLayout(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/UserDetailsLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        UserDetailsLayoutController controller = fxmlLoader.getController();
        controller.setUser(user);
        controller.loadTabContent();
        return anchorPane;
    }

    /**
     * This method is used when we want a stage that is not resizable to work as if it were.
     * If the parameterized stage is not resizable then it is made resizable but after the execution of the
     * consumer, the stage is not set to be resizeable.
     *
     * @param stage
     * @param consumer
     */
    public static void onResizableStage(Stage stage, Consumer<Stage> consumer) {
        if (!stage.isResizable()) {
            stage.setResizable(true);
        }
        consumer.accept(stage);
    }

    /**
     * The initialize method which is called by the FXMLLoader at the time when it loads the FXML document.
     *
     * @param location
     *         The location of the FXML doc
     * @param resources
     *         The resource bundle of the FXML doc
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void hyperlinkOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/SignUpLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        SignUpLayoutController controller = fxmlLoader.getController();
        RadioButton radioButton1 = controller.getStudentRadioButton();
        RadioButton radioButton2 = controller.getTeacherRadioButton();
        Scene scene = new Scene(anchorPane);
        final ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        controller.setToggleGroup(toggleGroup);
        controller.setSignUpStage(stage);
        controller.lateInitialize();
        stage.setScene(scene);
        stage.show();
    }

    public void youtubeOnAction(ActionEvent actionEvent) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText("Enter url of video!");
        textInputDialog.setTitle("Youtube streaming!");
        final String[] url = {"https://www.youtube.com/watch?v=wJnBTPUQS5A"};
        textInputDialog.showAndWait().ifPresent(s -> url[0] = s);

        Group group = new Group();
        Media media = new Media(url[0]);
        MediaPlayer mediaPlayer = new MediaPlayer(media);


        MediaView mediaView = new MediaView(mediaPlayer);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(mediaView);
        mediaPlayer.setAutoPlay(true);
        group.getChildren().add(anchorPane);
        Scene scene = SceneBuilder.create().width(500).height(500).root(group).fill(Color.WHITE).build();

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
