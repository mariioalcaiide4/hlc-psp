package com.example.practicahotel.modelo;

import com.example.practicahotel.modelo.repository.ReservaRepository;
import com.example.practicahotel.modelo.repository.impl.ConexionJDBC;
import com.example.practicahotel.util.ReservaUtil;
import com.example.practicahotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservaModelo {
    private ReservaRepository reservaRepository;
    private ConexionJDBC conexion = new ConexionJDBC();

    // Setter para la dependencia ReservaRepository
    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ObservableList<Reserva> setReservas(String idCliente) throws ExcepcionHotel {
        ObservableList<ReservaVO> listillaReservas = this.reservaRepository.RelacionClienteReservas(idCliente);
        return ReservaUtil.parseReservaVOReserva(listillaReservas);
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
