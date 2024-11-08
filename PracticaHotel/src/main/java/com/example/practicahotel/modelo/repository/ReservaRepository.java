package com.example.practicahotel.modelo.repository;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaVO;

import java.util.ArrayList;

public interface ReservaRepository {

    ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionHotel;

    void añadirReserva(ReservaVO var1) throws ExcepcionHotel;

    void borrarReserva(String var1) throws ExcepcionHotel;

    void editarReserva(ReservaVO var1) throws ExcepcionHotel;

    int lastIdReserva() throws ExcepcionHotel;
}


