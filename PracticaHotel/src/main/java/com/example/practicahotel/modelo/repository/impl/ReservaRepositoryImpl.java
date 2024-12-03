package com.example.practicahotel.modelo.repository.impl;

import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.ReservaVO;
import com.example.practicahotel.modelo.repository.ReservaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
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
            this.sentencia = "SELECT * FROM reservas";
            ResultSet rs = this.statement.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer id_reserva = rs.getInt("id_reserva");
                java.sql.Date sqlFechaLlegada = rs.getDate("fecha_llegada");
                java.sql.Date sqlFechaSalida = rs.getDate("fecha_salida");
                LocalDate fecha_entrada = sqlFechaLlegada != null ? sqlFechaLlegada.toLocalDate() : null;
                LocalDate fecha_salida = sqlFechaSalida != null ? sqlFechaSalida.toLocalDate() : null;

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

    @Override
    public ObservableList<ReservaVO> RelacionClienteReservas(String id_cliente) throws ExcepcionHotel {
        ObservableList<ReservaVO> reservasList = FXCollections.observableArrayList();
        Connection connex = null;
        try {
            connex = this.conexion.conectarBD();
            this.sentencia = "SELECT * FROM reservas WHERE id_cliente = ?";
            PreparedStatement preparedStatement = connex.prepareStatement(this.sentencia);
            preparedStatement.setString(1, id_cliente);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id_reserva = rs.getInt("id_reserva");
                java.sql.Date sqlFechaLlegada = rs.getDate("fecha_llegada");
                java.sql.Date sqlFechaSalida = rs.getDate("fecha_salida");
                LocalDate fecha_entrada = sqlFechaLlegada != null ? sqlFechaLlegada.toLocalDate() : null;
                LocalDate fecha_salida = sqlFechaSalida != null ? sqlFechaSalida.toLocalDate() : null;

                Integer num_habitaciones = rs.getInt("num_habitaciones");
                String tipo_habitacion = rs.getString("tipo_habitacion");
                boolean fumador = rs.getBoolean("fumador");
                String regimen = rs.getString("regimen");

                ReservaVO reserva = new ReservaVO(id_reserva, fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, regimen, id_cliente);
                reservasList.add(reserva);
            }
        } catch (SQLException e) {
            throw new ExcepcionHotel("Error al obtener las reservas del cliente: " + e.getMessage());
        }
        return reservasList;
    }


    public void añadirReserva(ReservaVO var1) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();
            int fumadorValue = var1.isFumador() ? 1 : 0;
            this.sentencia = "INSERT INTO reservas (id_reserva, fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, regimen, id_cliente) VALUES ('"
                    + var1.getId_reserva() + "','"
                    + var1.getFecha_entrada() + "','"
                    + var1.getFecha_salida() + "','"
                    + var1.getNum_habitaciones() + "','"
                    + var1.getTipo_habitacion() + "',"
                    + fumadorValue + ",'" // Inserta 1 o 0 en lugar de 'true' o 'false'
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
            String sql = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, num_habitaciones = ?, tipo_habitacion = ?, fumador = ?, regimen = ?, id_cliente = ? WHERE id_reserva = ?";

            // Usamos PreparedStatement para evitar errores de formato y de seguridad
            PreparedStatement preparedStatement = connex.prepareStatement(sql);

            // Asignar los valores de la reserva al PreparedStatement
            preparedStatement.setDate(1, Date.valueOf(personaVO.getFecha_entrada()));  // Fecha entrada
            preparedStatement.setDate(2, Date.valueOf(personaVO.getFecha_salida()));   // Fecha salida
            preparedStatement.setInt(3, personaVO.getNum_habitaciones());              // Número de habitaciones
            preparedStatement.setString(4, personaVO.getTipo_habitacion());            // Tipo de habitación
            preparedStatement.setInt(5, personaVO.isFumador() ? 1 : 0);                // Fumador (1 o 0)
            preparedStatement.setString(6, personaVO.getRegimen());                    // Régimen
            preparedStatement.setString(7, personaVO.getId_cliente());                 // ID Cliente
            preparedStatement.setInt(8, personaVO.getId_reserva());                    // ID Reserva para la condición WHERE

            // Ejecutar la actualización
            preparedStatement.executeUpdate();

            // Cerrar la conexión y la sentencia
            preparedStatement.close();
            this.conexion.desconectarBD(connex);

        } catch (SQLException e) {
            throw new ExcepcionHotel("Error al editar la reserva: " + e.getMessage());
        }
    }


    public int lastIdReserva() throws ExcepcionHotel {
        int lastIdReserva = 0;

        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();            ResultSet registro = statement.executeQuery("SELECT id_reserva FROM reservas ORDER BY id_cliente DESC LIMIT 1");
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

