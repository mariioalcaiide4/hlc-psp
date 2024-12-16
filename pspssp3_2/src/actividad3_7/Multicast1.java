package actividad3_7;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Multicast1 {
    public void main (String[] args){

        try {
            //Se crea socket multicast en el puerto 12345
            MulticastSocket ms = new MulticastSocket(12345);
            //Se configura la IP del grupo al que nos conectaremos
            InetAddress grupo = InetAddress.getByName("255.0.0.1");
            //Recibe el paquete del servidor multicast
            byte[] buf = new byte[1000];
            DatagramPacket recibido = new DatagramPacket(buf, buf.length);

            ms.joinGroup(grupo); //Se une al grupo
            ms.receive(recibido); //Recibe el paquete
            ms.leaveGroup(grupo); //Abandona el grupo

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
