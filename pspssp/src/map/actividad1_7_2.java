package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class actividad1_7_2 {
    public static void main (String[] args) throws IOException {

        InputStreamReader ini = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ini);
        String textito;

        try {
            System.out.println("Introduce una cadena... ");
            textito = "Hola";
            System.out.println("Cadena escrita " + textito);
            ini.close();
        }catch (Exception e) {
            e.printStackTrace();
        }






    }
}
