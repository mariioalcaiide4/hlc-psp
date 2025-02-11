package actividad4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

public class ProgramaCorreo {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario los datos de conexión
        System.out.print("Ingrese el servidor POP3: ");
        String servidor = scanner.next();

        System.out.print("Ingrese el puerto: ");
        int puerto = scanner.nextInt();

        System.out.print("Ingrese su usuario: ");
        String nombreUsuario = scanner.next();

        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.next();

        // Crear cliente POP3 para conectar con el servidor
        POP3Client clientePOP3 = new POP3Client();
        try {
            // Intentar conectar con el servidor POP3
            clientePOP3.connect(servidor, puerto);
            System.out.println("Conexión establecida: " + clientePOP3.getReplyString());

            // Intentar autenticar con el usuario y la contraseña proporcionados
            if (!clientePOP3.login(nombreUsuario, contrasena)) {
                System.err.println("Error: Autenticación fallida");
                clientePOP3.disconnect(); // Desconectar si la autenticación falla
                return;
            }

            System.out.println("Autenticación exitosa");

            // Obtener la lista de mensajes en la bandeja de entrada
            POP3MessageInfo[] listaMensajes = clientePOP3.listMessages();
            if (listaMensajes == null || listaMensajes.length == 0) {
                System.out.println("No hay correos en la bandeja de entrada");
            } else {
                System.out.println("Total de mensajes en la bandeja: " + listaMensajes.length);

                // Iterar sobre los mensajes y leer su contenido
                for (POP3MessageInfo mensaje : listaMensajes) {
                    System.out.println("Leyendo mensaje número " + mensaje.number);

                    Reader lectorMensaje = clientePOP3.retrieveMessage(mensaje.number); // Obtener el mensaje
                    if (lectorMensaje != null) {
                        try (BufferedReader bufferLector = new BufferedReader(lectorMensaje)) {
                            String linea;
                            while ((linea = bufferLector.readLine()) != null) {
                                System.out.println(linea);  // Mostrar el contenido del mensaje
                            }
                        }
                    } else {
                        System.out.println("No se pudo recuperar el mensaje número " + mensaje.number);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor");
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar la sesión y desconectar del servidor al finalizar
            try {
                clientePOP3.logout();  // Cerrar sesión del servidor POP3
                clientePOP3.disconnect();  // Desconectar del servidor
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Proceso finalizado");
    }
}