package Actividad2_7;

public class hilo_contador {
    private int c = 0;
    hilo_contador(int c){this.c = c;}

    public void incrementa(){
        c = c + 1;
    }

    public void decrementa(){
        c = c - 1;
    }

    public int getC(){
        return c;
    }

}
