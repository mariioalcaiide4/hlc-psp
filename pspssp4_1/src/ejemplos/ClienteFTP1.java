package ejemplos;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;


public class ClienteFTP1 {
    public static void main(String[] args) throws SocketException, IOException {

        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a: " + servFTP);
        cliente.connect(servFTP);

        // respuesta del servidor FTP
        System.out.println(cliente.getReplyString());

        // código de respuesta

        int respuesta = cliente.getReplyCode();
        System.out.println("Respuesta: "+respuesta);

        //comprobación del código de respuesta

        if (!FTPReply.isPositiveCompletion(respuesta)){
            cliente.disconnect();
            System.out.println("Conexión rechazada: "+respuesta);
        }

        //desconexión del servidor FTP

        cliente.disconnect();
        System.out.println("Conexión finalizada");

    }
}