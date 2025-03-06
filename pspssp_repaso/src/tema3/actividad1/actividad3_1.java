package tema3.actividad1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class actividad3_1 {
    public static void main (String[] args) {
        InetAddress direccion = null;
        System.out.println("-------------------------------------");
        System.out.println("SALIDA PARA MÁQUINA ELEGIDA");
        try {
            System.out.println("Introduce una dirección IP");
            Scanner scanner = new Scanner(System.in);
            String entrada = scanner.nextLine();

            direccion = InetAddress.getByName(entrada);
            pruebaMetodos(direccion);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void pruebaMetodos(InetAddress direccion){
            System.out.println("\tMétodo getByName(): " + direccion);
            InetAddress direc2;
            try {
                direc2 = InetAddress.getLocalHost();
                System.out.println("\tMétodo getLocalHost(): " + direc2);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println("\tMétodo getHostName(): " + direccion.getHostName());
            System.out.println("\tMétodo getHostAddress(): " + direccion.getHostAddress());
            System.out.println("\tMétodo toString(): " + direccion.toString());
            System.out.println("\tMétodo getCanonicalHostName(): " + direccion.getCanonicalHostName());
            System.out.println("-------------------------------------");

    }
}

