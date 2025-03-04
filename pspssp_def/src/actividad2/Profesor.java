package actividad2;

public class Profesor {
    int id;
    String nombre;
    Asignatura[] asignaturas;
    Especialidad espe;

    public Profesor(int id, String nombre, Asignatura asignaturas, Especialidad espe) {
        this.id = id;
        this.nombre = nombre;
        this.asignaturas = new Asignatura[]{asignaturas};
        this.espe = espe;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Asignatura[] getAsignaturas(){
        return asignaturas;
    }

    public Especialidad getEspecialidad(){
        return espe;
    }

    //Profesor profe1 = new Profesor(1, "pepe", asignaturas[3]);


}



