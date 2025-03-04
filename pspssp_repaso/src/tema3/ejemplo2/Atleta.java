package tema3.ejemplo2;

import java.io.Serializable;

public class Atleta implements Serializable {

    String nombre_completo;
    int edad;
    int trofeos;
    Deporte deporte;

    public Atleta(String nombre_completo, int edad, int trofeos, Deporte deporte) {
        this.nombre_completo = nombre_completo;
        this.edad = edad;
        this.trofeos = trofeos;
        this.deporte = deporte;

    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo){
       this.nombre_completo = nombre_completo;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public int getTrofeos(){
        return trofeos;
    }

    public void setTrofeos(int trofeos){
        this.trofeos = trofeos;
    }

    public Deporte getDeporte(){
        return deporte;
    }

    public void setDeporte(Deporte deporte){
        this.deporte = deporte;
    }

    @Override
    public String toString(){
        return "Nombre Completo " + nombre_completo + ", Edad " + edad + ", Trofeos " + trofeos + ", Deporte " + deporte;
    }

}
