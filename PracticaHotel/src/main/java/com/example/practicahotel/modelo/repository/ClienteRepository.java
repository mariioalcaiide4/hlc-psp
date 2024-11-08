package com.example.practicahotel.modelo.repository;

import com.example.practicahotel.modelo.ClienteVO;
import com.example.practicahotel.modelo.ExcepcionHotel;

import java.util.ArrayList;

public interface ClienteRepository {

    ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel;

    void añadirCliente(ClienteVO var1) throws ExcepcionHotel;

    void borrarCliente(String var1) throws ExcepcionHotel;

    void editarCliente(ClienteVO var1) throws ExcepcionHotel;

    int lastIdCliente() throws ExcepcionHotel;
}