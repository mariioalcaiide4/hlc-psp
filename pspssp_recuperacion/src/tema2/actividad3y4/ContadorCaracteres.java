package tema2.actividad3y4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContadorCaracteres implements Runnable{
    private final String archivo;

    public ContadorCaracteres(String archivo){
        this.archivo = archivo;
    }

    @Override
    public void run() {

        File file = new File(archivo); // Declaramos el tipo file para los archivos
        if (!file.exists()) {
            System.out.println("El archivo " + archivo + " no existe."); // Hacemos un if para comprobar si el archivo existe
            return;
        }

        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int charCount = 0;
            int c;
            while ((c = reader.read()) != -1) {
                charCount++;
            }
            System.out.println("Archivo: " + archivo + " - Caracteres: " + charCount);
        } catch (
                IOException e) {
            System.out.println("Error al leer el archivo: " + archivo);
            e.printStackTrace();
        }
    }
}
