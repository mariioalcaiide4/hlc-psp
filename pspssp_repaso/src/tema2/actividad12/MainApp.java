package tema2.actividad12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private int x = 1; // Posición inicial en x
    private int y = 100; // Posición constante en y
    private int direccion = 1; // 1 para derecha, -1 para izquierda
    private boolean enMovimiento = true; // Control del hilo
    private JButton btnControl;
    private HiloMovimiento hilo;

    public MainApp() {
        // Configuración del JFrame
        setTitle("Movimiento de una Letra");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.PINK);
        setLocationRelativeTo(null);
        setLayout(null);

        // Configurar fondo rosa
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("?", x, y); // Dibujar la letra
            }
        };
        fondo.setBackground(Color.PINK);
        fondo.setLayout(null);
        setContentPane(fondo);

        // Crear el botón
        btnControl = new JButton("Finalizar Hilo");
        btnControl.setBounds(150, 130, 120, 30);
        btnControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enMovimiento) {
                    enMovimiento = false;
                    hilo.pausar();
                    btnControl.setText("Reanudar Hilo");
                } else {
                    enMovimiento = true;
                    hilo.reanudar();
                    btnControl.setText("Finalizar Hilo");
                }
            }
        });
        add(btnControl);

        // Crear y empezar el hilo
        hilo = new HiloMovimiento();
        hilo.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("?", x, y);
    }

    private class HiloMovimiento extends Thread {
        private boolean pausado = false;

        public synchronized void pausar() {
            pausado = true;
        }

        public synchronized void reanudar() {
            pausado = false;
            notify();
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (pausado) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                x += direccion * 5; // Actualizar posición

                // Detectar bordes
                if (x >= getWidth() - 15) { // Rebote a la izquierda (-15 para que rebote cuando toque el extremo derecho de la letra)
                    direccion = -1;
                } else if (x <= 1) { // Rebote a la derecha
                    direccion = 1;
                }

                // Repaint para actualizar la letra
                repaint();

                try {
                    Thread.sleep(50); // Control de velocidad
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MainApp ventana = new MainApp();
        ventana.setVisible(true);
    }
}