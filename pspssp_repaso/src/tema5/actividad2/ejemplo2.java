package tema5.actividad2;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

public class ejemplo2 {

    public static void main(String[] args) {

        try {
            FileInputStream in = new FileInputStream("src/Act_02/datos.dat");
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();

            // Primera lectura, se obtiene el String
            String datos = (String) o;
            System.out.println("Datos: " + datos);

            // Segunda lectura
            o = ois.readObject();
            byte[] resumenOriginal = (byte[]) o;

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Se calcula el resumen del String leído del fichero
            md.update(datos.getBytes()); // Texto a resumir
            byte[] resumenActual = md.digest(); // Se calcula el resumen

            // Se comprueban los dos resúmenes
            if (MessageDigest.isEqual(resumenOriginal, resumenActual)) {
                System.out.println("Datos válidos");
            } else {
                System.out.println("Datos no válidos");
            }

            ois.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}