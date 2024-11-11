package Actividad2_7;

public class hilo2ejemplo extends Thread{
    private hilo_contador contador;

    public hilo2ejemplo(String n, hilo_contador c){
        setName(n);
        contador = c;
    }

    public void run() {
        synchronized (contador) {

            for (int k = 0; k < 300; k++) {
                contador.decrementa(); //el contador incrementa
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                }
            }
            System.out.println(getName() + " contador vale " + contador.getC());
        }
    }
}



