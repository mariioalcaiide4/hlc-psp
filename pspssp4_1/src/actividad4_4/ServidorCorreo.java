package actividad4_4;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

import java.io.IOException;

public class ServidorCorreo {
    public static void main (String[] args){

        SMTPClient cliente = new SMTPClient();
        AuthenticatingSMTPClient logeo = new AuthenticatingSMTPClient();
        int respuesta;
        String servSMTP = "localhost";
        String destinatario = "";
        String mensaje = "";
        String remitente = "";
        String username = "";
        String password = "";


        try {
            cliente.connect(servSMTP);
            System.out.println(cliente.getReplyString());
            respuesta = cliente.getReplyCode();

            if(!SMTPReply.isPositiveCompletion(respuesta)){
                System.out.println("Conexión rechazada...");
                System.exit(1);
            }






        } catch (IOException e){
            System.out.println("No se puede conectar al servidor...");
            e.printStackTrace();
            System.exit(2);
        }
    }
}
