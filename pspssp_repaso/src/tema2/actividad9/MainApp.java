package tema2.actividad9;

public class MainApp {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Productor p=new Productor(cola,1);
        Consumidor c=new Consumidor(cola,1);
        p.start();
        c.start();
    }
}

