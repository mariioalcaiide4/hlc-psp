package com.example.practicahotel.modelo.repository.impl;

import com.example.practicahotel.modelo.ClienteVO;
import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaVO;
import com.example.practicahotel.modelo.repository.ClienteRepository;
import com.example.practicahotel.modelo.repository.ReservaRepository;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
            this.sentencia = "SELECT * FROM reservas";
            ResultSet rs = this.statement.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer id_reserva = rs.getInt("id_reserva");
                LocalDate fecha_entrada = rs.getDate("fecha_entrada").toLocalDate();
                LocalDate fecha_salida = rs.getDate("fecha_salida").toLocalDate();
                Integer num_habitaciones = rs.getInt("num_habitaciones");
                String tipo_habitacion = rs.getString("tipo_habitacion");
                boolean fumador = rs.getBoolean("fumador");
                String regimen = rs.getString("regimen");
                String id_cliente = rs.getString("id_cliente");

                this.reserva = new ReservaVO(id_reserva, fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, regimen, id_cliente);
                this.reserva.setId_reserva(id_reserva);
                this.reservas.add(this.reserva);
            }

            this.conexion.desconectarBD(connex);
            return this.reservas;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void añadirReserva(ReservaVO var1) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();
            this.sentencia = "INSERT INTO reservas (id_reserva, fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, regimen, id_cliente) VALUES ('"
                    + var1.getId_reserva() + "','"
                    + var1.getFecha_entrada() + "','"
                    + var1.getFecha_salida() + "','"
                    + var1.getNum_habitaciones() + "','"
                    + var1.getTipo_habitacion() + "','"
                    + var1.isFumador() + "','"
                    + var1.getRegimen() + "','"
                    + var1.getId_cliente() + "')";
            this.statement.executeUpdate(this.sentencia);
            this.statement.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException e) {
            System.out.println("No se ha podido agregar la reserva: " + e);
        }
    }

    public void borrarReserva(Integer idReserva) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();
            String sql = String.format("DELETE FROM reservas WHERE id_reserva = '%d'", idReserva);
            this.statement.executeUpdate(sql);
            this.statement.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarReserva(ReservaVO personaVO) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();
            String sql = String.format("UPDATE reservas SET fecha_entrada = '%s', fecha_salida = '%s', num_habitaciones = '%s', tipo_habitacion = '%s', fumador = '%s',  regimen = '%s',  id_cliente = '%s' WHERE id_reserva = '%s'",
                    personaVO.getFecha_entrada(),
                    personaVO.getFecha_salida(),
                    personaVO.getNum_habitaciones(),
                    personaVO.getTipo_habitacion(),
                    personaVO.isFumador(),
                    personaVO.getRegimen(),
                    personaVO.getId_cliente());
            this.statement.executeUpdate(sql);
            this.statement.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int lastIdReserva() throws ExcepcionHotel {
        int lastIdReserva = 0;

        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();            ResultSet registro = statement.executeQuery("SELECT id_reserva FROM reservas ORDER BY id_reserva DESC LIMIT 1");
            if (registro.next()) {
                lastIdReserva = registro.getInt("");
            }
            registro.close();
            this.statement.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la búsqueda del ID");
        }
        return lastIdReserva;
    }
}

