package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.view.Cliente;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class RootLayoutController {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private MenuItem menuItemGestion;

    @FXML
    private BarChart<String, Number> graficoOcupacion;

    @FXML
    private CategoryAxis ejeX;  // Para los meses
    @FXML
    private NumberAxis ejeY;

    MainApp mainApp = new MainApp();

    public RootLayoutController() throws ExcepcionHotel {}


    // Método que se ejecuta al hacer clic en el MenuItem "Ver Ocupación por Meses"
    @FXML
    public void handleMenuItemGestion(ActionEvent event) {
        // Crear una nueva ventana (Stage) para el gráfico
        Stage stage = new Stage();
        stage.setTitle("Gráfico de Ocupación por Meses");

        // Crear el gráfico
        CategoryAxis ejeX = new CategoryAxis();
        ejeX.setLabel("Mes");
        NumberAxis ejeY = new NumberAxis();
        ejeY.setLabel("Porcentaje de Ocupación");

        BarChart<String, Number> graficoOcupacion = new BarChart<>(ejeX, ejeY);
        graficoOcupacion.setTitle("Ocupación por Mes");

        // Ejemplo de datos de ocupación por mes (deberías obtener estos valores de tu base de datos o lógica de negocio)
        List<Double> ocupacionesEnero = Arrays.asList(80.0, 75.0, 85.0); // Valores de ocupación por tipo de habitación
        List<Double> ocupacionesFebrero = Arrays.asList(60.0, 65.0, 70.0);
        List<Double> ocupacionesMarzo = Arrays.asList(90.0, 95.0, 85.0);
        List<Double> ocupacionesAbril = Arrays.asList(70.0, 80.0, 75.0);

        // Crear una lista de ocupación mensual calculada
        GestionOcupacionModelo gestion = new GestionOcupacionModelo();
        List<OcupacionMensual> ocupacionMensual = Arrays.asList(
                new OcupacionMensual("Enero", gestion.calcularOcupacionMensual(ocupacionesEnero)),
                new OcupacionMensual("Febrero", gestion.calcularOcupacionMensual(ocupacionesFebrero)),
                new OcupacionMensual("Marzo", gestion.calcularOcupacionMensual(ocupacionesMarzo)),
                new OcupacionMensual("Abril", gestion.calcularOcupacionMensual(ocupacionesAbril))
        );

        // Crear la serie de datos para el gráfico
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Ocupación Mensual");

        // Llenar la serie con los datos calculados
        for (OcupacionMensual ocupacion : ocupacionMensual) {
            serie.getData().add(new XYChart.Data<>(ocupacion.getMes(), ocupacion.getPorcentajeOcupacion()));
        }

        // Agregar la serie al gráfico
        graficoOcupacion.getData().add(serie);

        // Colocar el gráfico dentro de un contenedor (StackPane o cualquier otro contenedor adecuado)
        StackPane root = new StackPane();
        root.getChildren().add(graficoOcupacion);

        // Crear la escena y añadirla al Stage (ventana)
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        // Mostrar la ventana con el gráfico
        stage.show();
    }

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