package tema3.actividad8;

import java.io.*;
import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String servidor = "127.0.0.1"; // IP del servidor
        int puerto = 5000; // Puerto del servidor
        DatagramSocket socket = new DatagramSocket();

        // Crear objeto Persona
        Persona persona = new Persona("Juan", 25);
        System.out.println("Persona antes de enviarla: " + persona.toString());

        // Serializar objeto Persona
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(persona);
        oos.close();
        byte[] datos = baos.toByteArray();

        // Enviar datagrama al servidor
        DatagramPacket paqueteSalida = new DatagramPacket(
                datos, datos.length, InetAddress.getByName(servidor), puerto
        );
        socket.send(paqueteSalida);

        // Recibir objeto modificado desde el servidor
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteEntrada);

        // Deserializar objeto modificado
        ByteArrayInputStream bais = new ByteArrayInputStream(paqueteEntrada.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Persona personaModificada = (Persona) ois.readObject();
        ois.close();

        System.out.println("Persona después de recibirla: " + personaModificada);

        // Cerrar socket
        socket.close();
    }
}

