package com.example.practicahotel.modelo.repository.impl;

import com.example.practicahotel.modelo.ClienteVO;
import com.example.practicahotel.modelo.ExcepcionHotel;
import com.example.practicahotel.modelo.repository.ClienteRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {
    private ConexionJDBC conexion;
    private Statement statement;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    @Override
    public ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel {
        try{
            Connection connex = this.conexion.conectarBD();
            this.clientes = new ArrayList<>();
            this.sentencia = "SELECT * FROM clientes";
            ResultSet rs = this.statement.executeQuery(this.sentencia);

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia  = rs.getString("provincia");

                this.cliente = new ClienteVO(dni, nombre, apellido, direccion, localidad, provincia);
                this.cliente.setDni(dni);
                this.clientes.add(this.cliente);
            }

            this.conexion.desconectarBD(connex);
            return this.clientes;
        } catch (SQLException e) {
            System.out.println("No se ha podido realizar la operación: " + e);
        }
        return null; // Asegúrate de retornar algo si ocurre un error.
    }

    @Override
    public void añadirCliente(ClienteVO var1) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.sentencia = "INSERT INTO clientes (dni, nombre, apellido, direccion, localidad, provincia) VALUES ('"
                    + var1.getDni() + "','"
                    + var1.getNombre() + "','"
                    + var1.getApellido() + "','"
                    + var1.getDireccion() + "','"
                    + var1.getLocalidad() + "','"
                    + var1.getProvincia() + "')";
            this.statement.executeUpdate(this.sentencia);
            this.statement.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException e) {
            System.out.println("No se ha podido agregar el cliente: " + e);
        }
    }


    public void borrarCliente(String dni) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();
            Statement statement1 = connex.createStatement();
            String sql = String.format("DELETE FROM clientes WHERE dni = %d", dni);
            statement1.executeUpdate(sql);
            this.conexion.desconectarBD(connex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editarCliente(ClienteVO personaVO) throws ExcepcionHotel {
        try {
            Connection connex = this.conexion.conectarBD();
            this.statement = connex.createStatement();
            String sql = String.format("UPDATE clientes SET nombre = '%s', apellido = '%s', direccion = '%s', localidad = '%s', provincia = '%s' WHERE dni = %d", personaVO.getNombre(), personaVO.getApellido(), personaVO.getDireccion(), personaVO.getLocalidad(), personaVO.getProvincia(), personaVO.getDni());
            this.statement.executeUpdate(sql);
            this.statement.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int lastIdCliente() throws ExcepcionHotel {
        int lastIdCliente = 0;

        try {
            Connection connex = this.conexion.conectarBD();
            Statement statement1 = connex.createStatement();
            ResultSet registro = statement1.executeQuery("SELECT dni FROM cliente ORDER BY dni DESC LIMIT 1");
            if (registro.next()) {
                lastIdCliente = registro.getInt("");
            }
            registro.close();
            statement1.close();
            this.conexion.desconectarBD(connex);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la búsqueda del ID");
        }
        return lastIdCliente;
        }
    }



