package tema2.actividad2;

import jdk.swing.interop.SwingInterOpUtils;

public class Main implements Runnable {

    @Override
    public void run() {
        System.out.println("Hola mundo" + "ID:" + Thread.currentThread().getId());
    }

    public static void main (String[] args) {

        hilo1 h1 = new hilo1();
        hilo2 h2 = new hilo2();

        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);

        t1.start();
        t2.start();

    }
}
