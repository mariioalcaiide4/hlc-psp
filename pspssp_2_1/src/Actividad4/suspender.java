package Actividad4;

public class suspender {
    private boolean suspender;

    public synchronized void set (boolean b) {
        suspender = b;
        notifyAll();
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait();
        }
    }
}