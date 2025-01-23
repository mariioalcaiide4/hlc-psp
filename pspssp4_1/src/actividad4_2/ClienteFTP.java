package actividad4_2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ClienteFTP {

    public static void main(String[] args) {
        // Configuración del servidor FTP
        String servidor = "localhost";
        int puerto = 21;
        String usuario = "mario";
        String contraseña = "mario";
        String directorioExterno = "/home/usuario/";

        // Crear JFileChooser para seleccionar el archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un fichero para subir al servidor FTP");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Conexión al servidor FTP
            FTPClient cliente = new FTPClient();
            try {
                cliente.connect(servidor, puerto);
                boolean login = cliente.login(usuario, contraseña);

                if (login) {
                    cliente.enterLocalPassiveMode(); // Establecer modo pasivo
                    cliente.setFileType(FTP.BINARY_FILE_TYPE); // Configurar el tipo de archivo (binario)

                    // Cambiar al directorio remoto
                    if (!cliente.changeWorkingDirectory(directorioExterno)) {
                        // Si no existe el directorio, crear uno
                        if (cliente.makeDirectory(directorioExterno)) {
                            cliente.changeWorkingDirectory(directorioExterno);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al acceder o crear el directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    // Subir el archivo seleccionado
                    String remoteFile = selectedFile.getName(); // El nombre del archivo remoto será el mismo que el archivo local
                    try (FileInputStream inputStream = new FileInputStream(selectedFile)) {
                        boolean done = cliente.storeFile(remoteFile, inputStream); // Subir el archivo
                        if (done) {
                            JOptionPane.showMessageDialog(null, remoteFile + " se subió correctamente.");

                            // Listar el contenido del directorio actual del servidor FTP
                            System.out.println("Contenido del directorio actual en el servidor FTP:");
                            for (String file : cliente.listNames()) {
                                System.out.println(file);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al subir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión en el servidor FTP.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                cliente.logout(); // Cerrar sesión después de la carga
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                try {
                    if (cliente.isConnected()) {
                        cliente.disconnect(); // Desconectar al finalizar
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
    }
}