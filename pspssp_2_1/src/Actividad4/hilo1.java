package Actividad4;

public class hilo1 extends Thread {
    private suspender suspender1 = new suspender();

    public void Suspender() {
        suspender1.set(true);
    }

    public void Reanudar() {
        suspender1.set(false);
    }

    @Override
    public void run() {
        try {
            suspender1.esperandoParaReanudar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}