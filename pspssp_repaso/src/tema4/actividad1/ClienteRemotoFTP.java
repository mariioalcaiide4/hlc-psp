package tema4.actividad1;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class ClienteRemotoFTP {
    public static void main (String[] args){

        FTPClient cliente = new FTPClient();
        String servidor = "ftp.rediris.es";
        int puerto = 21;
        String usuario = "anonymous";
        String contraseña = "";

        try {
            cliente.connect(servidor);
            cliente.enterLocalPassiveMode(); //modo pasivo

            boolean login = cliente.login(usuario, contraseña);
            if (login)
                System.out.println("Login correcto...");
            else {
                System.out.println("Credenciales incorrectas...");
                cliente.disconnect();
                System.exit(1);
            }

            System.out.println("Conectado al servidor FTP.");

            // Cambiar a modo pasivo y binario
            cliente.enterLocalPassiveMode();
            cliente.setFileType(FTP.BINARY_FILE_TYPE);

            System.out.println("Dirección actual: " + cliente.printWorkingDirectory() );
            FTPFile[] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio actual:" + files.length);
            String tipos[] = {"Ficheros", "Directorio", "Enlace simb."};

            for (int i = 0; i < files.length; i++){
                System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
            }

            boolean logout = cliente.logout();
            if (logout)
                System.out.println("Logout del servidor FTP...");
            else
                System.out.println("Error al hacer logout...");
            cliente.disconnect();
            System.out.println("Desconectado...");
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
