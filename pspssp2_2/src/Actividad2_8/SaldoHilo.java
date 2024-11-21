package Actividad2_8;

public class SaldoHilo extends Thread{
    static Saldo saldo;
    private int cantidad;

    public SaldoHilo(Saldo saldo, String nombre, int cantidad) {
        super(nombre);
        this.cantidad = cantidad;
        this.saldo = saldo;

    public void run(){
        saldo.ingresarDinero();
        }



    }
}
