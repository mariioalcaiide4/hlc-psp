package map;

import java.io.IOException;
import java.io.*;

public class actividad1_8 {
    public static void main (String [] args) throws IOException {

    File directorio3 = new File("/home/usuario/hlc-psp/pspssp/out/production/pspssp");
    ProcessBuilder pr = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "map.actividad1_7_2");
    pr.directory(directorio3);

    //Creo el diretorio donde se va a alojar el proceso y creo el proceso

    Process prt = pr.start();

    // Creamos el proceso

    try {
        InputStream is = prt.getInputStream();
        int c;
        while ((c = is.read()) != -1)
            System.out.print((char) c);
        is.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Sacamos el programa por consola




    pr.redirectInput(ProcessBuilder.Redirect.from(fEnt));

    // Redirijimos la entrada al archivo fEnt

    pr.redirectOutput(ProcessBuilder.Redirect.to(fSal));

    // Redirijimos la salida al archivo fSal

     Process prt2 = pr.start();






    }
}
