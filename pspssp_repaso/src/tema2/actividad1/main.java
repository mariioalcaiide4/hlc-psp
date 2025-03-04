package tema2.actividad1;

public class main {
    public static void main (String[] args){

        hilo1 h1 = new hilo1();
        hilo2 h2 = new hilo2();

        h1.start();
        h2.start();


    }
}
