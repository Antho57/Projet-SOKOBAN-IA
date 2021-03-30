package IA;

import InterfaceGraphique.VueInferieureLancementIA;

import javax.swing.*;
import java.awt.*;

public class LancementIA implements Runnable{

    private IA ia;
    private VueInferieureLancementIA vueInf;

    public LancementIA(IA ia, VueInferieureLancementIA v){
        this.ia = ia;
        this.vueInf = v;
    }

    @Override
    public void run() {
        double timeStart = System.currentTimeMillis();
        //lancement de la recherche de l'IA
        this.ia.chercherSolution();
        //temp à la fin de l'execution
        double timeEnd = System.currentTimeMillis();
        System.out.println("Temps d'éxécution : " + ((timeEnd - timeStart) / 1000) + " secondes");

        JPanel j = (JPanel) this.vueInf.getComponents()[2];
        for (Component c : j.getComponents()) {
            if (c instanceof JButton) {
                if (((JButton) c).getText().equals("Jouer")) {
                    c.setEnabled(true);
                }
            }
        }
    }
}
