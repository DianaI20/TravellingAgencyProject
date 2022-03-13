package com.utcluj.travellingagencyproject;

import com.utcluj.travellingagencyproject.controller.ViewController;
import com.utcluj.travellingagencyproject.model.RegularUser;
import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.repository.UserRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

import static com.utcluj.travellingagencyproject.model.UserType.ADMIN;

public class Main extends Application {

        @Override
        public void start(Stage stage) throws IOException {


        // FXMLLoader fxmlLoader =  FXMLLoader(Main.class.getResource("login2.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 600,400);
            Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
            stage.show();
            //    stage.setScene(scene);
//            ViewController viewController = fxmlLoader.getController();
//            viewController.setStage(stage);

        }
        public static void main(String args[]){
            launch();
        }
    }
