package com.mnnit.tutorspoint;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageAccountLayoutController implements Initializable {
    public Label username;
    public Label usernameValue;
    public Button changePassword;

    public Label getUsername() {
        return username;
    }

    public void setUsername(final Label username) {
        this.username = username;
    }

    public Label getUsernameValue() {
        return usernameValue;
    }

    public void setUsernameValue(final Label usernameValue) {
        this.usernameValue = usernameValue;
    }

    public Button getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(final Button changePassword) {
        this.changePassword = changePassword;
    }

    public void changePasswordButtonOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }
}
