package com.utcluj.travellingagencyproject.controller;


import com.utcluj.travellingagencyproject.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.persistence.Table;
import java.io.IOException;

public class ViewController{

    UserController userController;
    Stage stage;
    Scene scene;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public BorderPane borderPane;



    @FXML
    public void logIn() {
        userController.logIn(usernameField.getText(), passwordField.getText());
    }

    @FXML
    public void changeToAdminWindow(){
        stage.setScene(new Scene(borderPane));
    }
    public void setStage(Stage stage){
         this.stage = stage;
    }

    public ViewController() {
        this.userController = new UserController();
    }
}
