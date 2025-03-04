//Esta actividad la he planteado creando las diferentes clases como dicen el papel y la teoría
//y me he ido fijando tambien en las actividades que hemos ido haciendo a lo largo de la unidad
//El código no funciona porque no me ha dado tiempo a acabar la actividad


package actividad2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;

public class SocketServer {
    public static void main(String[] args) {
        int puerto = 6000; // Puerto donde el servidor escuchará
        int n = 20; // Número de clientes que el servidor manejará

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado, esperando hasta " + n + " clientes...");

            System.out.println("Profesores disponibles " + profe[5] );

            for (int i = 1; i <= n; i++) {
                Socket clienteConectado = servidor.accept(); // Espera la conexión del cliente
                System.out.println("Cliente " + i + " conectado");

                // Crear una instancia de la clase ClienteHandler y pasar el cliente y su número
                IdCliente contClientes = new IdCliente(clienteConectado, i);
                contClientes.start(); // Inicia el hilo
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
