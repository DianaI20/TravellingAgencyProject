package com.utcluj.travellingagencyproject;

import com.utcluj.travellingagencyproject.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {

        @Override
        public void start(Stage stage) throws IOException {


            FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("login.fxml"));
            Parent login = (Parent) fxmlLoader.load(); // the file that will be displayed at first should be here
            Scene scene1 = new Scene(login);
            stage.setScene(scene1);
            stage.setTitle("Log in");
            stage.show();
            ViewController viewController = fxmlLoader.getController();

            FXMLLoader fxmlLoader1 = new  FXMLLoader(getClass().getResource("adminInterface.fxml"));
            Parent adminInterface = (Parent) fxmlLoader1.load();
            Stage adminStage = new Stage();
            Scene scene = new Scene(adminInterface);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin stage");

            FXMLLoader fxmlLoader2 = new  FXMLLoader(getClass().getResource("userInterface.fxml"));
            Parent userInterface =  fxmlLoader2.load();
            Stage userStage = new Stage();
            Scene userScene = new Scene(userInterface);
            userStage.setScene(userScene);
            userStage.setTitle("User stage");

          viewController.initialize(stage, adminStage, userStage, fxmlLoader2.getController(),fxmlLoader1.getController());

        }
        public static void main(String args[]){
            launch();
        }
    }
