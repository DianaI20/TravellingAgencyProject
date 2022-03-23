package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.InvalidPeriodException;
import com.utcluj.travellingagencyproject.exceptions.LimitOfPeopleReachedException;
import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserInterfaceController {

    private VacationPackageController vpc;
    private DestinationController dstCtrl;
    private UserController userController;
    private User currentUser;


    @FXML
    private TextField lowPriceField;

    @FXML
    private TextField highPriceField;

    @FXML
    private TextField destName;

    @FXML
    private DatePicker startingDateField;

    @FXML
    private DatePicker endingDateField;

    @FXML
    private TableView<VacationPackage> vpTable;

    @FXML
    private TableColumn<VacationPackage, String> vpName;

    @FXML
    private TableColumn<VacationPackage, Float> vpPrice;

    @FXML
    private TableColumn<VacationPackage, Destination> dst;

    @FXML
    private TableColumn<VacationPackage, Integer> noAvailableSeats;

    @FXML
    private TableColumn<VacationPackage, Date> startingDate;

    @FXML
    private TableColumn<VacationPackage, Date> endingDate;

    @FXML
    private Button signOutBtn;



    public UserInterfaceController() {
        this.vpc = new VacationPackageController();
        this.userController = new UserController();
    }

    @FXML
    public void viewAvaialbleVacPack() {
        vpName.setCellValueFactory(new PropertyValueFactory<VacationPackage, String>("name"));
        vpPrice.setCellValueFactory(new PropertyValueFactory<VacationPackage, Float>("price"));
        noAvailableSeats.setCellValueFactory(new PropertyValueFactory<VacationPackage, Integer>("noAvailableSeats"));
        dst.setCellValueFactory(new PropertyValueFactory<VacationPackage, Destination>("destination"));
        startingDate.setCellValueFactory(new PropertyValueFactory<VacationPackage, Date>("startingDate"));
        endingDate.setCellValueFactory(new PropertyValueFactory<VacationPackage, Date>("endingDate"));
        vpTable.getItems().setAll(this.getObservableList(vpc.getAvailableVacationPackages()));
    }

    @FXML
    public void viewBookedVacPck() {
        vpName.setCellValueFactory(new PropertyValueFactory<VacationPackage, String>("name"));
        vpPrice.setCellValueFactory(new PropertyValueFactory<VacationPackage, Float>("price"));
        noAvailableSeats.setCellValueFactory(new PropertyValueFactory<VacationPackage, Integer>("noAvailableSeats"));
        dst.setCellValueFactory(new PropertyValueFactory<VacationPackage, Destination>("destination"));
        startingDate.setCellValueFactory(new PropertyValueFactory<VacationPackage, Date>("startingDate"));
        endingDate.setCellValueFactory(new PropertyValueFactory<VacationPackage, Date>("endingDate"));
        vpTable.getItems().setAll(this.getObservableList(userController.getUserByUsername(currentUser.getUsername()).getBookedVacationPackages()));
    }

    @FXML
    public void bookVacation() {
        try {
            VacationPackage vp = vpTable.getSelectionModel().getSelectedItem();
            try {
                userController.bookVacation(currentUser, vp);
                vpc.bookVacationPackage(vp);
                vpTable.getItems().setAll(FXCollections.observableArrayList(vpc.getAvailableVacationPackages()));
                createInformationWindow("Vacation was booked successfully!", Alert.AlertType.CONFIRMATION);
            } catch (LimitOfPeopleReachedException e) {
                createInformationWindow("This package is no longer available.", Alert.AlertType.WARNING);
            }
        } catch (NullPointerException np) {
            createInformationWindow("No vacation package selected.", Alert.AlertType.ERROR);
        }
    }

    public void applyFilters() {
        List<VacationPackage> vp = null;
        try {
            vp = vpc.applyFilters(highPriceField.getText(),lowPriceField.getText(),destName.getText(),startingDateField.getValue(), endingDateField.getValue());
            vpTable.getItems().setAll(getObservableList(vp));
        } catch (InvalidPeriodException e) {
            createInformationWindow("Invalid dates.", Alert.AlertType.ERROR);
        } catch (InvalidInputException e) {
            createInformationWindow("Invalid input", Alert.AlertType.ERROR);
        }catch(NumberFormatException  nb){
            createInformationWindow("Invalid input", Alert.AlertType.ERROR);
        }
    }


    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public TableView<VacationPackage> getVpTable() {
        return vpTable;
    }

    public Button getSignOutBtn() {
        return signOutBtn;
    }

    private void createInformationWindow(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Info");
        alert.setHeaderText(message);
        alert.showAndWait();
    }


// getObservableList

    private List<VacationPackage> getObservableList(List<VacationPackage> filteredList) {
        ObservableList<VacationPackage> data = FXCollections.observableArrayList();
        Iterator ite = filteredList.iterator();
        while (ite.hasNext()) {
            data.add((VacationPackage) ite.next());
        }
        return data;
    }



}
