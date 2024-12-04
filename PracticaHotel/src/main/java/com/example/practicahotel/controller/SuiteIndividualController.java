package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ReservaModelo;
import com.example.practicahotel.modelo.SliderModelo;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class SuiteIndividualController {
    private MainApp mainApp;
    private ReservaModelo reservaModelo;

    @FXML
    private ImageView imageView;

    @FXML
    private Slider slider;

    private SliderModelo modelo;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    public void initialize() {
        modelo = new SliderModelo();

        // Configurar slider
        slider.setMax(modelo.getSuite_junior().size() - 1);
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            imageView.setImage(modelo.getSuite_junior().get(index));
        });

        // Mostrar la primera imagen por defecto
        if (!modelo.getSuite_junior().isEmpty()) {
            imageView.setImage(modelo.getSuite_junior().get(0));
        }
    }

    private void actualizarOcupacion() {
        int habitacionesTotales = reservaModelo.getHabitacionesTotales("suite_individual");
        int habitacionesOcupadas = reservaModelo.getHabitacionesOcupadas("suite_individual", LocalDate.now());
        double porcentajeOcupacion = (double) habitacionesOcupadas / habitacionesTotales;

        // Establecer el progreso del ProgressIndicator
        progressIndicator.setProgress(porcentajeOcupacion);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setReservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;
        actualizarOcupacion();
    }


}