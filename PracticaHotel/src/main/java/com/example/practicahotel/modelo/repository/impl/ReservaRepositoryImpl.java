package com.example.practicahotel.modelo.repository.impl;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaVO;
import com.example.practicahotel.modelo.repository.ClienteRepository;
import com.example.practicahotel.modelo.repository.ReservaRepository;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {
    private ConexionJDBC conexion = new ConexionJDBC();
    private Statement statement;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public ReservaRepositoryImpl(ConexionJDBC conexion) {
        this.conexion = conexion;
    }

    public ReservaRepositoryImpl() {}




    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.reservas = new ArrayList<>();
            this.statement = connex.createStatement();
            this.sentencia = "SELECT * FROM `reservas`";
            ResultSet rs = this.statement.executeQuery(this.sentencia);












        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void añadirReserva(ReservaVO var1) throws ExcepcionHotel {

    }

    public void borrarReserva(Integer var1) throws ExcepcionHotel {

    }

    public void editarReserva(ReservaVO var1) throws ExcepcionHotel {

    }

    public int lastIdReserva() throws ExcepcionHotel {
        return 0;
    }
}
