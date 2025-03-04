package actividad1;

public class CompartirInfo {
    public static void main (String[] args){

        GestionCuenta c = new GestionCuenta(40);
        Persona pepe = new Persona("Pepe", c);
        Persona loli = new Persona("Loli", c);
        pepe.start();
        loli.start();
    }
}
