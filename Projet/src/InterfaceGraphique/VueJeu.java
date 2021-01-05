package InterfaceGraphique;

import Jeu.Labyrinthe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueJeu extends JPanel {

    private Labyrinthe lab;

    private Image sol, mur, emplacement, joueur, caisse;

    private Image[] sprites;

    public VueJeu(Labyrinthe l){

        this.lab = l;

        try{
            this.sol = ImageIO.read(new File("Ressources/Images/sol.png"));
            this.mur = ImageIO.read(new File("Ressources/Images/mur.png"));
            this.emplacement = ImageIO.read(new File("Ressources/Images/emplacement.png"));
            this.joueur = ImageIO.read(new File("Ressources/Images/joueur.png"));
            this.caisse = ImageIO.read(new File("Ressources/Images/caisse.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void MAJ(String dep){
        this.lab.move(dep);
        this.repaint();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

    }


}
