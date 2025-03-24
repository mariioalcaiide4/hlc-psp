package tema2.actividad3y4;

import java.io.*;

public class Secuencial {
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("No se han introducido argumentos");
            return; // Si no hay archivos, salimos del programa
        }

        long t_comienzo = System.currentTimeMillis(); // Iniciar medición

        for (String archivo : args) { // Hacemos que el string archivo sean los argumentos del main (los archivos)
            File file = new File(archivo); // Declaramos el tipo file para los archivos
            if (!file.exists()) {
                System.out.println("El archivo " + archivo + " no existe."); // Hacemos un if para comprobar si el archivo existe
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int charCount = 0;
                int c;
                while ((c = reader.read()) != -1) {
                    charCount++;
                }
                System.out.println("Archivo: " + archivo + " - Caracteres: " + charCount);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + archivo);
                e.printStackTrace();
            }

            long t_fin = System.currentTimeMillis(); // Finalizar medición

            System.out.println("Tiempo total de ejecución: " + (t_fin - t_comienzo) + " ms");

        }

    /*
        Explicación paso a paso

        Verificamos si el usuario ha pasado archivos como argumentos.
                Si no hay argumentos, mostramos un mensaje de uso y terminamos el programa.

        Registramos el tiempo de inicio usando System.currentTimeMillis()
        Nos permitirá calcular cuánto tarda el programa en procesar todos los archivos.

        Recorremos los archivos que el usuario pasó como argumento.
                args es un arreglo de cadenas que contiene los nombres de los archivos.

        Verificamos si el archivo existe antes de intentar leerlo.
                Si no existe, mostramos un mensaje de error y pasamos al siguiente archivo con continue.

        Abrimos el archivo y leemos su contenido carácter por carácter.
        Usamos BufferedReader y FileReader para leer el archivo de manera eficiente.
                read() lee un solo carácter a la vez, y el bucle sigue hasta que llegue al final del archivo (-1).

                Imprimimos el número total de caracteres encontrados en cada archivo.

                Si ocurre un error al abrir el archivo (por ejemplo, si no tenemos permisos), capturamos la excepción y mostramos un mensaje.

        Cuando terminamos de procesar todos los archivos, medimos el tiempo de finalización.

        Calculamos el tiempo total de ejecución y lo mostramos en pantalla.


*/
    }
}
