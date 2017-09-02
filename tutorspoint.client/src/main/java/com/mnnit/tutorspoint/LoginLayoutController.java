package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * The controller of the LoginLayout.
 * <p>The login layout consists of a basic login UI with two text fields and a login button</p>
 */
public final class LoginLayoutController implements Initializable {

    public static final Logger LOGGER = Logger.getLogger(LoginLayoutController.class.getName());
    /** The login button */
    @FXML
    private Button loginButton;
    /** This text field gives us the password as typed by the user */
    @FXML
    private PasswordField passwordTextField;
    /** This text field gives us the username as typed by the user */
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
            Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
            Scene scene = new Scene(loadUserDetailsLayout(user), 500, 500);
            onResizableStage(stage, stage1 -> {
                stage1.setWidth(scene.getWidth());
                stage1.setHeight(scene.getHeight());
            });
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("User %s logged in with type %s", user.getUsername(), user.getUserType()));
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
        stage.setResizable(false);
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
}
