package Actividad2_6;

public class uso_hiloprioritario {
    public static void main(String[] args) {

        hiloprioritario h1 = new hiloprioritario("Hilo1");
        hiloprioritario h2 = new hiloprioritario("Hilo2");
        hiloprioritario h3 = new hiloprioritario("Hilo3");

        h1.setPriority(Thread.MAX_PRIORITY);
        h2.setPriority(Thread.NORM_PRIORITY);
        h3.setPriority(Thread.MIN_PRIORITY);

        h1.start();
        h2.start();
        h3.start();

        try {
            Thread.sleep(12000);
        } catch(Exception e){ }

        h1.pararHilo();
        h2.pararHilo();
        h3.pararHilo();

        System.out.println("h1 (Prioridad Maxima):" + h1.getContador());
        System.out.println("h2 (Prioridad Normal):" + h2.getContador());
        System.out.println("h3 (Prioridad Minima):" + h3.getContador());

    }
}
