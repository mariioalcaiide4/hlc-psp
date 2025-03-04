package tema3.ejemplo1;

import java.io.*;
import java.net.*;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 5555; //mismo puerto que en el servidor
        try (Socket socket = new Socket("localhost", puerto);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor.");
            System.out.println("¿Qué acción deseas realizar? (crear/modificar/listar): ");
            String action = reader.readLine();
            output.writeUTF(action);
            output.flush();

            while (true) {
                if (action.equals("crear")) {
                    System.out.print("Ingrese ID del alumno: ");
                    int id = Integer.parseInt(reader.readLine());

                    System.out.print("Ingrese nombre del alumno: ");
                    String nombre = reader.readLine();

                    System.out.print("Ingrese edad del alumno: ");
                    int edad = Integer.parseInt(reader.readLine());

                    Alumno alumno = new Alumno(id, nombre, edad);
                    output.writeObject(alumno);
                    output.flush();
                } else if (action.equals("modificar")) {
                    System.out.print("Ingrese ID del alumno a modificar: ");
                    int id = Integer.parseInt(reader.readLine());
                    output.writeInt(id);

                    System.out.print("Ingrese nuevo nombre: ");
                    String nuevoNombre = reader.readLine();
                    output.writeUTF(nuevoNombre);

                    System.out.print("Ingrese nueva edad: ");
                    int nuevaEdad = Integer.parseInt(reader.readLine());
                    output.writeInt(nuevaEdad);

                    output.flush();
                } else if (action.equals("listar")) {
                    List<Alumno> alumnos = (List<Alumno>) input.readObject();
                    System.out.println("Lista de alumnos:");
                    for (Alumno alumno : alumnos) {
                        System.out.println(alumno);
                    }
                    return;
                }

                System.out.println("Respuesta del servidor: " + input.readUTF());
            }


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

