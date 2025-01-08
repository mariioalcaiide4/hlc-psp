package actividad3_7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCServer {
    public void main (String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        MulticastSocket ms = new MulticastSocket();
        int Puerto = 12345;
        InetAddress grupo = InetAddress.getByName("225.0.0.1"); //Grupo

        String cadena="";

        while(!cadena.trim().equals("*")){
            System.out.println("Datos a enviar al grupo");
            cadena = in.readLine();
            //ENVIANDO AL GRUPO
            DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, Puerto);
            ms.send(paquete);
        }
        ms.close();//cierro el socket
        System.out.println("Socket Cerrado...");
    }
}
