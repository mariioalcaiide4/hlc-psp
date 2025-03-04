package tema2.actividad4;

public class MyHilo extends Thread {
    private SolicitarSuspender suspender = new SolicitarSuspender();
    private boolean ejecutando = true; // Variable para controlar el bucle
    long contador = 0;

    public void Suspende(){
        suspender.set(true);
    }

    public void Reanuda(){
        suspender.set(false);
    }

    public void Detener(){
        ejecutando = false;
    }

    public long getContador(){
        return contador;
    }

    public void run() {
        while (ejecutando) {
            try {
                suspender.esperandoParaReanudar(); // Se suspende si es necesario
                contador++;
                System.out.println("Contador: " + contador);
                Thread.sleep(1500); // Simula trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


