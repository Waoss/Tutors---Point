package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.net.InsertUserTask;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SignUpLayoutController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Button signUpButton;
    public RadioButton studentRadioButton;
    public RadioButton teacherRadioButton;
    private ToggleGroup toggleGroup;
    private String type = "STUDENT";
    private Stage signUpStage;

    public void signUpButtonOnAction(ActionEvent actionEvent) {
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
        System.setProperty("com.mnnit.tutorspoint.client.username", user.getUsername());
        System.setProperty("com.mnnit.tutorspoint.client.usertype", user.getUserType().toString());
        System.setProperty("com.mnnit.tutorspoint.client.password", user.getPassword());

        try {
            InsertUserTask insertUserTask = new InsertUserTask(user);
            new Thread(insertUserTask).start();
            while (insertUserTask.isRunning()) {
                wait();
            }
            if (insertUserTask.get()) {
                new Alert(Alert.AlertType.INFORMATION, "User successfully signed up!").showAndWait();
                signUpStage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").showAndWait();
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void lateInitialize() {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
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
