package Actividad2_6;

public class hiloprioritario extends Thread {

    private int c = 0;
    private boolean stopHilo = false;

    public hiloprioritario(String nombre) {
        super(nombre);
    }
    public int getContador(){
        return c;
    }
    public void pararHilo(){
        stopHilo = true;
    }
    public void run(){
        while(!stopHilo){
            try {
                Thread.sleep(20);
            }catch(Exception e){}
            c++;
        }
        System.out.println("Fin hilo" +this.getName());
    }
}
