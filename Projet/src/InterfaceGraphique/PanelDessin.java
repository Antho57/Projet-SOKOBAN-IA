package InterfaceGraphique;

import Jeu.Labyrinthe;

import javax.swing.*;

public class PanelDessin extends JPanel {

    private Labyrinthe lab;

    public PanelDessin(Labyrinthe l){
        this.lab = l;
    }
}
