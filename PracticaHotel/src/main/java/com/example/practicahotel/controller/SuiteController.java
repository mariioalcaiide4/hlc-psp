package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ReservaModelo;
import com.example.practicahotel.modelo.SliderModelo;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class SuiteController {
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
        if (!modelo.getSuite().isEmpty()) {
            slider.setMax(modelo.getSuite().size() - 1);
            slider.valueProperty().addListener((obs, oldVal, newVal) -> {
                int index = newVal.intValue();
                imageView.setImage(modelo.getSuite().get(index));
            });

            // Mostrar la primera imagen por defecto
            imageView.setImage(modelo.getSuite().get(0));
        } else {
            // Manejar el caso de listas vacías
            System.out.println("No hay imágenes disponibles para mostrar.");
        }
    }


    private void actualizarOcupacion() {
        int habitacionesTotales = reservaModelo.getHabitacionesTotales("suite");
        int habitacionesOcupadas = reservaModelo.getHabitacionesOcupadas("suite", LocalDate.now());
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
