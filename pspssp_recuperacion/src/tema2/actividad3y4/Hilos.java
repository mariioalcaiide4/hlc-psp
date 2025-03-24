package tema2.actividad3y4;

public class Hilos {
    public static void main(String[] args){

        if (args.length == 0){
            System.out.println("No se han introducido argumentos");
            return; // Si no hay archivos, salimos del programa
        }

        long t_comienzo = System.currentTimeMillis(); // Iniciar medición

        // Hacemos un array de hilos que mida igual de cuantos argumentos haya

        Thread[] hilos = new Thread[args.length];

        for (int i = 0; i < args.length; i++){
            String archivo = args[i];
            hilos[i] = new Thread(new ContadorCaracteres(archivo));
            hilos[i].start();
        }

        long t_final = System.currentTimeMillis();
        System.out.println("Tiempo total de ejecución: " + (t_final - t_comienzo) + "ms");

    }
}
