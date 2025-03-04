package tema2.actividad4;

public class SolicitarSuspender {

    //Suspender lo pone como un booleano, que puede ser true o false

    private boolean suspender;

    // El método set() es synchronized, lo que significa que solo un hilo puede ejecutarlo a la vez.
    // El método set() también notifica a todos los hilos que están esperando en el objeto SolicitarSuspender.

    public synchronized void set(boolean b){
        suspender = b;
        notifyAll();
    }

    // El método esperandoParaReanudar() es synchronized, lo que significa que solo un hilo puede ejecutarlo a la vez.
    // El método esperandoParaReanudar() también espera hasta que el valor de suspender sea false.
    // Si suspender es true, el hilo se queda esperando en el objeto SolicitarSuspender.

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait();
        }
    }
}
