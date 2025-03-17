package tema2.actividad1;

public class actividad1 extends Thread {

    public void run() {
        System.out.println("Hola mundo " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        actividad1 hilo = new actividad1();
        actividad1 hilo2 = new actividad1();
        actividad1 hilo3 = new actividad1();
        actividad1 hilo4 = new actividad1();
        actividad1 hilo5 = new actividad1();

        hilo.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

    }
}
