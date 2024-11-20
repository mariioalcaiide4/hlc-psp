package Actividad2_8;

public class Saldo {

    private int saldo_actual = 0;

    //Constructor

    Saldo(){saldo_actual = 0;}

    //Getter

    int getSaldo(){return saldo_actual;}

    //Métodos

    void restar(int cantidad){saldo_actual = saldo_actual - cantidad;}
    void sumar(int cantidad){saldo_actual = saldo_actual + cantidad;}

    public void sacarDinero(int cant, String nom){
        if (getSaldo() >= cant){
            System.out.println(nom + ": SE VA A RETIRAR SALDO (ACTUAL ES: " + getSaldo() + ")");
            try {
                    Thread.sleep(500);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            } restar(cant); //resta la cantidad al saldo

            System.out.println("\t" + nom + " retira => " + cant + " ACTUAL(" + getSaldo() + ")");
        }   else {
            System.out.println("No se puede sacar dinero, NO HAY SALDO ("+ getSaldo() + ")");
        }
        if (getSaldo() < 0){
            System.out.println("SALDO NEGATIVO => " + getSaldo() + ")");
        }
    }

    public void ingresarDinero(int cant, String nom){
        System.out.println(nom + ": SE VA A INGRESAR SALDO (ACTUAL ES: " + getSaldo() + ")");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        } sumar(cant); //suma la cantidad al saldo
        System.out.println("\t" + nom + " ingresa => " + cant + " ACTUAL(" + getSaldo() + ")");
    }
}

