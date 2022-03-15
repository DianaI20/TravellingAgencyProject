package com.utcluj.travellingagencyproject.controller;


import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.PasswordTooShortException;
import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class ViewController {

    private UserController userController;

    private Stage adminStage;
    private Stage userStage;
    private Stage loginStage;
    private User currentUser;
    private UserInterfaceController usic;
    private AdminInterfaceController adic;

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public void logIn() {
        try {
            currentUser = userController.login(usernameField.getText(), passwordField.getText());
            usic.setCurrentUser(currentUser);
            switchToUserWindow();
        } catch (NoResultException np) {
            createInformationWindow("Wrong username or password", Alert.AlertType.ERROR);
        }
    }

    public void register() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            userController.register(usernameField.getText(), passwordField.getText());
            createInformationWindow("Register was done succesfully!", Alert.AlertType.CONFIRMATION);
        } catch (PersistenceException e) {
            createInformationWindow("This username already exists!", Alert.AlertType.ERROR);
        } catch (PasswordTooShortException e) {
            createInformationWindow("Password too short!", Alert.AlertType.ERROR);
        } catch (InvalidInputException e) {
            createInformationWindow("Invalid input", Alert.AlertType.ERROR);
        }
    }

    public void switchToAdminWindow() {

        if (loginStage != null && adminStage != null) {
            loginStage.hide();
            adminStage.show();
        } else {
            System.out.println("NULL STAGES :( ");
        }
    }

    public void switchToUserWindow() {
        if (userStage != null && loginStage != null) {
            loginStage.hide();
            userStage.show();
        } else {
            System.out.println("NULL STAGES");
        }
    }

    public void initialize(Stage logInWindow, Stage adminWindow, Stage userWindow, UserInterfaceController usic, AdminInterfaceController adic) {
        this.loginStage = logInWindow;
        this.adminStage = adminWindow;
        this.userStage = userWindow;
        this.usic = usic;
        this.adic = adic;
        signOut();

    }

    private void signOut() {
        usic.getSignOutBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userStage.hide();
                loginStage.show();
                passwordField.clear();
                usernameField.clear();
                usic.getVpTable().getItems().clear();
            }
        });
        adic.getSignOutBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                adminStage.hide();
                loginStage.show();
                passwordField.clear();
                usernameField.clear();
                adic.getTable().getItems().clear();
                adic.getVpTable().getItems().clear();
            }
        });
    }

    private void createInformationWindow(String  message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Info");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public ViewController() {
        this.userController = new UserController();
    }
}
