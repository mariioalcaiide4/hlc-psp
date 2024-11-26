package com.example.practicahotel.controller;

import com.example.practicahotel.view.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientEditDialogController {

    @FXML
    private TextField dniField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;

    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        dniField.setText(cliente.getDni());
        firstNameField.setText(cliente.getNombre());
        lastNameField.setText(cliente.getApellido());
        streetField.setText(cliente.getDireccion());
        localidadField.setText(cliente.getLocalidad());
        provinciaField.setText(cliente.getProvincia());
    }

    public boolean isOkClicked(){return okClicked;}

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            cliente.setDni(dniField.getText());
            cliente.setNombre(firstNameField.getText());
            cliente.setApellido(lastNameField.getText());
            cliente.setDireccion(streetField.getText());
            cliente.setLocalidad(localidadField.getText());
            cliente.setProvincia(provinciaField.getText());

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

        if (dniField.getText() == null || dniField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
            alert.show();
            return false;
        }
    }




}
