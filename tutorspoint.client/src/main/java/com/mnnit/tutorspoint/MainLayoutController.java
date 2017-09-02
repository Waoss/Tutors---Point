package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.UserType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller of the main layout.
 * <p>The main layout consists of a tabpane that wraps a simple login screen whose control is defined in
 * {@link LoginLayoutController} class.
 * </p>
 */
public final class MainLayoutController implements Initializable {

    public Tab studentLoginTab;
    public Tab teacherLoginTab;

    /**
     * The initialize method which sets the content of the respective tabs.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            studentLoginTab.setContent(getLoginLayout("STUDENT"));
            teacherLoginTab.setContent(getLoginLayout("TEACHER"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method that loads the login layout and also sets the userType property of its controller.
     *
     * @param type
     *         The type of the user because the controller needs to know what type of user has logged in.
     * @return The loaded Parent.
     * @throws IOException
     *         If the resource is not found.
     */
    private AnchorPane getLoginLayout(String type) throws IOException {
        return getLoginLayout(UserType.valueOf(type));
    }

    /**
     * {@inheritDoc}
     *
     * @param type
     * @return
     * @throws IOException
     */
    private AnchorPane getLoginLayout(UserType type) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/LoginLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        LoginLayoutController controller = fxmlLoader.getController();
        controller.setUserType(type);
        return anchorPane;
    }
}
