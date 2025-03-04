package tema2.actividad4;

import java.util.Scanner;

public class main {
    public static void main (String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        MyHilo hilo1 = new MyHilo();
        hilo1.start();
        String opcion = "";

        do {
            System.out.println("Introduce una opción: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "S":
                    hilo1.Suspende();
                    System.out.println("Hilo suspendido");
                    break;
                case "R":
                    hilo1.Reanuda();
                    System.out.println("Hilo reanudado");
                    break;
                case "*":
                    hilo1.Detener();
                    hilo1.join();
                    System.out.println("Hilo detenido");
                    System.out.println("Contador final: " + hilo1.getContador());
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != "*");
    }
}
