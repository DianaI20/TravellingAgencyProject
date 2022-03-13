package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.model.Destination;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class AdminInterfaceController {

    private DestinationController destinationController;
    public ObservableList<Destination> data;
    @FXML
    private TableView<Destination> table;

    @FXML
    private TableColumn<Destination, String> destinationName;

    @FXML
    private TableColumn<Destination, Long> destinationId;

    @FXML
    public void addDestination(){
    }
    private List<Destination> initializeTable() {

        data = FXCollections.observableArrayList();

        List<Destination> allDst = destinationController.viewAllDestinations();
        Iterator ite = allDst.iterator();
        while (ite.hasNext()) {
            Destination obj = (Destination) ite.next();
            data.add(obj);
        }
        return data;
    }

    @FXML
    private void viewDestinations(){
        destinationId.setCellValueFactory(new PropertyValueFactory<Destination, Long>("id"));
        destinationName.setCellValueFactory(new PropertyValueFactory<Destination, String>("name"));
        table.getItems().setAll(this.initializeTable());
    }

    @FXML
    private void deleteDestination(){
    }

    @FXML
    private void addVacatiomPackage(){

    }

    @FXML
    private void deleteVacationPackage(){

    }
    @FXML
    private void editVacationPackage(){

    }
    @FXML
    private void viewVacationPackage(){

    }

    public AdminInterfaceController() {
        this.destinationController = new DestinationController();
    }



}
