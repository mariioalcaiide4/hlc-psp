package tema2.actividad7;

import java.util.Random;

public class Persona extends Thread {
    private GestionCuenta cuenta;
    String persona;

    public Persona(String n, GestionCuenta cuenta){
        super(n);
        this.cuenta = cuenta;
    }

    public void run() {
        cuenta.añadirCantidad(new Random().nextInt(500), getName());
        cuenta.retirarDinero(new Random().nextInt(500), getName());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cuenta.añadirCantidad(new Random().nextInt(500), getName());
        cuenta.retirarDinero(new Random().nextInt(500), getName());
    }


}
