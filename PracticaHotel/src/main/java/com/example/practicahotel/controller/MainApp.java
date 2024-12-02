package com.example.practicahotel.controller;

import com.example.practicahotel.modelo.ClienteModelo;
import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaModelo;
import com.example.practicahotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.practicahotel.modelo.repository.impl.ReservaRepositoryImpl;
import com.example.practicahotel.view.Cliente;
import com.example.practicahotel.view.Reserva;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    ClienteModelo clienteModelo;
    ReservaModelo reservaModelo;

    public MainApp() throws ExcepcionHotel {
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
            clienteModelo = new ClienteModelo();
            reservaModelo = new ReservaModelo();
            clienteModelo.setClienteRepository(clienteRepository);
            reservaModelo.setReservaRepository(reservaRepository);
            ArrayList<Cliente> listaClientes = clienteModelo.obtenerClientes();
            clienteData.addAll(listaClientes);

        }

        public ObservableList<Cliente> getClientData() {
            return clienteData;
        }

        private Stage escenarioPrincipal;
        private BorderPane rootLayout;

        public void start(Stage primaryStage) {
            this.escenarioPrincipal = primaryStage;
            this.escenarioPrincipal.setTitle("Gestión Hotel");
            initRootLayout();
            showClienteOverview();
        }

        public void initRootLayout() {
            try {
                // Load root layout from fxml file.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/com/example/practicahotel/RootLayout.fxml"));
                rootLayout = (BorderPane) loader.load();

                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                escenarioPrincipal.setScene(scene);
                escenarioPrincipal.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Shows the person overview inside the root layout.
        public void showClienteOverview() {
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/com/example/practicahotel/HotelOverview.fxml"));
                AnchorPane personOverview = (AnchorPane) loader.load();

                // Set person overview into the center of root layout.
                rootLayout.setCenter(personOverview);

                // Give the controller access to the main app.
                ClienteOverviewController controller = loader.getController();
                controller.setClienteModelo(clienteModelo);
                controller.setReservaModelo(reservaModelo);
                controller.setMainApp(this);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean showPersonEditDialog(Cliente cliente) {
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/com/example/practicahotel/PersonEditDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Editar persona");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(escenarioPrincipal);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the person into the controller.
                ClientEditDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setCliente(cliente);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();

                return controller.isOkClicked();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }


    public boolean showReservaEditDialog(String id_cliente, Reserva reserva) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/practicahotel/ReservaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(escenarioPrincipal);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ReservarEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReserva(id_cliente, reserva);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}

