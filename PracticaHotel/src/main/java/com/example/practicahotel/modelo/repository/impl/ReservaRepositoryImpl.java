package com.example.practicahotel.modelo.repository.impl;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.repository.ClienteRepository;

import java.util.ArrayList;

public class ReservaRepositoryImpl implements ClienteRepository {
    @Override
    public ArrayList<PersonVO> ObtenerListaReservas() throws ExcepcionHotel {
        return null;
    }

    @Override
    public void añadirReserva(PersonVO var1) throws ExcepcionHotel {

    }

    @Override
    public void borrarReserva(Integer var1) throws ExcepcionHotel {

    }

    @Override
    public void editarReserva(PersonVO var1) throws ExcepcionHotel {

    }

    @Override
    public int lastIdReserva() throws ExcepcionHotel {
        return 0;
    }
}
