package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ReservaModelo;
import com.example.practicahotel.modelo.SliderModelo;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class DobleController {
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
        slider.setMax(modelo.getDoble().size() - 1);
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int index = newVal.intValue();
            imageView.setImage(modelo.getDoble().get(index));
        });

        // Mostrar la primera imagen por defecto
        if (!modelo.getDoble().isEmpty()) {
            imageView.setImage(modelo.getDoble().get(0));
        }
    }

    private void actualizarOcupacion() {
        int habitacionesTotales = reservaModelo.getHabitacionesTotales("doble");
        int habitacionesOcupadas = reservaModelo.getHabitacionesOcupadas("doble", LocalDate.now());
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