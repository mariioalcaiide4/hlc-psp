package map;

import java.io.*;

public class actividad1_6 {

    public static void main(String [] args) throws IOException {

    InputStreamReader ini = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(ini);
    int numero, numero2;


    try {
        System.out.println("Introduce un numero ");
        numero = Integer.parseInt(br.readLine());
        System.out.println("El primer numero es " + numero);
        System.out.println("Introduce otro numero ");
        numero2 = Integer.parseInt(br.readLine());
        System.out.println("El segundo numero es " + numero2);
        System.out.println("La suma es " + (numero+numero2));
        ini.close();
        }catch (Exception e){
        e.printStackTrace();
    }


    }
}
