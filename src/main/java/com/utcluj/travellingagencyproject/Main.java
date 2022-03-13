package com.utcluj.travellingagencyproject;

import com.utcluj.travellingagencyproject.controller.DestinationController;
import com.utcluj.travellingagencyproject.controller.ViewController;
import com.utcluj.travellingagencyproject.model.Destination;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {

        @Override
        public void start(Stage stage) throws IOException {

          FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("login2.fxml"));
          Parent login = (Parent) fxmlLoader.load(); // the file that will be displayed at first should be here
          Scene scene1 = new Scene(login);
          stage.setScene(scene1);
          stage.show();
          ViewController viewController = fxmlLoader.getController();

          Parent adminInterface =  FXMLLoader.load(getClass().getResource("adminInterface.fxml"));
          Stage adminStage = new Stage();
          Scene scene = new Scene(adminInterface);
          adminStage.setScene(scene);

          Parent userInterface =  FXMLLoader.load(getClass().getResource("adminInterface.fxml"));
          Stage userStage = new Stage();
          Scene userScene = new Scene(userInterface);
          userStage.setScene(userScene);

            if (viewController != null){
              viewController.setWindows(stage, adminStage, userStage);
          }else{
              System.out.println("Something went wrong :(");
          }

        }
        public static void main(String args[]){
            launch();
        }
    }
