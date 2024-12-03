package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ClienteModelo;
import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaModelo;
import com.example.practicahotel.view.Cliente;
import com.example.practicahotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    private ListView listaReservas;

    @FXML
    private TextField dniBuscarTextField;


    //Referenciamos al MainApp

    private MainApp mainApp;
    private ClienteModelo clienteModelo;
    private ReservaModelo reservaModelo;

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

            listaReservas.getItems().clear();
            listaReservas.setItems(reservaModelo.setReservas(cliente.getDni()));

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
    private void handleNewReserva() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente == null) {
            // Mostrar una alerta si no hay selección
            Alert alert = new Alert(Alert.AlertType.WARNING, "Debes seleccionar un cliente para añadir una reserva.");
            alert.show();
            return; // Salir del método si no hay cliente seleccionado
        }

        Reserva reservaNueva = new Reserva();
        boolean okClicked = mainApp.showReservaEditDialog(selectedCliente.getDni(), reservaNueva);
        if (okClicked) {
            try {
                reservaModelo.añadirReserva(reservaNueva);
                listaReservas.getItems().add(reservaNueva);
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al añadir la reserva: " + e.getMessage());
                alert.show();
            }
        }
    }


    //Maneja la edición de una reserva seleccionada.

    @FXML
    private void handleEditReserva() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Debes seleccionar un cliente para añadir una reserva.");
            alert.show();
            return;
        }
        Reserva selectedReserva = (Reserva) listaReservas.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = mainApp.showReservaEditDialog(selectedCliente.getDni(), selectedReserva);
            if (okClicked) {
                try {
                    reservaModelo.editarReserva(selectedReserva);
                    listaReservas.refresh();
                } catch (ExcepcionHotel e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error al modificar la reserva: " + e.getMessage());
                    alert.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una reserva para editarla");
            alert.show();
        }
    }

    @FXML
    private Reserva handleDeleteReserva() {
        int selectedIndex = listaReservas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reserva selectedReserva = (Reserva) listaReservas.getItems().get(selectedIndex);
            try {
                reservaModelo.borrarReserva(selectedReserva);
                listaReservas.getItems().remove(selectedIndex);
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al eliminar la reserva: " + e.getMessage());
                alert.show();
            }
            return selectedReserva;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una reserva para borrarla");
            alert.show();
            return null;
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

        clienteTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleClienteSeleccionado();
            }
        });
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        clienteTable.setItems(mainApp.getClientData());
    }

    public void setClienteModelo(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
    }

    public void setReservaModelo(ReservaModelo reservaModelo){
        this.reservaModelo = reservaModelo;
    }

    @FXML
    private void handleBuscarDni() {
        String dniBusqueda = dniBuscarTextField.getText().trim();
        if (dniBusqueda.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, ingrese un DNI para buscar.");
            alert.show();
            return;
        }

        Cliente clienteEncontrado = null;
        for (Cliente cliente : clienteTable.getItems()) {
            if (cliente.getDni().equals(dniBusqueda)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            clienteTable.getSelectionModel().select(clienteEncontrado);
            showClienteDetails(clienteEncontrado);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se encontró un cliente con el DNI proporcionado.");
            alert.show();
        }
    }


    private boolean esDniValido(String dni) {
        // Comprobar que el DNI no está vacío y tiene el formato adecuado
        if (dni == null || dni.length() != 9) {
            return false;
        }
        // El formato del DNI español debe tener 8 números seguidos de una letra
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        String numero = dni.substring(0, 8);
        char letra = dni.charAt(8);

        try {
            Integer.parseInt(numero); // Comprobar que los primeros 8 caracteres son números
            int indice = Integer.parseInt(numero) % 23;
            return letra == letras.charAt(indice); // Comprobar que la letra coincide con el cálculo
        } catch (NumberFormatException e) {
            return false; // Si no es un número válido, devolver false
        }
    }

    @FXML
    private void handleClienteSeleccionado() {
        // Obtener el cliente seleccionado
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();

        if (selectedCliente != null) {
            // Actualizar las reservas en el ListView con las reservas del cliente seleccionado
            actualizarReservasParaCliente(selectedCliente.getDni());
        }
    }

    private void actualizarReservasParaCliente(String dniCliente) {
        try {
            ObservableList<Reserva> reservas = reservaModelo.setReservas(dniCliente); // Devuelve ObservableList
            listaReservas.setItems(reservas); // Lo asignas directamente al ListView
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al obtener las reservas: " + e.getMessage());
            alert.show();
        }
    }





}






