package tema3.actividad10;

import java.io.Serializable;

public class Datos implements Serializable {
    private String cadena;  // Mensaje intercambiado con el servidor
    private int intentos;   // Número de intentos realizados
    private int identificador;  // ID del jugador
    private boolean gana;  // True si el jugador gana
    private boolean juega; // True si el jugador sigue jugando, false si finaliza

    public Datos(String cadena, int intentos, int identificador) {
        this.cadena = cadena;
        this.intentos = intentos;
        this.identificador = identificador;
        this.gana = false;
        this.juega = true;
    }

    public Datos() {
        super();
    }

    // Métodos Get y Set de los atributos
    public String getCadena() { return cadena; }
    public void setCadena(String cadena) { this.cadena = cadena; }

    public int getIntentos() { return intentos; }
    public void setIntentos(int intentos) { this.intentos = intentos; }

    public int getIdentificador() { return identificador; }
    public void setIdentificador(int identificador) { this.identificador = identificador; }

    public boolean isGana() { return gana; }
    public void setGana(boolean gana) { this.gana = gana; }

    public boolean isJuega() { return juega; }
    public void setJuega(boolean juega) { this.juega = juega; }
} // Fin de Datos

