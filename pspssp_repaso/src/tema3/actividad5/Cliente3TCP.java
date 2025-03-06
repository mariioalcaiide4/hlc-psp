package tema3.actividad5;

import java.io.*;
import java.net.*;

public class Cliente3TCP {
    public static void main(String[] args) throws IOException {

        String servidor = "localhost";
        int puerto = 5900;
        Socket cliente = new Socket(servidor, puerto);

        //CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        int numeroCliente = flujoEntrada.readInt();
        System.out.println("Soy el Cliente número: " + numeroCliente);

        //CERRAR LOS FLUJOS

        flujoEntrada.close();
        cliente.close();

    }
}