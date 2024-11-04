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
            Thread.sleep(8000);
        } catch(Exception e){ }




    }
}
