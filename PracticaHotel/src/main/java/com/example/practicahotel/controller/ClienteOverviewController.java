package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ClienteModelo;
import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.view.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClienteOverviewController {
    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, String> firstNameColumn;
    @FXML
    private TableColumn<Cliente, String> lastNameColumn;

    @FXML
    private Label dniLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;


    //Referenciamos al MainApp

    private MainApp mainApp;
    private ClienteModelo clienteModelo;

    public ClienteOverviewController(){}

    private void showClienteDetails(Cliente cliente) {
        if (cliente != null) {
            //Rellenamos los Label con la información del cliente
            dniLabel.setText(cliente.getDni());
            firstNameLabel.setText(cliente.getNombre());
            lastNameLabel.setText(cliente.getApellido());
            streetLabel.setText(cliente.getDireccion());
            localidadLabel.setText(cliente.getLocalidad());
            provinciaLabel.setText(cliente.getProvincia());
        } else {
            //Cliente si es null, borramos texto
            dniLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteCliente() throws ExcepcionHotel {
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            clienteTable.getItems().remove(selectedIndex);
            Cliente cl = clienteTable.getSelectionModel().getSelectedItem();
            clienteModelo.borrarCliente(cl.getDni());
        } else {
            //Sin seleccionar nada
            Alert alert = new Alert(Alert.AlertType.ERROR, "Seleccione una persona");
            alert.show();
        }
    }


    @FXML
    private void handleNewPerson() throws ExcepcionHotel {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
            agendaModelo.anadirPersona(tempPerson);
            showPersonDetails(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() throws ExcepcionPersona {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
                agendaModelo.editarPersona(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes tener seleccionada a una persona para editarla");
            alert.show();
        }
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    public void setAgendaModelo(AgendaModelo agendaModelo) {
        this.agendaModelo = agendaModelo;
    }
}





}
