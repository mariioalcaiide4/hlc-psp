package tema3.actividad9;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 44444;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("=>Conecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());

                // Crear y ejecutar un hilo para atender al cliente
                new Thread(new ManejadorCliente(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Clase que maneja cada cliente en un hilo separado
class ManejadorCliente implements Runnable {
    private Socket cliente;

    public ManejadorCliente(Socket socket) {
        this.cliente = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equals("*")) {
                    System.out.println("=>Desconecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
                    break; // Salir del bucle y cerrar la conexión
                }

                String respuesta = mensaje.toUpperCase();
                salida.println(respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

