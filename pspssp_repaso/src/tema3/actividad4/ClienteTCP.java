package tema3.actividad4;

import java.io.*;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) throws IOException {

        // Definimos la dirección IP del servidor al que queremos conectarnos
        String servidorIP = "127.0.0.1";
        int puerto = 5000;
        System.out.println("Programa Cliente Arrancado...");
        Socket cliente = new Socket(servidorIP, puerto);

        //CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        // Pedir un número al usuario
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduce un número entero: ");
        int numero = Integer.parseInt(teclado.readLine());

        // Enviar número al servidor
        flujoSalida.writeInt(numero);
        System.out.println("Número enviado al servidor.");

        //CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        // Recibir y mostrar el resultado
        int resultado = flujoEntrada.readInt();
        System.out.println("El cuadrado de " + numero + " es: " + resultado);


        //CERRAR SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
    }
}