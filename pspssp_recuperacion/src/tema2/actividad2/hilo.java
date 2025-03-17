package tema2.actividad2;

public class hilo implements Runnable {
    private String mensaje;

    public hilo (String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
        System.out.println(mensaje + ", ID: " + Thread.currentThread().getId());
    }

    public static void main (String[] args){
        for (int i = 1; i <= 5; i++) {
            hilo hilo = new hilo("Hola mundo " + i);
            Thread hiloThread = new Thread(hilo);
            hiloThread.start();
        }
    }
}
