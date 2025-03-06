package tema3.actividad3;

import java.io.*;
import java.net.*;

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

        //FLUJO DE SALIDA

        OutputStream salida = null;
        salida = cliente1.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);

        //ENVIO UN SALUDO

        flujoSalida.writeUTF("ESTO ES UN MENSAJE EN MAYÚSCULA QUE SE VA A CONVERTIR");

        //CLIENTE ME ENVIA UN MENSAJE

        System.out.println("Recibiendo del cliente: \n\t" + flujoEntrada.readUTF());

        // Cerramos las conexiones
        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        cliente1.close();
        servidor.close();
    }
}