package Actividad2;

public class HiloNuevo implements Runnable {

    //Funcionalidad del hilo

    @Override
    public void run() {
        System.out.println("Hola mundo" + " ID: " + Thread.currentThread().getId());
    }

    public static void main (String[] args) {





    }
}
