package actividad2;

public class Asignatura {
    int idAsig;
    String nombreAsig;

    public Asignatura(int idAsig, String nombreAsig) {
        this.idAsig = idAsig;
        this.nombreAsig = nombreAsig;
    }

    public int getIdAsig() {
        return idAsig;
    }

    public String getNombreAsig() {
        return nombreAsig;
    }

    Asignatura psp = new Asignatura(1, "PSP");
    Asignatura adat = new Asignatura(2, "ADAT");
    Asignatura diu = new Asignatura(3, "DIU");

}