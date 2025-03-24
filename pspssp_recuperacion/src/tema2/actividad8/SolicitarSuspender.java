package tema2.actividad8;

public class SolicitarSuspender {
    public boolean suspender;

    public synchronized void set (boolean b){
        suspender = b;
        notifyAll();
    }

    public synchronized void esperandoParaContinuar() throws InterruptedException {
        while (suspender){
            wait(); //SUSPENDER HILO HASTA RECIBIR notify
        }
    }

}
