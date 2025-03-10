package tema5.actividad1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class md5 {

    public static void main(String[] args) {

        MessageDigest emd; // Declaración de la variable para la instancia de MessageDigest

        try {
            // Se obtiene una instancia del algoritmo MD5
            emd = MessageDigest.getInstance("MD5");

            // Texto a procesar
            String texto = "Paco Gelte Congana";

            // Se convierte el texto en un array de bytes
            byte[] dataBytes = texto.getBytes();

            // Se actualiza el objeto MessageDigest con los bytes del texto
            emd.update(dataBytes);

            // Se calcula el hash (resumen) del texto
            byte[] resumen = emd.digest();

            // Se imprimen datos informativos sobre el proceso
            System.out.println("Mensaje original: " + texto);
            System.out.println("Número de bytes del hash: " + emd.getDigestLength());
            System.out.println("Algoritmo utilizado: " + emd.getAlgorithm());

            // CORRECCIÓN: No se debe usar new String(resumen), ya que no es una conversión legible
            System.out.println("Mensaje resumen (hash en bytes): " + bytesToHex(resumen));

            // Se obtiene el proveedor del algoritmo
            Provider proveedor = emd.getProvider();
            System.out.println("Proveedor: " + proveedor.getName());

        } catch (NoSuchAlgorithmException e) {
            // Captura de excepción si el algoritmo no está disponible en el entorno
            e.printStackTrace();
        }
    }

    // Método para convertir un array de bytes a su representación hexadecimal
    static String bytesToHex(byte[] resumen) {
        StringBuilder hex = new StringBuilder(); // Se usa StringBuilder por eficiencia
        for (byte b : resumen) {
            String h = Integer.toHexString(b & 0xFF); // Se convierte cada byte a hexadecimal
            if (h.length() == 1) {
                hex.append("0"); // Si es de un solo carácter, se antepone un '0'
            }
            hex.append(h);
        }
        return hex.toString().toUpperCase(); // Se devuelve en mayúsculas
    }
}
