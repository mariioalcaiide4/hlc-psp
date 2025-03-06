package tema3.actividad6;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 5000;  // Puerto en el que escucha el servidor
        byte[] buffer = new byte[1024];  // Buffer para recibir los datos

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP esperando mensajes en el puerto " + puerto);

            while (true) {
                // Recibir mensaje del cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                // Convertir el mensaje a String
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                // Si el mensaje es "*", cerrar el servidor
                if (mensaje.equals("*")) {
                    System.out.println("Asterisco recibido. Cerrando servidor...");
                    break;
                }

                // Convertir mensaje a mayúsculas
                String respuesta = mensaje.toUpperCase();
                byte[] bufferSalida = respuesta.getBytes();

                // Enviar respuesta al cliente
                DatagramPacket paqueteSalida = new DatagramPacket(
                        bufferSalida, bufferSalida.length,
                        paqueteRecibido.getAddress(), paqueteRecibido.getPort()
                );
                socket.send(paqueteSalida);
                System.out.println("Respuesta enviada: " + respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

