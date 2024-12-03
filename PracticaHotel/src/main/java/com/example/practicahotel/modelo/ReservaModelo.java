package com.example.practicahotel.modelo;

import com.example.practicahotel.modelo.repository.ReservaRepository;
import com.example.practicahotel.util.ReservaUtil;
import com.example.practicahotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ReservaModelo {
    private ReservaRepository reservaRepository;

    // Setter para la dependencia ReservaRepository
    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // Método para obtener las reservas de un cliente
    public ObservableList<Reserva> setReservas(String id_cl) {
        try {
            // Obtener la lista de reservas en formato VO (Valor Object)
            ObservableList<ReservaVO> listaReservas = this.reservaRepository.RelacionClienteReservas(id_cl);

            // Convertir la lista VO a la lista de vista (Reserva)
            return (ObservableList<Reserva>) ReservaUtil.parseReservaVOReserva((ArrayList<ReservaVO>) listaReservas);
        } catch (ExcepcionHotel e) {
            e.printStackTrace(); // Imprime el error en consola
            return FXCollections.emptyObservableList(); // Devuelve una lista vacía en caso de error
        }
    }


    // Método para añadir una reserva
    public void añadirReserva(Reserva reserva) throws ExcepcionHotel {
        // Convertir la reserva de vista a VO y pasársela al repositorio
        reservaRepository.añadirReserva(ReservaUtil.parseReservaReservaVO(reserva));
    }

    // Método para editar una reserva
    public void editarReserva(Reserva reserva) throws ExcepcionHotel {
        // Convertir la reserva de vista a VO y pasársela al repositorio
        reservaRepository.editarReserva(ReservaUtil.parseReservaReservaVO(reserva));
    }

    // Método para borrar una reserva
    public void borrarReserva(Reserva reserva) throws ExcepcionHotel {
        // Pasar el ID de la reserva al repositorio para borrarla
        reservaRepository.borrarReserva(reserva.getId_Reserva());
    }
}
