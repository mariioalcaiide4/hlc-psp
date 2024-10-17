package Actividad2;

public class hilo 1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Hola mundo" + "ID:" + Thread.currentThread().getId());

    }
