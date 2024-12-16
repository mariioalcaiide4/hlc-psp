package actividad3_7;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class Multicast {
    public void main (String[] args){

        try {

            MulticastSocket ms = new MulticastSocket();

            int Puerto = 12345;

            InetAddress grupo = InetAddress.getByName("225.0.0.1");

            String msge = "Bienvenidos al grupo";

            DatagramPacket paquete = new DatagramPacket(msge.getBytes(), msge.length(), grupo, Puerto);

            ms.send(paquete);
            ms.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
