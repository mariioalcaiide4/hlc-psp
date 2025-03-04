package tema2.actividad2;

public class hilo2 implements Runnable {
    public void run() {
        for (int i = 0; i > -1; i++) {
            System.out.println("TAC");
            try {
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}