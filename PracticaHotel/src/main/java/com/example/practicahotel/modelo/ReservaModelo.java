package com.example.practicahotel.modelo;

import com.example.practicahotel.modelo.repository.ReservaRepository;
import com.example.practicahotel.util.ClienteUtil;
import java.util.ArrayList;

import com.example.practicahotel.util.ReservaUtil;
import com.example.practicahotel.view.Cliente;
import com.example.practicahotel.view.Reserva;

public class ReservaModelo {
    ReservaRepository reservaRepository;
    public void setReservaRepository(ReservaRepository reservaRepository) {this.reservaRepository = reservaRepository;}

    public ArrayList<Reserva> obtenerReservas() throws ExcepcionHotel{
        ArrayList<ReservaVO> listillaReservas = reservaRepository.ObtenerListaReservas();
        return ReservaUtil.parseReservaVOReserva(listillaReservas);
    }

    public void añadirReserva(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.añadirReserva(ReservaUtil.parseReservaReservaVO(reserva));
    }

    public void editarCliente(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.editarReserva(ReservaUtil.parseReservaReservaVO(reserva));

    }

    public void borrarCliente(Reserva reserva) throws ExcepcionHotel {
        reservaRepository.borrarReserva(reserva.getId_Reserva());


    }
}