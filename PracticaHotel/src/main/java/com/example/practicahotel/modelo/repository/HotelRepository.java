package com.example.practicahotel.modelo.repository;

import com.example.practicahotel.modelo.ExcepcionHotel;

import java.util.ArrayList;

public interface HotelRepository {

    ArrayList<PersonVO> ObtenerListaClientes() throws ExcepcionHotel;

    void añadirCliente(PersonVO var1) throws ExcepcionHotel;

    void borrarCliente(Integer var1) throws ExcepcionHotel;

    void editarCliente(PersonVO var1) throws ExcepcionHotel;

    

    int lastId() throws ExcepcionHotel;
}
