package map;

import java.io.*;

public class actividad1_6_2 {
    public static void main(String[] args) throws IOException {

        File directorio = new File("/home/usuario/hlc-psp/pspssp/out/production/pspssp");
        ProcessBuilder plb = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "map.actividad1_6");
        plb.directory(directorio);

        //Creo el directorio donde va a estar alojado el proceso y defino el proceso que se va a llevar a cabo

        Process pr = plb.start();
        OutputStream os = pr.getOutputStream();

        //Cominezo el proceso y envío entrada del proceso "pr" que ejecuta el programa actividad1_6 y sobreescribiría información, en este caso numeros

        os.write("15\n".getBytes());
        os.write("24\n".getBytes());
        os.flush();

        //Meto de entrada dos numero, en este caso las dos variables que tengo y limpiezo el flujo de la entrada

        InputStream is = pr.getInputStream();
        int c;
        while ((c = is.read()) != -1)
            System.out.print((char) c);
        is.close();

        //Obtengo la salida del programa del proceso "pr" osea "actividad1_6"

        int valorSal;
        try {
            valorSal = pr.waitFor();
            System.out.println("Valor de salida " + valorSal);
        }catch (InterruptedException e){
            e.printStackTrace();

        }










    }
}
