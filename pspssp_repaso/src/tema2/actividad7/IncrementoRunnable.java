package tema2.actividad7;

public class IncrementoRunnable {
    public static void main(String[] args) {
        // Variable compartida
        ContadorSincronizado contador = new ContadorSincronizado();


        Runnable tarea = () -> {
            for (int i = 0; i < 5000; i++) {
                contador.incrementar();
            }
        };

        // Crear 5 hilos
        Thread[] hilos = new Thread[5];
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(tarea);
            }

        // Iniciar los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperar a que los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el valor final
        System.out.println("Valor final con sincronización: " + contador.getValor());
    }
}






