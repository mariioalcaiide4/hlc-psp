package actividad1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Scanner;

public class ShaExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Escribe el primer texto: ");
            String input1 = scanner.nextLine();

            System.out.println("Escribe el segundo texto: ");
            String input2 = scanner.nextLine();

            System.out.println("Escribe una clave para mezclar con los textos: ");
            String key = scanner.next();

            // Concatenamos los textos con la clave
            String combinedText1 = input1 + key;
            String combinedText2 = input2 + key;

            // Crear el objeto MessageDigest para usar SHA-256
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // PROCESAR TEXTO 1
            byte[] textBytes1 = combinedText1.getBytes(); // Convertir el texto en bytes
            sha256.update(textBytes1); // Pasar los bytes al algoritmo SHA-256
            byte[] hash1 = sha256.digest(); // Generar el hash

            System.out.println("=========== TEXTO 1 ==========");
            System.out.println("Texto original: " + input1);
            System.out.println("Hash en bytes (no legible): " + new String(hash1));
            System.out.println("Hash en hexadecimal: " + convertToHex(hash1));

            // Reiniciar el objeto para calcular otro hash
            sha256.reset();

            // PROCESAR TEXTO 2
            byte[] textBytes2 = combinedText2.getBytes(); // Convertir el texto en bytes
            sha256.update(textBytes2); // Pasar los bytes al algoritmo SHA-256
            byte[] hash2 = sha256.digest(); // Generar el hash

            System.out.println("=========== TEXTO 2 ==========");
            System.out.println("Texto original: " + input2);
            System.out.println("Hash en bytes (no legible): " + new String(hash2));
            System.out.println("Hash en hexadecimal: " + convertToHex(hash2));

            // Mostrar el proveedor del algoritmo usado
            Provider provider = sha256.getProvider();
            System.out.println("Proveedor del algoritmo: " + provider.getName());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Cerrar el Scanner para evitar problemas de memoria
        }
    }

    // Convierte los bytes generados en el hash a un formato legible (hexadecimal)
    static String convertToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(b & 0xFF); // Convertir byte a hexadecimal
            if (hex.length() == 1) {
                hexString.append("0"); // Asegurar que tenga 2 caracteres
            }
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase(); // Convertir a mayúsculas
    }
}