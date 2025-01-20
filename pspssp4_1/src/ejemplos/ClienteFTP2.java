package ejemplos;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class ClienteFTP2 {
    public static void main(String[] args){
        FTPClient cliente = new FTPClient();
        //Se crea el cliente de ftp
        String servFTP = "ftp.rediris.es";
        //Se pone la dirección del cliente
        System.out.println("Nos conectamos a: " + servFTP);
        //Credenciales para acceder al servidor FTP
        String usuario = "anonymous";
        String contraseña = "anonymous";
        try {
                cliente.connect(servFTP);
                cliente.enterLocalPassiveMode(); //modo pasivo

                boolean login = cliente.login(usuario, contraseña);
                if (login)
                    System.out.println("Login correcto...");
                else {
                    System.out.println("Credenciales incorrectas...");
                    cliente.disconnect();
                    System.exit(1);
                }
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
