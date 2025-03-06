package tema3.actividad5;

import java.io.*;
import java.net.*;

public class Cliente1TCP {
    public static void main(String[] args) throws IOException {
        String servidor = "localhost";  // IP del servidor
        int puerto = 5900;  // Puerto donde escucha el servidor

        // Conectar con el servidor
        Socket cliente = new Socket(servidor, puerto);

        // Crear flujo de entrada para recibir datos
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        // Recibir número de cliente
        int numeroCliente = flujoEntrada.readInt();
        System.out.println("Soy el Cliente número: " + numeroCliente);

        // Cerrar conexiones
        flujoEntrada.close();
        cliente.close();
    }
}

