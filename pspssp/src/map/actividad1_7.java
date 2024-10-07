package map;

import java.io.File;
import java.io.IOException;

public class actividad1_7 {
    public static void main(String[] args) throws IOException {

        File directorio2 = new File("/home/usuario/hlc-psp/pspssp/out/production/pspssp");
        ProcessBuilder plb = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "map.actividad1_7_2");
        plb.directory(directorio2);

        // Creo el directorio donde se va a alojar el proceso y creo el proceso y lo ubico en el directorio

        File fSalida = new File("/home/usuario/hlc-psp/pspssp/out/production/pspssp/map/salida.txt");
        File fError = new File("/home/usuario/hlc-psp/pspssp/out/production/pspssp/map/error.txt");
        File fEntrada = new File("/home/usuario/hlc-psp/pspssp/out/production/pspssp/map/entrada.txt");

        // Creamos los archivos donde va a ir la salida y los errores

        plb.redirectInput(fEntrada);
        plb.redirectOutput(fSalida);
        plb.redirectError(fError);
        Process prt = plb.start();

        // Redireccionamos entrada, salida y errores a ficheros y iniciamos procesos





    }
}

