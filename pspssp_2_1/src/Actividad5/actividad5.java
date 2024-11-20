package Actividad5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad5 extends JFrame implements ActionListener {
    // Clase interna para el hilo del contador
    class HiloContador extends Thread {
        private boolean parar;
        private boolean pausado;
        long contador = 0;

        public HiloContador() {
            parar = false;
            pausado = false;
        }

        @Override
        public void run() {
            while (!parar) {
                synchronized (this) {
                    while (pausado) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
                repaint();
            }
        }

        public synchronized void pausar() {
            pausado = true;
        }

        public synchronized void reanudar() {
            pausado = false;
            notify();
        }

        public void detener() {
            parar = true;
        }

        public long getContador() {
            return contador;
        }

        public boolean isPausado() {
            return pausado;
        }
    }

    private HiloContador hilo1;
    private HiloContador hilo2;

    private Font fuente;
    private JButton b1, b2;

    public actividad5() {
        setTitle("Actividad 2_5 - Hilos en JFrame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.yellow);

        // Inicializar botones
        b1 = new JButton("Finalizar hilo 1");
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Finalizar hilo 2");
        b2.addActionListener(this);
        add(b2);

        fuente = new Font("Serif", Font.BOLD, 26);

        // Iniciar los hilos
        hilo1 = new HiloContador();
        hilo2 = new HiloContador();
        hilo1.start();
        hilo2.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(fuente);
        g.drawString("Contador Hilo 1: " + hilo1.contador, 20, 100);
        g.drawString("Contador Hilo 2: " + hilo2.contador, 20, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (!hilo1.isPausado()) {
                hilo1.pausar();
                b1.setText("Reanudar hilo 1");
            } else {
                hilo1.reanudar();
                b1.setText("Finalizar hilo 1");
            }
        } else if (e.getSource() == b2) {
            if (!hilo2.isPausado()) {
                hilo2.pausar();
                b2.setText("Reanudar hilo 2");
            } else {
                hilo2.reanudar();
                b2.setText("Finalizar hilo 2");
            }
        }
    }

    public void stop() {
        if (hilo1 != null) {
            hilo1.detener();
        }
        if (hilo2 != null) {
            hilo2.detener();
        }
        hilo1 = null;
        hilo2 = null;
    }

    public static void main(String[] args) {
        actividad5 frame = new actividad5();
        frame.setVisible(true);
    }
}
