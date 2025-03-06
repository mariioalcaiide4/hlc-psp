package tema3.actividad4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        int puerto = 5000; // Puerto donde escucha el servidor
        ServerSocket servidor = new ServerSocket(puerto);
        Socket cliente1 = null;
        System.out.println("Servidor escuchando en el puerto " + puerto);
        cliente1 = servidor.accept();

        //CREAMOS FLUJO DE ENTRADA DEL CLIENTE

        InputStream entrada = null;
        entrada = cliente1.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        // Leer el número enviado por el cliente
        int numero = flujoEntrada.readInt();
        System.out.println("Número recibido del cliente: " + numero);

        // Calcular el cuadrado del número

        int cuadrado = numero * numero;

        //FLUJO DE SALIDA

        OutputStream salida = null;
        salida = cliente1.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);
        flujoSalida.writeInt(cuadrado);

        // Cerramos las conexiones

        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        cliente1.close();
        servidor.close();
    }
}