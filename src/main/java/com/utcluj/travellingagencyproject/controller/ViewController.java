package com.utcluj.travellingagencyproject.controller;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ViewController{

    UserController userController;
    private Stage adminStage;
    private Stage userStage;
    private Stage loginStage;

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public void logIn() {
        userController.logIn(usernameField.getText(), passwordField.getText());
    }

    public void switchToAdminWindow (){
        if (loginStage != null && adminStage != null){
            loginStage.hide();
            adminStage.show();
        }else
        {
            System.out.println("NULL STAGES :( ");
        }
    }

    public void switchToUserWindow (){
        if (userStage != null && loginStage != null){
            loginStage.hide();
            userStage.show();
        }else
        {
            System.out.println("NULL STAGES :( ");
        }
    }

    public void setWindows(Stage logInWindow, Stage adminWindow, Stage userWindow){
        this.loginStage = logInWindow;
        this.adminStage = adminWindow;
        this.userStage = userWindow;
    }

    public ViewController() {
        this.userController = new UserController();

    }
}
