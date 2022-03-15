package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.exceptions.*;
import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.model.VacationStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdminInterfaceController {

    private DestinationController destinationController;
    private VacationPackageController vpController;

    @FXML
    private TableView<Destination> table;

    @FXML
    private TableColumn<Destination, String> destinationName;

    @FXML
    private TableColumn<Destination, Long> destinationId;

    @FXML
    private TextField dstIdField;

    // attributes for vacation packages

    @FXML
    private TableView<VacationPackage> vpTable;

    @FXML
    private TableColumn<VacationPackage, String> vpName;

    @FXML
    private TableColumn<VacationPackage, Long> vpId;

    @FXML
    private TableColumn<VacationPackage, Float> vpPrice;

    @FXML
    private TableColumn<VacationPackage, VacationStatus> vpSts;

    @FXML
    private TableColumn<VacationPackage, Destination> dst;

    @FXML
    private TableColumn<VacationPackage, Integer> noAvailableSeats;

    @FXML
    private TableColumn<VacationPackage, Integer> maximumSeats;

    @FXML
    private TableColumn<VacationPackage, Date> startingDate;

    @FXML
    private TableColumn<VacationPackage, Date> endingDate;

    @FXML
    private TextArea details;

    @FXML
    private TextField vpNameField;

    @FXML
    private TextField vpDestField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField noPeopleField;

    @FXML
    private DatePicker startingDateField;

    @FXML
    private DatePicker endingDateField;

    @FXML
    private Button signOutBtn;

    private List<Destination> updateTable() {

        ObservableList<Destination> data = FXCollections.observableArrayList();

        List<Destination> allDst = destinationController.viewAllDestinations();
        Iterator ite = allDst.iterator();
        while (ite.hasNext()) {
            Destination obj = (Destination) ite.next();
            data.add(obj);
        }
        return data;
    }

    private List<VacationPackage> updateVacPackTable() {
        ObservableList<VacationPackage> data = FXCollections.observableArrayList();

        List<VacationPackage> allVP = vpController.getAllVacationPackages();
        Iterator ite = allVP.iterator();
        while (ite.hasNext()) {
            data.add((VacationPackage) ite.next());
        }
        return data;
    }

    @FXML
    private void viewDestinations() {
        destinationId.setCellValueFactory(new PropertyValueFactory<Destination, Long>("id"));
        destinationName.setCellValueFactory(new PropertyValueFactory<Destination, String>("name"));
        table.getItems().setAll(this.updateTable());
    }


    @FXML
    public void addDestination() {
        try {
            destinationController.addDestination(new Destination(dstIdField.getText()));
            table.getItems().setAll(this.updateTable());
        } catch (EmptyFieldException e) {
            createInformationWindow("Fields cannot be empty.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void deleteDestination() {
        try{
           Destination d = table.getSelectionModel().getSelectedItem();
           destinationController.deleteDestination(d.getId());
           table.getItems().setAll(this.updateTable());
           vpTable.getItems().setAll(this.updateVacPackTable());
       }catch(NullPointerException np){
            createInformationWindow("No destination selected.", Alert.AlertType.ERROR);

        }

    }

    @FXML
    private void viewVacationPackages() {
        vpId.setCellValueFactory(new PropertyValueFactory<VacationPackage, Long>("id"));
        vpName.setCellValueFactory(new PropertyValueFactory<VacationPackage, String>("name"));
        vpPrice.setCellValueFactory(new PropertyValueFactory<VacationPackage, Float>("price"));
        vpSts.setCellValueFactory(new PropertyValueFactory<VacationPackage, VacationStatus>("status"));
        noAvailableSeats.setCellValueFactory(new PropertyValueFactory<VacationPackage, Integer>("noAvailableSeats"));
        maximumSeats.setCellValueFactory(new PropertyValueFactory<VacationPackage, Integer>("noMaximumSeats"));
        dst.setCellValueFactory(new PropertyValueFactory<VacationPackage, Destination>("destination"));
        startingDate.setCellValueFactory(new PropertyValueFactory<VacationPackage, Date>("startingDate"));
        endingDate.setCellValueFactory(new PropertyValueFactory<VacationPackage, Date>("endingDate"));

        vpTable.getItems().setAll(this.updateVacPackTable());
    }

    @FXML
    private void addVacationPackage() {
        try {
            Destination dst = destinationController.getDestinationById(Long.parseLong(vpDestField.getText()));
            vpController.insertVacationPackage(vpNameField.getText(),
                    priceField.getText(),
                    dst,
                    noPeopleField.getText(),
                    startingDateField.getValue(),
                    endingDateField.getValue());
            vpTable.getItems().setAll(this.updateVacPackTable());
            createInformationWindow("Vacation was added successfully!", Alert.AlertType.CONFIRMATION);
        } catch (NumberFormatException nb) {
            createInformationWindow("Invalid input", Alert.AlertType.ERROR);
        } catch (InvalidInputException e) {
            createInformationWindow("Invalid input", Alert.AlertType.ERROR);
        } catch (InvalidPeriodException e) {
            createInformationWindow("Invalid period.", Alert.AlertType.ERROR);
        }catch(NoResultException nre){
            createInformationWindow("No destination with this id.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void deleteVacationPackage() {
        try{
            VacationPackage vp = vpTable.getSelectionModel().getSelectedItem();
            vpController.deleteVacationPackage(vp.getId());
            vpTable.getItems().setAll(this.updateVacPackTable());
            createInformationWindow("Vacation was deleted successfully!", Alert.AlertType.CONFIRMATION);
        }catch (NullPointerException np){
            createInformationWindow("No package selected", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editVacationPackage() {
        try {
            VacationPackage vp = vpTable.getSelectionModel().getSelectedItem();
            Destination dst = destinationController.getDestinationById(Long.parseLong(vpDestField.getText()));
            System.out.println("Destination" + vpDestField.getText());
            try {
                vpController.editVacationPackage(vp, vpNameField.getText(),
                        dst,
                        priceField.getText(),
                        noPeopleField.getText(),
                        startingDateField.getValue(),
                        endingDateField.getValue());
                vpTable.getItems().setAll(this.updateVacPackTable());
                createInformationWindow("Vacation was edited successfully!", Alert.AlertType.CONFIRMATION);
            } catch (NumberFormatException nb) {
                nb.printStackTrace();
                createInformationWindow("Invalid input", Alert.AlertType.ERROR);
            } catch (InvalidPeriodException e) {
                e.printStackTrace();
                createInformationWindow("Starting date is after ending date.", Alert.AlertType.ERROR);
            }
        } catch (NullPointerException e) {
            createInformationWindow("No package selected.", Alert.AlertType.ERROR);
        }catch (NoResultException e) {
            e.printStackTrace();
            createInformationWindow("No destination with this id.", Alert.AlertType.ERROR);
        }
    }


    public Button getSignOutBtn() {
        return signOutBtn;
    }

    public TableView<Destination> getTable() {
        return table;
    }

    public TableView<VacationPackage> getVpTable() {
        return vpTable;
    }

    private void createInformationWindow(String  message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Info");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public AdminInterfaceController() {
        this.destinationController = new DestinationController();
        this.vpController = new VacationPackageController();
    }

}
