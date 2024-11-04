package Actividad3;

import Actividad3.HiloContador1;

public class UsoContador extends Thread {
    public static void main(String[] args) {

        //Primer hilo
        HiloContador1 hilo1 = new HiloContador1();
        new Thread(String.valueOf(hilo1)).start();

        //Segundo hilo
        HiloContador1 hilo2 = new HiloContador1();
        new Thread(String.valueOf(hilo2)).start();
    }
}