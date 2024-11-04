package Actividad3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HiloContador extends Applet implements ActionListener {
    //Declaramos los objetos
    private Thread hilo;
    long contador;
    private Font fuente;
    private boolean parar;
    private Button boton1, boton2;

    public void start(){}

    public void init(){
        setBackground(Color.yellow);
        add(boton1=new Button("Iniciar contador"));
        boton1.addActionListener(this);
        add(boton2=new Button("Finalizar contador"));
        boton2.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);
    }


    public void run() {
        parar = false;
        while (!parar){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            contador++;
            }

        }

    public void paint(Graphics g){
        g.clearRect(0,0,400,400);
        g.setFont(fuente);
        g.drawString(Long.toString(contador), 80, 100);
    }

    //Controles de los botones

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == boton1) //Pulso Iniciar
    {
    boton1.setLabel("Iniciar contador");
    if(hilo != null && hilo.isAlive()){}
    //Si el hilo esta funcionando no hago nago nada
    else {
        hilo = new Thread(String.valueOf(this));
        hilo.start();
     }
    } else if(e.getSource() == boton2) //Pulso Parar
        parar=true;
    }

    public void stop(){
        hilo = null;
    }


}