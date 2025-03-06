package tema3.actividad2;

import java.io.*;
import java.net.*;


public class Cliente2TCP {
    public static void main(String[] args) {

        // Definimos la dirección IP del servidor al que queremos conectarnos
        String servidorIP = "127.0.0.1";  // 127.0.0.1 significa "mi propia computadora" (localhost)

        // Definimos el puerto en el que el servidor está escuchando conexiones
        int puerto = 5000;

        // Intentamos conectarnos al servidor
        try (
                // Creamos un socket para conectarnos al servidor en la IP y puerto especificados
                Socket socket = new Socket(servidorIP, puerto);

        ) {
            // Si la conexión se establece con éxito, mostramos información sobre la conexión
            System.out.println("Conectado al servidor:");
            System.out.println(" - Puerto local del cliente: " + socket.getLocalPort());  // Puerto asignado al cliente
            System.out.println(" - Puerto remoto del servidor: " + socket.getPort());    // Puerto en el que el servidor está escuchando
            System.out.println(" - IP del servidor: " + socket.getInetAddress().getHostAddress()); // Dirección IP del servidor

        } catch (IOException e) {
            // Si ocurre un error al conectarse o al enviar/recibir datos, imprimimos el error
            e.printStackTrace();
        }
    }
}

