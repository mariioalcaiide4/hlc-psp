package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.regimen;
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
    private ToggleButton desayunoRadioButton;

    @FXML
    private ToggleButton mediaPensionRadioButton;

    @FXML
    private ToggleButton pensionCompletaRadioButton;


    private regimen regimenSeleccionado;

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

        desayunoRadioButton.setUserData("DESAYUNO");
        mediaPensionRadioButton.setUserData("MEDIA_PENSION");
        pensionCompletaRadioButton.setUserData("PENSION_COMPLETA");

        regimenSeleccionado = regimen.DESAYUNO;
        desayunoRadioButton.setSelected(true);

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

        String regimen = reserva.getRegimen(); // Ejemplo: "DESAYUNO", "MEDIA_PENSION"
        if ("DESAYUNO".equals(regimen)) {
            desayunoRadioButton.setSelected(true);
        } else if ("MEDIA_PENSION".equals(regimen)) {
            mediaPensionRadioButton.setSelected(true);
        } else if ("PENSION_COMPLETA".equals(regimen)) {
            pensionCompletaRadioButton.setSelected(true);
        }

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

            if (desayunoRadioButton.isSelected()){
                reserva.setRegimen(regimen.DESAYUNO.name());
            } else if (mediaPensionRadioButton.isSelected()){
                reserva.setRegimen(regimen.MEDIA_PENSION.name());
            } else if (pensionCompletaRadioButton.isSelected()){
                reserva.setRegimen(regimen.PENSION_COMPLETO.name());
            }

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

        if (!desayunoRadioButton.isSelected() && !mediaPensionRadioButton.isSelected() && !pensionCompletaRadioButton.isSelected()) {
            errorMessage += "Tipo de alojamiento no válido!\n";
        }

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
        desayunoRadioButton.setSelected(false);
        mediaPensionRadioButton.setSelected(false);
        pensionCompletaRadioButton.setSelected(false);
        fumadorCheckBox.setSelected(false);

    }
}


