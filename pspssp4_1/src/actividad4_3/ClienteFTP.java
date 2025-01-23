package actividad4_3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClienteFTP {

    private static FTPClient ftpClient = new FTPClient();

    public static void main(String[] args) throws IOException {

        // Crear ventana principal
        JFrame frame = new JFrame("DESCARGAR FICHEROS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.GREEN); // Fondo del JFrame

        // Panel para ingresar usuario y contraseña
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false); // Fondo transparente para que tome el color del JFrame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen interno
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField("mario", 15); // Usuario predefinido
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField("mario", 15); // Contraseña predefinida
        JButton connectButton = new JButton("Conectar");

        // Personalizar botones
        connectButton.setBackground(new Color(173, 216, 230)); // Azul clarito
        connectButton.setOpaque(true);
        connectButton.setBorderPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(connectButton, gbc);

        frame.add(loginPanel, BorderLayout.NORTH);

        // Lista de archivos y botones
        DefaultListModel<String> fileListModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(fileListModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        JButton downloadButton = new JButton("Descargar");
        JButton exitButton = new JButton("Salir");

        // Personalizar botones
        downloadButton.setBackground(new Color(173, 216, 230)); // Azul clarito
        downloadButton.setOpaque(true);
        downloadButton.setBorderPainted(false);

        exitButton.setBackground(new Color(173, 216, 230)); // Azul clarito
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Fondo transparente para que tome el color del JFrame
        buttonPanel.add(downloadButton);
        buttonPanel.add(exitButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Acción para conectarse al servidor FTP
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = new String(passField.getPassword());
                try {
                    ftpClient.connect("localhost", 21); // Dirección y puerto del servidor FTP
                    boolean login = ftpClient.login(user, password);
                    ftpClient.changeWorkingDirectory("/home/usuario");

                    if (login) {
                        JOptionPane.showMessageDialog(frame, "Conexión exitosa.");
                        ftpClient.enterLocalPassiveMode();

                        // Listar archivos disponibles
                        fileListModel.clear();
                        for (String file : ftpClient.listNames()) {
                            fileListModel.addElement(file);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para descargar un archivo
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = fileList.getSelectedValue();
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(frame, "Seleccione un archivo para descargar.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Seleccionar directorio de destino
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File directory = fileChooser.getSelectedFile();
                    File localFile = new File(directory, selectedFile);

                    try (FileOutputStream fos = new FileOutputStream(localFile)) {
                        boolean success = ftpClient.retrieveFile(selectedFile, fos);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Archivo descargado correctamente en: " + localFile.getAbsolutePath());
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error al descargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para salir
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
