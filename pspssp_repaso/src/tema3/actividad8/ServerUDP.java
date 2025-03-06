package tema3.actividad8;

import java.io.*;
import java.net.*;

public class ServerUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int puerto = 5000; // Puerto del servidor
        DatagramSocket socket = new DatagramSocket(puerto);
        byte[] buffer = new byte[1024];

        System.out.println("Servidor UDP esperando datagramas en el puerto " + puerto);

        // Recibir datagrama del cliente
        DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteRecibido);

        // Deserializar objeto Persona
        ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Persona persona = (Persona) ois.readObject();
        ois.close();

        System.out.println("Persona recibida del cliente: " + persona);

        // Modificar datos de la persona (aumentar edad)
        persona.setEdad(persona.getEdad() + 1);

        // Serializar objeto modificado
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(persona);
        oos.close();
        byte[] datosModificados = baos.toByteArray();

        // Enviar objeto modificado al cliente
        DatagramPacket paqueteSalida = new DatagramPacket(
                datosModificados, datosModificados.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort()
        );
        socket.send(paqueteSalida);

        System.out.println("Objeto modificado enviado al cliente: " + persona);

        // Cerrar socket
        socket.close();
    }
}

