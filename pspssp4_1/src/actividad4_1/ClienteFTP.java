package actividad4_1;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

public class ClienteFTP {
    public static void main (String [] args) throws SocketException, IOException {

        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a " + servFTP);
        cliente.connect(servFTP);

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

            Scanner
            String directorio1 = "";
            if (cliente.changeWorkingDirectory(directorio1))
                System.out.println("El directorio actual es " + cliente.printWorkingDirectory());
            else
                System.out.println("No existe el directorio " + directorio1);
            FTPFile[] files = cliente.listFiles();


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


        


