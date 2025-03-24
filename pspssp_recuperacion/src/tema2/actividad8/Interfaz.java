package tema2.actividad8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame implements ActionListener {

    private Thread cont1, cont2;
    long contador1 = 0;
    long contador2 = 0;
    private boolean suspender1, suspender2;
    private boolean reanudar1, reanudar2;
    private boolean comenzar, finalizar;
    private Font fuente;
    private JButton stop1, stop2;
    private JButton continue1, continue2;
    private JButton start, destroy;

    public Interfaz(){

        // Personalización del JFrame

        setTitle("Actividad 8 - Parar, Suspender y Detener");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.gray);

        start = new JButton("Comenzar Proceso");
        start.addActionListener(this);
        add(start);

        stop1 = new JButton("Suspender Hilo");
        stop1.addActionListener(this);
        add(stop1);

        continue1 = new JButton("Reanudar Hilo");
        continue1.addActionListener(this);
        add(continue1);

        stop2 = new JButton("Suspender Hilo");
        stop2.addActionListener(this);
        add(stop2);

        continue2 = new JButton("Reanudar Hilo");
        continue2.addActionListener(this);
        add(continue2);

        destroy = new JButton("Finalizar Proceso");
        destroy.addActionListener(this);
        add(destroy);

        fuente = new Font("Verdana", Font.BOLD, 30);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(fuente);
        g.drawString("Hilo 1: " + contador1, 80, 150);
        g.drawString("Hilo 2: " + contador2, 80, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if ()



    }

}
