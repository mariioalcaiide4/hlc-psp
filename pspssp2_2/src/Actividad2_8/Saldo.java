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

    public void sacarDinero(int cantidad){

    }

    public void meterDinero(){

    }

}

