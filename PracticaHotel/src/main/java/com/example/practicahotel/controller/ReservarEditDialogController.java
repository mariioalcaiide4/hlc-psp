package com.example.practicahotel.controller;

import com.example.practicahotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReservarEditDialogController {

    @FXML
    private TextField idClienteField;

    @FXML
    private TextField idReservaField;

    @FXML
    private DatePicker fechaLlegadaPicker;

    @FXML
    private DatePicker fechaSalidaPicker;

    @FXML
    private ComboBox<Integer> numeroHabitacionesComboBox;

    @FXML
    private ComboBox<String> tipoHabitacionComboBox;

    @FXML
    private CheckBox fumadorCheckBox;

    @FXML
    private ComboBox<String> regimenComboBox;

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;


    @FXML
    private void initialize() {

        ObservableList<Integer> numeroHabitacionesOptions = FXCollections.observableArrayList(0, 1);
        numeroHabitacionesComboBox.setItems(numeroHabitacionesOptions);

        // Inicializa el ComboBox con las opciones de tipo de habitación.
        ObservableList<String> tipoHabitacionOptions = FXCollections.observableArrayList(
                "DOBLE_USO_INDIVIDUAL", "DOBLE", "JUNIOR_SUITE", "SUITE"
        );
        tipoHabitacionComboBox.setItems(tipoHabitacionOptions);

        // Inicializa el ComboBox con las opciones de tipo de alojamiento.
        //ObservableList<String> tipoAlojamientoOptions = FXCollections.observableArrayList(
         //       "DESAYUNO", "MEDIA_PENSION", "PENSION_COMPLETA"
        //);
       // regimenComboBox.setItems(tipoAlojamientoOptions);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setReserva(String dni_cliente, Reserva reserva) {
        this.reserva = reserva;

        idClienteField.setText(dni_cliente);
        idReservaField.setText(String.valueOf(reserva.getId_Reserva()));
        fechaLlegadaPicker.setValue(reserva.getFecha_Entrada());
        fechaSalidaPicker.setValue(reserva.getFecha_Salida());
        numeroHabitacionesComboBox.setValue(reserva.getNumHabitaciones());
        tipoHabitacionComboBox.setValue(reserva.getTipoHabitacion());
        fumadorCheckBox.setSelected(reserva.isFumador());
      //  regimenComboBox.setValue(reserva.getRegimen());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            reserva.setId_Cliente(idClienteField.getText());
            reserva.setId_Reserva(Integer.parseInt(idReservaField.getText()));
            reserva.setFecha_Entrada(fechaLlegadaPicker.getValue());
            reserva.setFecha_Salida(fechaSalidaPicker.getValue());
            reserva.setNum_Habitaciones(numeroHabitacionesComboBox.getValue());
            reserva.setTipoHabitacion(tipoHabitacionComboBox.getValue());
            reserva.setFumador(fumadorCheckBox.isSelected());
        //    reserva.setRegimen(regimenComboBox.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (idClienteField.getText() == null || idClienteField.getText().isEmpty()) {
            errorMessage += "DNI no válido!\n";
        }
        if (idReservaField.getText() == null || idReservaField.getText().isEmpty()) {
            errorMessage += "ID no válido!\n";
        }
        if (fechaLlegadaPicker.getValue() == null) {
            errorMessage += "Fecha de llegada no válida!\n";
        }
        if (fechaSalidaPicker.getValue() == null) {
            errorMessage += "Fecha de salida no válida!\n";
        }
        if (numeroHabitacionesComboBox.getValue() == null || numeroHabitacionesComboBox.getValue() <= 0) {
            errorMessage += "Número de habitaciones no válido!\n";
        }
        if (tipoHabitacionComboBox.getValue() == null || tipoHabitacionComboBox.getValue().isEmpty()) {
            errorMessage += "Tipo de habitación no válido!\n";
        }
        //if (regimenComboBox.getValue() == null || regimenComboBox.getValue().isEmpty()) {
            errorMessage += "Tipo de alojamiento no válido!\n";
       // }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Mostrar el mensaje de error en un cuadro de alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void handleClean(){

        idClienteField.setText("");
        idReservaField.setText("");
        fechaLlegadaPicker.setValue(null);
        fechaSalidaPicker.setValue(null);
        numeroHabitacionesComboBox.setValue(null);
        tipoHabitacionComboBox.setValue(null);
       // regimenComboBox.setValue(null);
        fumadorCheckBox.setSelected(false);

    }
}


