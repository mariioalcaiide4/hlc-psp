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
        clienteRepository.añadirCliente(ClienteUtil.parsePersonPersonVO(cliente));
    }

    public void editarCliente(Cliente cliente) throws ExcepcionHotel {
        clienteRepository.editarCliente(ClienteUtil.parsePersonPersonVO(cliente));

    }

    public void borrarCliente(Cliente cliente) throws ExcepcionHotel {
        reservaRepository.borrarReserva();


    }

}