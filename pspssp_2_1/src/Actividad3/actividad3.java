package Actividad3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad3 extends JFrame implements ActionListener {
    private Thread hilo1, hilo2;
    long CONTADOR1 = 0;
    long CONTADOR2 = 0;
    private boolean pararHilo1, pararHilo2;
    private Font fuente;
    private JButton b1, b2;

    public actividad3() {
        setTitle("Actividad 3 - Hilos en JFrame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBackground(Color.pink);

        b1 = new JButton("Finalizar hilo 1");
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Finalizar hilo 2");
        b2.addActionListener(this);
        add(b2);

        fuente = new Font("Verdana", Font.BOLD, 26);

        // Inicializamos los hilos
        hilo1 = new Thread(new Runnable() {
            public void run() {
                while (!pararHilo1) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CONTADOR1++;
                    repaint();
                }
            }
        });

        hilo2 = new Thread(new Runnable() {
            public void run() {
                while (!pararHilo2) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CONTADOR2++;
                    repaint();
                }
            }
        });

        // Iniciar ambos hilos al comenzar el JFrame
        start();
    }

    public void start() {
        pararHilo1 = false;
        pararHilo2 = false;
        if (!hilo1.isAlive()) {
            hilo1.start();
        }
        if (!hilo2.isAlive()) {
            hilo2.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(fuente);
        g.drawString("Hilo1: " + Long.toString(CONTADOR1), 80, 150);
        g.drawString("Hilo2: " + Long.toString(CONTADOR2), 80, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (!pararHilo1) {
                pararHilo1 = true;
                b1.setText("Reanudar hilo 1");
            } else {
                pararHilo1 = false;
                b1.setText("Finalizar hilo 1");
                if (!hilo1.isAlive()) {
                    hilo1 = new Thread(new Runnable() {
                        public void run() {
                            while (!pararHilo1) {
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                CONTADOR1++;
                                repaint();
                            }
                        }
                    });
                    hilo1.start();
                }
            }
        }
        if (e.getSource() == b2) {
            if (!pararHilo2) {
                pararHilo2 = true;
                b2.setText("Reanudar hilo 2");
            } else {
                pararHilo2 = false;
                b2.setText("Finalizar hilo 2");
                if (!hilo2.isAlive()) {
                    hilo2 = new Thread(new Runnable() {
                        public void run() {
                            while (!pararHilo2) {
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                CONTADOR2++;
                                repaint();
                            }
                        }
                    });
                    hilo2.start();
                }
            }
        }
    }

    public void stop() {
        // Parar ambos hilos cuando se cierre el JFrame
        pararHilo1 = true;
        pararHilo2 = true;
    }

    public static void main(String[] args) {
        actividad3 frame = new actividad3();
        frame.setVisible(true);
    }
}
