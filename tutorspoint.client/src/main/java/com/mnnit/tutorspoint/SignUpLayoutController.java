package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.net.InsertUserTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;

public class SignUpLayoutController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Button signUpButton;
    public RadioButton studentRadioButton;
    public RadioButton teacherRadioButton;
    private ToggleGroup toggleGroup;
    private String type = "STUDENT";
    private Stage signUpStage;
    private boolean isTypeSelected = false;

    public void signUpButtonOnAction(ActionEvent actionEvent) {
        if (!isTypeSelected) {
            new Alert(Alert.AlertType.ERROR, "Please select at least one type").showAndWait();
            return;
        }
        UserType userType;
        if (type.equals("STUDENT")) {
            userType = UserType.STUDENT;
        } else {
            userType = UserType.TEACHER;
        }
        User user = new UserBuilder()
                .setUsername(usernameTextField.getText())
                .setPassword(passwordTextField.getText())
                .setUserType(userType)
                .createUser();
        try {
            InsertUserTask insertUserTask = new InsertUserTask(user);
            Thread insertUserThread = new Thread(insertUserTask);
            insertUserThread.setDaemon(true);
            insertUserThread.start();

            Platform.runLater(() -> {
                new Alert(Alert.AlertType.INFORMATION, "User successfully signed up!").showAndWait();
                signUpStage.close();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void lateInitialize() {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            isTypeSelected = true;
            RadioButton radioButton = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
            type = radioButton.getText();
        });
    }

    public RadioButton getStudentRadioButton() {
        return studentRadioButton;
    }

    public void setStudentRadioButton(final RadioButton studentRadioButton) {
        this.studentRadioButton = studentRadioButton;
    }

    public RadioButton getTeacherRadioButton() {
        return teacherRadioButton;
    }

    public void setTeacherRadioButton(final RadioButton teacherRadioButton) {
        this.teacherRadioButton = teacherRadioButton;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(final ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public Stage getSignUpStage() {
        return signUpStage;
    }

    public void setSignUpStage(final Stage signUpStage) {
        this.signUpStage = signUpStage;
    }
}
