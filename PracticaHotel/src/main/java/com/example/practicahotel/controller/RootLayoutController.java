package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.view.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RootLayoutController {

    MainApp mainApp = new MainApp();

    public RootLayoutController() throws ExcepcionHotel {}

    public void handleShowVistaDoble() {
        mainApp.showVistaDoble();
    }

    public void handleShowVistaDobleIndividual() {
        mainApp.showVistaDobleIndividual();
    }

    public void handleShowVistaSuiteJunior() {
        mainApp.showVistaSuiteIndividual();
    }

    public void handleShowVistaSuite() {
        mainApp.showVistaSuite();
    }



}