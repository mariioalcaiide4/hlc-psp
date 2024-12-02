package com.example.practicahotel.modelo;

import com.example.practicahotel.modelo.repository.ReservaRepository;
import com.example.practicahotel.util.ClienteUtil;
import java.util.ArrayList;

import com.example.practicahotel.util.ReservaUtil;
import com.example.practicahotel.view.Cliente;
import com.example.practicahotel.view.Reserva;
import javafx.collections.ObservableList;

public class ReservaModelo {
    ReservaRepository reservaRepository;
    public void setReservaRepository(ReservaRepository reservaRepository) {this.reservaRepository = reservaRepository;}

    public ArrayList<Reserva> obtenerReservas() throws ExcepcionHotel{
        ArrayList<ReservaVO> listillaReservas = reservaRepository.ObtenerListaReservas();
        return ReservaUtil.parseReservaVOReserva(listillaReservas);
    }

    public ObservableList<Reserva> RelacionClienteReservas() throws ExcepcionHotel{
        ArrayList<ReservaVO> listillaReservas1 = (ArrayList<ReservaVO>) reservaRepository.RelacionClienteReservas();
        return (ObservableList<Reserva>) ReservaUtil.parseReservaVOReserva(listillaReservas1);
    }

    /*
     public ObservableList<Reserva> setReservas(String dni) {
        try {
            ObservableList<ReservaVO> reservasVO = this.reservaRepository.obtenerListaReservasCliente(dni);
            return ReservaUtil.conversionReserva(reservasVO); // Convierte las reservas a la forma esperada
        } catch (ExcepcionHotel e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexión");
            alerta.setHeaderText("La base de datos no está conectada.");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            return FXCollections.emptyObservableList(); // Devuelve una lista vacía en caso de error
        }
    }
     */


    public void añadirReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.añadirReserva(ReservaUtil.parseReservaReservaVO(reserva));
    }

    public void editarReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.editarReserva(ReservaUtil.parseReservaReservaVO(reserva));

    }

    public void borrarReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.borrarReserva(reserva.getId_Reserva());


    }
}