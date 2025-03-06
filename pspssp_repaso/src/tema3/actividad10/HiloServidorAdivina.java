package tema3.actividad10;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidorAdivina extends Thread {
    ObjectInputStream fentrada;
    ObjectOutputStream fsalida;
    Socket socket = null;
    ObjetoCompartido objeto;
    int identificador;
    int intentos = 0;

    public HiloServidorAdivina(Socket s, ObjetoCompartido objeto, int id) {
        this.socket = s;
        this.objeto = objeto;
        this.identificador = id;
        try {
            fsalida = new ObjectOutputStream(socket.getOutputStream());
            fentrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S en HiloServidor");
            e.printStackTrace();
        }
    } // fin Constructor

    public void run() {
        Datos datos = new Datos("Adivina un número entre 1 y 25", intentos, identificador);
        System.out.println("\t=>Cliente conectado: " + identificador);

        // PREPARAR PRIMER ENVÍO AL CLIENTE
        if (objeto.seAcabo()) {
            datos.setCadena("LO SENTIMOS, EL JUEGO HA TERMINADO, HAN ADIVINADO EL N°");
            datos.setJuega(false); // YA NO TIENE QUE JUGAR
        }
        try {
            fsalida.reset();
            fsalida.writeObject(datos);
        } catch (IOException e) {
            System.out.println("Error en el Hilo al realizar el primer envío al jugador: " + identificador);
            return;
        }

        while (!objeto.seAcabo() || !(datos.getIntentos() == 5)) {
            int numecli = 0;
            try {
                // RECIBIR DATOS DEL CLIENTE
                Datos d = (Datos) fentrada.readObject();
                numecli = Integer.parseInt(d.getCadena());
            } catch (IOException e) {
                System.out.println("Error en el Hilo al leer del jugador: " + identificador);
                break;
            } catch (NumberFormatException n) {
                System.out.println("El jugador: " + identificador + " se ha desconectado");
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }

            // JUGAR EL NÚMERO
            String cad = objeto.nuevaJugada(identificador, numecli);
            intentos++;

            datos = new Datos(cad, intentos, identificador);

            if (objeto.seAcabo()) {
                datos.setJuega(false); // no tiene que seguir jugando
                if (identificador == objeto.getGanador()) {
                    datos.setGana(true);
                }
            }

            try {
                // ENVIAR DATOS AL CLIENTE
                fsalida.reset();
                fsalida.writeObject(datos);
            } catch (IOException ni) {
                System.out.println("Error escribiendo en flujo de salida del jugador: " + identificador + " " + ni.getMessage());
                break;
            } catch (NullPointerException n) {
                System.out.println("El jugador " + identificador + " ha desconectado ");
                break;
            }
        } // fin while

        if (objeto.seAcabo()) {
            System.out.println("EL JUEGO SE HA ACABADO.....!");
            System.out.println("\t==>Desconecta: " + identificador);
        }
        try {
            fsalida.close();
            fentrada.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error en Hilo al cerrar cliente: " + identificador);
            e.printStackTrace();
        }
    } // fin método run
}
