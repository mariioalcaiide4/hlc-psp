package actividad4_4;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.IOException;
import java.util.Scanner;

public class ServidorCorreo {
    public static void main (String[] args){

        Scanner scanner = new Scanner(System.in);
        try {
                    // Pedir los datos necesarios
            System.out.print("Introduce servidor SMTP: ");
            String smtpServer = scanner.nextLine();

            System.out.print("Introduce puerto SMTP: ");
            int port = Integer.parseInt(scanner.nextLine());

            System.out.print("Introduce correo del remitente: ");
            String fromEmail = scanner.nextLine();

            System.out.print("Introduce correo destinatario: ");
            String toEmail = scanner.nextLine();

            System.out.print("Introduce asunto: ");
            String subject = scanner.nextLine();

            System.out.println("Introduce el mensaje (finalizará cuando se introduzca un * ):");
            StringBuilder messageBody = new StringBuilder();
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("*")) break;
                messageBody.append(line).append("\n");
            }

            scanner.close();

            // Crear y conectar el cliente SMTP
            SMTPClient cliente = new SMTPClient();
            cliente.connect(smtpServer, port);
            int replyCode = cliente.getReplyCode();

            if (!cliente.login(smtpServer)) {
                System.out.println("Error: No se pudo iniciar sesión en el servidor SMTP.");
                cliente.disconnect();
                return;
            }

            System.out.println("Conectado al servidor SMTP. Código de respuesta: " + replyCode);

            // Crear el encabezado del correo
            SimpleSMTPHeader header = new SimpleSMTPHeader(fromEmail, toEmail, subject);

            // Enviar el correo
            cliente.setSender(fromEmail);
            cliente.addRecipient(toEmail);

            Writer writer = cliente.sendMessageData();
            if (writer == null) {
                System.out.println("Fallo al enviar");
                System.exit(1);
            }
            writer.write(header.toString());
            writer.write(messageBody.toString());
            writer.flush();
            writer.close();
            cliente.completePendingCommand();
            System.out.println("Correo enviado con éxito.");


            // Desconectar
            cliente.logout();
            cliente.disconnect();

        } catch (IOException e) {
            System.out.println("Error en la conexión SMTP: " + e.getMessage());
        }
    }
}
