package Actividad2_7;

public class usohilo {
    public static void main(String[] args) {

        hilo_contador cont = new hilo_contador(100);
        hilo1ejemplo a = new hilo1ejemplo("Hilo A", cont);
        hilo2ejemplo b = new hilo2ejemplo("Hilo B", cont);
        a.start();
        b.start();
    }
}
