package tema3.actividad5;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        int puerto = 5900;  // Puerto del servidor
        int maxClientes = 3;  // Número de clientes a aceptar (puedes cambiarlo)

        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor escuchando en el puerto " + puerto);

        for (int i = 1; i <= maxClientes; i++) {
            // Aceptar cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente " + i + " conectado desde: " + cliente.getInetAddress());

            // Crear flujo de salida
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            // Enviar el número de cliente
            flujoSalida.writeInt(i);
            System.out.println("Número " + i + " enviado al cliente.");

            // Cerrar conexión con este cliente
            flujoSalida.close();
            cliente.close();
        }

        // Cerrar servidor después de atender a todos los clientes
        servidor.close();
    }
}
