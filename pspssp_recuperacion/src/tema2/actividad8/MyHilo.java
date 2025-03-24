package tema2.actividad8;

public class MyHilo extends Thread {

    private SolicitarSuspender suspender = new SolicitarSuspender();
    private boolean trabajando = true;
    long contador = 0;

    private void Suspende() {suspender.set(true);}

    private void Reanuda() {suspender.set(false);}

    private void Detener() {trabajando = (false);}

    private long getContador() {return contador;}

    public void run(){
        try {
            while(trabajando) {
                suspender.esperandoParaContinuar();
                contador++;
                System.out.println("Contador: " + contador);
                Thread.sleep(2500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
