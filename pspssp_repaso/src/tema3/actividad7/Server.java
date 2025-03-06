package tema3.actividad7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int puerto = 5000;
        ServerSocket serverSocket = new ServerSocket(puerto);
        Random random = new Random();
        System.out.println("Esperando al cliente");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + socket.getInetAddress());


            //Se prepara un flujo de salida para objetos
            ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());

            // Recibir objeto Numeros
            Numeros numeros = (Numeros) flujoEntrada.readObject();
            System.out.println("Número recibido: " + numeros.getNumero());

            // Si el número es menor o igual a 0, finalizar servidor
            if (numeros.getNumero() <= 0) {
                System.out.println("Número no válido. Cerrando servidor...");
                flujoEntrada.close();
                flujoSalida.close();
                socket.close();
                return;
            }

            // Calcular cuadrado y cubo
            numeros.setCuadrado((long) Math.pow(numeros.getNumero(), 2));
            numeros.setCubo((long) Math.pow(numeros.getNumero(), 3));

            // Enviar objeto actualizado al cliente
            flujoSalida.writeObject(numeros);
            System.out.println("Resultados enviados: Cuadrado=" + numeros.getCuadrado() + ", Cubo=" + numeros.getCubo());

            // Cerrar conexión con el cliente
            flujoEntrada.close();
            flujoSalida.close();
            socket.close();

        }

    }
}
