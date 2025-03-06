package tema3.actividad2;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 5000; // Puerto donde escucha el servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Aceptamos el primer cliente
            System.out.println("\nEsperando primer cliente...");
            Socket cliente1 = servidor.accept();
            System.out.println("Cliente 1 conectado");

            // Aceptamos el segundo cliente
            System.out.println("\nEsperando segundo cliente...");
            Socket cliente2 = servidor.accept();
            System.out.println("Cliente 2 conectado");

            // Cerramos las conexiones
            cliente1.close();
            cliente2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
