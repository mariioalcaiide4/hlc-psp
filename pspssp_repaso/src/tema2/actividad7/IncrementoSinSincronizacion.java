package tema2.actividad7;

public class IncrementoSinSincronizacion {
    public static void main(String[] args) {
        // Variable compartida
        hilo_contador contador = new hilo_contador();

        // Crear 5 hilos
        Thread[] hilos = new Thread[5];
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    contador.incrementar();
                }
            });
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
        System.out.println("Valor final sin sincronización: " + contador.getValor());
    }
}

class hilo_contador {
    private int valor = 0;

    public void incrementar() {
        valor++;
    }

    public int getValor() {
        return valor;
    }
}

