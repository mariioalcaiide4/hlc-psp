package tema3.ejemplo2;

import java.io.Serializable;

public class Deporte implements Serializable {
    private String nombre;
    private String categoria; // Ejemplo: Individual, Equipo
    private String origen; // País de origen del deporte
    private boolean olimpico;

    public Deporte(String nombre, String categoria, String origen, boolean olimpico) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.origen = origen;
        this.olimpico = olimpico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public boolean getOlimpico() {
        return olimpico;
    }

    public void setOlimpico(boolean olimpico) {
        this.olimpico = olimpico;
    }

    @Override
    public String toString() {
        return "Deporte: " + nombre + ", Categoría: " + categoria + ", Origen: " + origen + ", Olímpico: " + olimpico;
    }
}


