package tema3.actividad3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Locale;

public class ClienteTCP {
    public static void main(String[] args) throws IOException {

        // Definimos la dirección IP del servidor al que queremos conectarnos
        String servidorIP = "127.0.0.1";
        int puerto = 5000;
        System.out.println("Programa Cliente Arrancado...");
        Socket cliente = new Socket(servidorIP, puerto);

        //CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        //ENVIO UN SALUDO AL SERVIDOR

        //CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        //EL SERVIDOR ME MANDA UN MENSAJE
        String mensaje = flujoEntrada.readUTF();
        System.out.println("Mensaje recibido: " + mensaje);

        String mensajeEnMinusculas = mensaje.toLowerCase();
        flujoSalida.writeUTF(mensajeEnMinusculas);


        //CERRAR SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
    }
}