package actividad1;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class md5 {

    public static void main(String[] args) {

        MessageDigest emd;

        try {
            emd = MessageDigest.getInstance("MD5");
            String texto = "Esto es un texto plano.";

            byte[] dataBytes = texto.getBytes(); // Texto a Bytes
            emd.update(dataBytes); // Se introduce texto en bytes a resumir
            byte[] resumen = emd.digest(); // Se calcula el resumen

            System.out.println("Mensaje original: " + texto);
            System.out.println("Número de bytes: " + emd.getDigestLength());
            System.out.println("Algoritmo: " + emd.getAlgorithm());
            System.out.println("Mensaje resumen: " + new String(resumen));
            System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen));

            Provider proveedor = emd.getProvider();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    // Convierte un array de bytes a hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    }

}