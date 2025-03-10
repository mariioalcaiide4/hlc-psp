package tema5.actividad1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Scanner;

public class ShaExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Scanner para entrada de usuario

        try {
            // Pedir los textos y la clave al usuario
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
            sha256.reset(); // Asegurar que el digest está limpio
            sha256.update(combinedText1.getBytes()); // Pasar los bytes al algoritmo
            byte[] hash1 = sha256.digest(); // Generar el hash

            System.out.println("=========== TEXTO 1 ==========");
            System.out.println("Texto original: " + input1);
            // CORRECCIÓN: No se debe usar new String(hash1) porque no es legible
            System.out.println("Hash en hexadecimal: " + convertToHex(hash1));

            // PROCESAR TEXTO 2
            sha256.reset(); // Restablecer antes de calcular otro hash
            sha256.update(combinedText2.getBytes()); // Pasar los bytes al algoritmo
            byte[] hash2 = sha256.digest(); // Generar el hash

            System.out.println("=========== TEXTO 2 ==========");
            System.out.println("Texto original: " + input2);
            // CORRECCIÓN: Se muestra el hash correctamente en hexadecimal
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
