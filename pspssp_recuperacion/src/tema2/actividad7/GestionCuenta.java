//Esta actividad la he planteado de manera muy parecida a una que hicimos durante el segundo tema
// y la unica modificación ha sido la del saldo límite y lo he solucionado con una variable y un if
//El código funciona correctamente


package tema2.actividad7;

import java.util.Random;

public class GestionCuenta {
    private int saldo = 40; // Atributo saldo
    private int saldo_limite = 700;

    // Constructor que asigna un valor inicial al saldo
    public GestionCuenta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método para obtener el saldo
    public synchronized double obtenerSaldo() {
        try {
            Thread.sleep(new Random().nextInt(1000)); // Sleep aleatorio entre 0 y 1000 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return saldo;
    }
    // Método para restar el saldo de la cuenta
    public synchronized void restar(int cantidad) {
        try {
            Thread.sleep(new Random().nextInt(1000)); // Sleep aleatorio entre 0 y 1000 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        saldo = saldo - cantidad;
    }

    // Método para restablecer el nuevo valor del saldo
    public synchronized void restablecerSaldo(int nuevoSaldo) {
        try {
            Thread.sleep(new Random().nextInt(1000)); // Sleep aleatorio entre 0 y 1000 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.saldo = nuevoSaldo;
    }

    // Método para añadir una cantidad al saldo
    public synchronized void añadirCantidad(int cantidad, String persona) {
        if (saldo < saldo_limite) {
            double saldoAntes = this.saldo;
            this.saldo += cantidad;
            double saldoDespues = this.saldo;
            System.out.println(persona + " añade " + cantidad + " al saldo.");
            System.out.println("Saldo antes: " + saldoAntes + ", saldo después: " + saldoDespues);
        }
    }

    //Método para retirar la cantidad de la cuenta
    public synchronized void retirarDinero(int cantidad, String persona){
        if (obtenerSaldo() >=cantidad){
            System.out.println(persona + ": SE VA A RETIRAR SALDO (ACTUAL ES: " + obtenerSaldo() + ")");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){}

        restar(cantidad);

        System.out.println("\t" + persona + " retira => " + cantidad + " ACTUAL(" + obtenerSaldo() + ")");

        } else {
            System.out.println(persona + " NO PUEDE RETIRAR DINERO, NO HAY SALDO (" + obtenerSaldo() + ")");
        }
        if (obtenerSaldo() < 0) {
            System.out.println("SALDO NEGATIVO " + obtenerSaldo());
        }
    }
}
