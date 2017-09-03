package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.User;
import com.mnnit.tutorspoint.core.UserType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class UserDetailsLayoutController implements Initializable {
    public static final Logger LOGGER = Logger.getLogger(UserDetailsLayoutController.class.getName());
    @FXML
    public Tab manageAccountTab;
    public Tab watchVideosTab;
    public Tab uploadVideoTab;
    private SimpleObjectProperty<User> user = new SimpleObjectProperty<>(this, "user");

    public final SimpleObjectProperty<User> userProperty() {
        return user;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        user.addListener((observable, oldValue, newValue) -> {
            LOGGER.info("The user has changed to " + newValue.getUsername());
        });
    }

    public void loadTabContent() {
        try {
            manageAccountTab.setContent(getManageAccountLayout());
            uploadVideoTab.setContent(getUploadVideoLayout(getUser().getUserType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AnchorPane getManageAccountLayout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/ManageAccountLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ManageAccountLayoutController controller = fxmlLoader.getController();
        controller.getUsernameValue().setText(getUser().getUsername());
        return anchorPane;
    }

    private AnchorPane getUploadVideoLayout(UserType userType) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/UploadVideoLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        UploadVideoLayoutController controller = fxmlLoader.getController();
        if (userType == UserType.STUDENT) {
            controller.getMessage().setText("Sorry! As a student, you cannot upload videos!\n" +
                    "However you can watch some cool videos under the \n'watch videos tab!'");
        } else {

        }
        return anchorPane;
    }

    public User getUser() {
        return user.get();
    }

    public void setUser(final User user) {
        this.user.set(user);
    }
}
