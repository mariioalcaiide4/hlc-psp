package tema3.actividad9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class ClienteTCP extends JFrame {
    private JTextField campoTexto;
    private JTextArea areaRespuesta;
    private JButton btnEnviar, btnLimpiar, btnSalir;
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClienteTCP() {
        // Configuración de la ventana
        setTitle("Cliente TCP");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes
        campoTexto = new JTextField(20);
        areaRespuesta = new JTextArea(5, 30);
        areaRespuesta.setEditable(false);
        btnEnviar = new JButton("Enviar");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");

        // Agregar componentes
        add(new JLabel("Escribe texto:"));
        add(campoTexto);
        add(btnEnviar);
        add(btnLimpiar);
        add(btnSalir);
        add(new JScrollPane(areaRespuesta));

        // Conectar con el servidor
        try {
            socket = new Socket("127.0.0.1", 44444);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            areaRespuesta.setText("Error al conectar con el servidor.");
            btnEnviar.setEnabled(false);
        }

        // Acción botón "Enviar"
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = campoTexto.getText().trim();
                if (!mensaje.isEmpty()) {
                    salida.println(mensaje);
                    try {
                        String respuesta = entrada.readLine();
                        areaRespuesta.append("Respuesta: " + respuesta + "\n");
                    } catch (IOException ex) {
                        areaRespuesta.append("Error al recibir respuesta.\n");
                    }
                }
            }
        });

        // Acción botón "Limpiar"
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTexto.setText("");
                areaRespuesta.setText("");
            }
        });

        // Acción botón "Salir"
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salida.println("*");  // Enviar * para cerrar conexión
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ClienteTCP();
    }
}

