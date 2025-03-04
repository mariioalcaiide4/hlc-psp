package tema3.ejemplo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor_TCP {
    private static List<Alumno> alumnos = new ArrayList<>();

    public static void main(String[] args) {
        int puerto = 5555;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            //bucle infinito para aceptar conexiones del cliente
            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                    String mensajeCliente = input.readUTF(); //esto es para leer el mensaje del cliente

                    if (mensajeCliente.equals("crear")) {
                        Alumno alumno = (Alumno) input.readObject();
                        alumnos.add(alumno); //se añade al final de la lista
                        output.writeUTF("Alumno creado: " + alumno); //envío el mensaje al cliente

                    } else if (mensajeCliente.equals("modificar")) {
                        int id = input.readInt(); //con esto leo el id del alumno (int)
                        Alumno alumno = buscarAlumnoPorId(id);
                        //si el id existe
                        if (alumno != null) {
                            String nuevoNombre = input.readUTF();
                            int nuevaEdad = input.readInt();
                            alumno.setNombre(nuevoNombre);
                            alumno.setEdad(nuevaEdad);
                            output.writeUTF("Alumno modificado: " + alumno);
                        } else {
                            output.writeUTF("Alumno no encontrado");
                        }
                    } else if (mensajeCliente.equals("listar")) {
                        output.writeObject(alumnos); //muestro la lista de alumnos al cliente
                    }

                    output.flush(); //esto es para asegurarse de que se envíe el mensaje al cliente
                } catch (Exception e) {
                    System.out.println("Error en la conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    //método para buscar un alumno por su id, lo puedes meter directamente en el elseif de modificar
    private static Alumno buscarAlumnoPorId(int id) {
        for (Alumno alumno : alumnos) {
            if (alumno.getId() == id) {
                return alumno;
            }
        }
        return null;
    }
}