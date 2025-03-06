package tema3.actividad10;

public class ObjetoCompartido {
    private int numero;   // Número a adivinar
    private boolean acabo; // True cuando el juego ha terminado
    private int ganador;  // Jugador ganador

    public ObjetoCompartido(int numero) {
        this.numero = numero; // Número a adivinar
        this.acabo = false;
    }

    public boolean seAcabo() {
        return acabo;
    }

    public int getGanador() {
        return ganador;
    }

    public synchronized String nuevaJugada(int jugador, int suNumero) {
        String cad = "";

        if (!acabo) {
            if (suNumero > numero) {  // Demasiado grande
                cad = "Número demasiado grande";
            } else if (suNumero < numero) {  // Demasiado pequeño
                cad = "Número demasiado bajo";
            } else if (suNumero == numero) {  // Número correcto
                cad = "Jugador " + jugador + " gana, adivinó el número: " + numero;
                acabo = true;
                ganador = jugador;
            }
        } else {
            cad = "Jugador " + ganador + " adivinó el número: " + numero;
        }

        return cad;
    }
} // Fin de ObjetoCompartido

