package tema2.actividad5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad5 extends JFrame implements ActionListener {

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

        // Personalizar JFrame, título, tamaño, cierre, layout y color de fondo

        setTitle("Actividad 5 - Hilos en JFrame");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBackground(Color.CYAN);

        // Crear botones y añadirlos al JFrame

        b1 = new JButton("Finalizar hilo 1");
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Finalizar hilo 2");
        b2.addActionListener(this);
        add(b2);

        // Fuente del JFrame

        fuente = new Font("Verdana", Font.BOLD, 26);

        // Iniciar los hilos

        hilo1 = new HiloContador();
        hilo2 = new HiloContador();
        hilo1.start();
        hilo2.start();
    }

    // Pintar los contadores en el JFrame

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(fuente);
        g.drawString("Hilo1: " + hilo1.contador, 20, 100);
        g.drawString("Hilo2: " + hilo2.contador, 20, 100);
    }

    //Darle acción a los botones

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


    //Ejecutar el JFrame

    public static void main(String[] args) {
        tema2.actividad5.actividad5 frame = new tema2.actividad5.actividad5();
        frame.setVisible(true);
    }
}
