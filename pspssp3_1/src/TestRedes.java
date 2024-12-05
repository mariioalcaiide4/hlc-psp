import java.net.*;

public class TestRedes {
    public static void main(String[] args) {
        InetAddress direc = null;
        System.out.println("-------------------------------------------");
        System.out.println("SALIDA PARA LOCALHOST: ");
        try {
            //LOCALHOST
            direc = InetAddress.getByName("localhost");
            pruebaMetodos(direc);//

            //URL www.google.es
            System.out.println("-------------------------------------------");
            System.out.println("SALIDA PARA UNA URL");
            direc = InetAddress.getByName("www.google.es");
            pruebaMetodos(direc);

            //Array de las direcciones IP de google
            System.out.println("\tDIRECCIONES IP PARA: " + direc.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(direc.getHostName());
            for (int i = 0; i < direcciones.length; i++)
                System.out.println("\t\t"+direcciones[i].toString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }




    public static void pruebaMetodos(InetAddress direc){
        System.out.println("\tMétodo getByName(): " + direc);
        InetAddress direc2;
        try {
                direc2 = InetAddress.getLocalHost();
                System.out.println("\tMétodo getLocalHost(): " + direc2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("\tMétodo getHostName(): " + direc.getHostName());
        System.out.println("\tMétodo getHostAddress(): " + direc.getHostAddress());
        System.out.println("\tMétodo toString(): " + direc.toString());
        System.out.println("\tMétodo getCanonicalHostName(): " + direc.getCanonicalHostName());

    }
}