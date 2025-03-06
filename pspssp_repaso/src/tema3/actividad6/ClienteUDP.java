package tema3.actividad6;

import java.net.*;
import java.io.*;

public class ClienteUDP {
    public static void main(String[] args) {
        String servidor = "127.0.0.1"; // Dirección del servidor (localhost)
        int puerto = 5000;  // Puerto del servidor
        byte[] buffer;  // Buffer para enviar datos
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(4000);  // Espera máximo 4 segundos por respuesta

            while (true) {
                // Leer mensaje del usuario
                System.out.print("Introduce un mensaje (* para salir): ");
                String mensaje = teclado.readLine();

                // Convertir a bytes y enviar al servidor
                buffer = mensaje.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        buffer, buffer.length, InetAddress.getByName(servidor), puerto
                );
                socket.send(paqueteSalida);

                // Si el usuario escribió "*", terminar el cliente
                if (mensaje.equals("*")) {
                    System.out.println("Saliendo del cliente...");
                    break;
                }

                // Recibir respuesta del servidor
                buffer = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);

                try {
                    socket.receive(paqueteEntrada);
                    String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                    System.out.println("Servidor responde: " + respuesta);
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. No se recibió respuesta del servidor.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
