package tema3.actividad7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String servidorIP = "127.0.0.1"; // IP del servidor
        int puerto = 5000; // Puerto del servidor
        Scanner scanner = new Scanner(System.in);

        // Pedir número al usuario
        System.out.print("Introduce un número entero (>0 para continuar, <=0 para salir): ");
        int numero = scanner.nextInt();

        // Crear objeto Numeros con el número ingresado
        Numeros numeros = new Numeros(numero);

        // Conectar con el servidor
        Socket cliente = new Socket(servidorIP, puerto);
        ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());

        // Enviar objeto al servidor
        flujoSalida.writeObject(numeros);

        // Si el número es menor o igual a 0, terminar cliente
        if (numero <= 0) {
            System.out.println("Número no válido. Saliendo del cliente...");
            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
            return;
        }

        // Recibir objeto actualizado del servidor
        numeros = (Numeros) flujoEntrada.readObject();

        // Mostrar resultados
        System.out.println("Número: " + numeros.getNumero());
        System.out.println("Cuadrado: " + numeros.getCuadrado());
        System.out.println("Cubo: " + numeros.getCubo());

        // Cerrar conexión
        flujoSalida.close();
        flujoEntrada.close();
        cliente.close();
    }
}
