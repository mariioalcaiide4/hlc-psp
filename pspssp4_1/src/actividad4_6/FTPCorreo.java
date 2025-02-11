package actividad4_6;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FTPCorreo {

    private static final String FTP_SERVER = "localhost";
    private static final int FTP_PORT = 21;

    private static final String SMTP_SERVER = "smtp.example.com"; // Servidor SMTP
    private static final int SMTP_PORT = 25;
    private static final String SMTP_FROM = "admin@example.com";
    private static final String SMTP_TO = "admin@example.com";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int usuariosConectados = 0;

        while (true) {
            System.out.print("Introduce nombre de usuario (* para salir): ");
            String username = scanner.nextLine();
            if (username.equals("*")) break;

            System.out.print("Introduce contraseña: ");
            String password = scanner.nextLine();

            if (autenticarUsuario(username, password)) {
                usuariosConectados++;
                registrarConexion(username);
            } else {
                System.out.println("Error: Usuario o contraseña incorrectos.");
            }
        }

        // Enviar correo electrónico con el total de usuarios conectados
        enviarCorreo(usuariosConectados);

        scanner.close();
    }

    private static boolean autenticarUsuario(String username, String password) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FTP_SERVER, FTP_PORT);
            if (ftpClient.login(username, password)) {
                System.out.println("Autenticación exitosa para " + username);
                ftpClient.logout();
                ftpClient.disconnect();
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error de conexión FTP: " + e.getMessage());
        }
        return false;
    }

    private static void registrarConexion(String username) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FTP_SERVER, FTP_PORT);
            ftpClient.login(username, "usu" + username.charAt(username.length() - 1)); // usa su contraseña
            ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            String logFilePath = username + "/LOG/LOG.TXT";
            String newLogEntry = "Hora de conexión: " + new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new Date()) + "\n";

            InputStream inputStream = ftpClient.retrieveFileStream(logFilePath);
            StringBuilder contenido = new StringBuilder("Conexiones del usuario.\n");
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    contenido.append(line).append("\n");
                }
                reader.close();
                inputStream.close();
            }

            contenido.append(newLogEntry);
            OutputStream outputStream = ftpClient.storeFileStream(logFilePath);
            outputStream.write(contenido.toString().getBytes());
            outputStream.close();
            ftpClient.completePendingCommand();

            System.out.println("Registro actualizado para " + username);

            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            System.out.println("Error al registrar la conexión: " + e.getMessage());
        }
    }

    private static void enviarCorreo(int usuariosConectados) {
        if (usuariosConectados == 0) return;

        SMTPClient smtpClient = new SMTPClient();
        try {
            smtpClient.connect(SMTP_SERVER, SMTP_PORT);
            if (!smtpClient.login(SMTP_SERVER)) {
                System.out.println("Error: No se pudo conectar al servidor SMTP.");
                smtpClient.disconnect();
                return;
            }

            smtpClient.setSender(SMTP_FROM);
            smtpClient.addRecipient(SMTP_TO);

            SimpleSMTPHeader header = new SimpleSMTPHeader(SMTP_FROM, SMTP_TO, "Reporte de conexiones FTP");
            String mensaje = "Se han conectado " + usuariosConectados + " usuarios al servidor FTP.";

            Writer writer = smtpClient.sendMessageData();
            writer.write(header.toString());
            writer.write(mensaje);
            writer.flush();
            writer.close();
            smtpClient.completePendingCommand();

            System.out.println("Correo enviado con éxito.");

            smtpClient.logout();
            smtpClient.disconnect();
        } catch (IOException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}

