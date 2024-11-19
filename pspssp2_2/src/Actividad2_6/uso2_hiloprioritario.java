package Actividad2_6;

public class uso2_hiloprioritario extends Thread {
    uso2_hiloprioritario(String nombre){
        this.setName(nombre);
    }

    public void run() {
        System.out.println("Ejecutando [" + getName() + "]");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("\t(" + getName() + ": " + i + ")");
        }
    }

    public static void main (String [] args) {

        uso2_hiloprioritario hilo1 = new uso2_hiloprioritario("Hilo");
        uso2_hiloprioritario hilo2 = new uso2_hiloprioritario("Hilo2");
        uso2_hiloprioritario hilo3 = new uso2_hiloprioritario("Hilo3");
        uso2_hiloprioritario hilo4 = new uso2_hiloprioritario("Hilo4");
        uso2_hiloprioritario hilo5 = new uso2_hiloprioritario("Hilo5");
        uso2_hiloprioritario hilo6 = new uso2_hiloprioritario("Hilo6");
        uso2_hiloprioritario hilo7 = new uso2_hiloprioritario("Hilo7");
        uso2_hiloprioritario hilo8 = new uso2_hiloprioritario("Hilo8");
        uso2_hiloprioritario hilo9 = new uso2_hiloprioritario("Hilo9");
        uso2_hiloprioritario hilo10 = new uso2_hiloprioritario("Hilo10");


        //Asignamos prioridades

        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo2.setPriority(Thread.MAX_PRIORITY);
        hilo3.setPriority(3);
        hilo4.setPriority(5);
        hilo5.setPriority(7);
        hilo6.setPriority(8);
        hilo7.setPriority(9);
        hilo8.setPriority(3);
        hilo9.setPriority(Thread.NORM_PRIORITY);
        hilo10.setPriority(2);

        //Ejecutamos hilos

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();


    }
}

