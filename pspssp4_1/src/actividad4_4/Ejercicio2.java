package actividad4_4;

import org.apache.commons.net.smtp.SMTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

public class Ejercicio2 extends JFrame {
    private JTextField serverField, portField, userField, fromField, toField, subjectField;
    private JPasswordField passwordField;
    private JTextArea messageArea;
    private JRadioButton tlsYes, tlsNo;
    private JButton sendButton;

    public Ejercicio2() {
        setTitle("Cliente SMTP Básico");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        add(new JLabel("Servidor SMTP:"));
        serverField = new JTextField();
        add(serverField);

        add(new JLabel("Puerto:"));
        portField = new JTextField();
        add(portField);

        add(new JLabel("Usuario:"));
        userField = new JTextField();
        add(userField);

        add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Remitente:"));
        fromField = new JTextField();
        add(fromField);

        add(new JLabel("Destinatario:"));
        toField = new JTextField();
        add(toField);

        add(new JLabel("Asunto:"));
        subjectField = new JTextField();
        add(subjectField);

        add(new JLabel("Mensaje:"));
        messageArea = new JTextArea();
        add(messageArea);

        tlsYes = new JRadioButton("Con TLS");
        tlsNo = new JRadioButton("Sin TLS", true);
        ButtonGroup tlsGroup = new ButtonGroup();
        tlsGroup.add(tlsYes);
        tlsGroup.add(tlsNo);
        add(tlsYes);
        add(tlsNo);

        sendButton = new JButton("Enviar mensaje");
        add(sendButton);
        sendButton.addActionListener(new SendEmailAction());
    }

    private class SendEmailAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String host = serverField.getText();
            int port = Integer.parseInt(portField.getText());
            String user = userField.getText();
            String password = new String(passwordField.getPassword());
            String from = fromField.getText();
            String to = toField.getText();
            String subject = subjectField.getText();
            String messageText = messageArea.getText();

            try (Socket socket = new Socket(host, port);
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                writer.println("EHLO " + host);
                writer.println("AUTH LOGIN");
                writer.println(Base64.getEncoder().encodeToString(user.getBytes()));
                writer.println(Base64.getEncoder().encodeToString(password.getBytes()));
                writer.println("MAIL FROM: " + from);
                writer.println("RCPT TO: " + to);
                writer.println("DATA");
                writer.println("Subject: " + subject);
                writer.println("\r\n" + messageText);
                writer.println(".");
                writer.println("QUIT");

                JOptionPane.showMessageDialog(null, "Mensaje enviado con éxito");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al enviar el mensaje", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SMTPClient client = new SMTPClient(); // Crear una instancia
        });
    }
}
