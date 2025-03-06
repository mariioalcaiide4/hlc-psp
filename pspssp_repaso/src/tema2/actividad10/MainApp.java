package tema2.actividad10;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class MainApp {
    public static void main(String[] args) {

        System.out.println("\n2ª Parte: Ping Pong\n");
        ColaxPing cola2 = new ColaxPing();
        ProductorXPing productorPing = new ProductorXPing(cola2,1);
        ConsumidorXPing  consumidorPing = new ConsumidorXPing(cola2,1);
        ConsumidorXPing  consumidorPing2 = new ConsumidorXPing(cola2,2);
        productorPing.start();
        consumidorPing.start();
        consumidorPing2.start();

    }
}