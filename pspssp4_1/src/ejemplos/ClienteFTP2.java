package ejemplos;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class ClienteFTP2 {
    public static void main(String[] args){
        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a: " + servFTP);
        String usuario = "anonymous";
        String contraseña = "anonymous";
        try {




        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
