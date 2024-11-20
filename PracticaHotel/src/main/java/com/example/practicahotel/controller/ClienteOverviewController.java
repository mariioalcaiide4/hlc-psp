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
    private void handleDeleteClient() throws ExcepcionHotel {
        // Obtener el índice seleccionado
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            // Obtener el cliente seleccionado
            Cliente clienteSeleccionado = clienteTable.getSelectionModel().getSelectedItem();

            try {
                // Eliminar de la base de datos
                clienteModelo.borrarCliente(clienteSeleccionado);

                // Eliminar del observable list
                clienteTable.getItems().remove(clienteSeleccionado);

                // Mensaje opcional de confirmación
                System.out.println("Cliente eliminado correctamente.");
            } catch (Exception e) {
                // Manejo de excepciones en caso de error
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo eliminar el cliente.");
                alert.show();
                e.printStackTrace();
            }
        } else {
            // Mostrar alerta si no hay selección
            Alert alert = new Alert(Alert.AlertType.ERROR, "Seleccione un cliente para eliminar.");
            alert.show();
        }
    }



    @FXML
    private void handleNewClient() throws ExcepcionHotel {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showPersonEditDialog(tempCliente);
        if (okClicked) {
            mainApp.getClientData().add(tempCliente);
            clienteModelo.anadirCliente(tempCliente);
            showClienteDetails(tempCliente);
        }
    }

    @FXML
    private void handleEditClient() throws ExcepcionHotel {
        Cliente selectedPerson = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showClienteDetails(selectedPerson);
                clienteModelo.editarCliente(selectedPerson);
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
        showClienteDetails(null);

        // Listen for selection changes and show the person details when changed.
        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        clienteTable.setItems(mainApp.getClientData());
    }

    public void setClienteModelo(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
    }
}






