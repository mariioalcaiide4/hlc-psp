package com.example.practicahotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Conexión exitosa.");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes");
            while (resultSet.next()) {
                System.out.println("Cliente: " + resultSet.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}