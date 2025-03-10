package tema5.actividad3;

import java.io.File;
import java.io.FileOutputStream;
import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Claves {

    public static void main(String[] args) {

        try {
            // Generador de par de claves DSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(2048, numero); // Se inicializa el generador con 2048 bits

            // Generar el par de claves: PRIVADA y PÚBLICA
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavePriv = par.getPrivate();
            PublicKey clavePubl = par.getPublic();

            // Definir la ruta de almacenamiento
            String privateKeyPath = "src/actividad3/private.key";
            String publicKeyPath = "src/actividad3/public.key";

            // Asegurar que la carpeta exista antes de escribir los archivos
            crearDirectorioSiNoExiste("src/actividad3");

            // Guardar las claves en archivos
            guardarClave(clavePriv.getEncoded(), privateKeyPath);
            guardarClave(clavePubl.getEncoded(), publicKeyPath);

            // Proceso de firma con la clave privada
            String mensaje = "Este mensaje va a ser firmado";
            byte[] firma = firmarMensaje(mensaje, clavePriv);

            // Verificación de la firma con la clave pública
            boolean verificado = verificarFirma(mensaje, firma, clavePubl);

            if (verificado) {
                System.out.println("Firma verificada con clave pública");
            } else {
                System.out.println("Firma no verificada");
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un directorio si no existe.
     * @param ruta Ruta del directorio.
     */
    private static void crearDirectorioSiNoExiste(String ruta) {
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + ruta);
            } else {
                System.out.println("No se pudo crear el directorio: " + ruta);
            }
        }
    }

    /**
     * Guarda una clave en un archivo binario.
     * @param clave Clave en bytes.
     * @param ruta Ruta del archivo.
     * @throws IOException
     */
    private static void guardarClave(byte[] clave, String ruta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(clave);
            System.out.println("Clave guardada en: " + ruta);
        }
    }

    /**
     * Firma un mensaje con la clave privada.
     * @param mensaje Texto a firmar.
     * @param clavePriv Clave privada para firmar.
     * @return Firma en bytes.
     * @throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
     */
    private static byte[] firmarMensaje(String mensaje, PrivateKey clavePriv)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature dsa = Signature.getInstance("SHA256withDSA");
        dsa.initSign(clavePriv);
        dsa.update(mensaje.getBytes());
        return dsa.sign(); // Retorna la firma generada
    }

    /**
     * Verifica la firma de un mensaje usando la clave pública.
     * @param mensaje Texto original.
     * @param firma Firma en bytes.
     * @param clavePubl Clave pública para la verificación.
     * @return true si la firma es válida, false si no lo es.
     * @throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
     */
    private static boolean verificarFirma(String mensaje, byte[] firma, PublicKey clavePubl)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature verificaDSA = Signature.getInstance("SHA256withDSA");
        verificaDSA.initVerify(clavePubl);
        verificaDSA.update(mensaje.getBytes());
        return verificaDSA.verify(firma); // Retorna true si la firma es válida
    }
}

