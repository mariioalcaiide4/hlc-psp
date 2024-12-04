package com.example.practicahotel.modelo.repository;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaVO;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ReservaRepository {

    ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionHotel;

    ObservableList<ReservaVO> RelacionClienteReservas(String id_cliente) throws ExcepcionHotel;

    void añadirReserva(ReservaVO var1) throws ExcepcionHotel;

    void borrarReserva(Integer var1) throws ExcepcionHotel;

    void editarReserva(ReservaVO var1) throws ExcepcionHotel;

    int lastIdReserva() throws ExcepcionHotel;

    int obtenerHabitacionesOcupadas(String tipoHabitacion, LocalDate fechaActual);

    int obtenerTotalHabitaciones(String tipoHabitacion);
}


