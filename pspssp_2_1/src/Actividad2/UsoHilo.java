package Actividad2;

public class UsoHilo {
    public static void main(String[] args) {

        //Primer hilo
        HiloNuevo hilo1 = new HiloNuevo();
        new Thread(hilo1).start();

        //Segundo hilo
        HiloNuevo hilo2 = new HiloNuevo();
        new Thread(hilo2).start();

        //Tercer hilo
        HiloNuevo hilo3 = new HiloNuevo();
        new Thread(hilo3).start();

        //Cuarto hilo
        HiloNuevo hilo4 = new HiloNuevo();
        new Thread(hilo4).start();

        //Quinto hilo
        HiloNuevo hilo5 = new HiloNuevo();
        new Thread(hilo5).start();
    }
}
