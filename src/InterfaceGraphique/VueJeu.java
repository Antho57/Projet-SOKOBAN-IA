package InterfaceGraphique;

import Jeu.Caisse;
import Jeu.Labyrinthe;
import Jeu.Mur;
import Jeu.Sujet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VueJeu extends JPanel implements Observateur{

    private Labyrinthe lab;

    private Image sol, mur, emplacement, joueur, caisse, caissev;

    public VueJeu(Labyrinthe l){
        this.lab = l;
        this.setSize(new Dimension(760, 755));

        try{
            this.sol = ImageIO.read(new File("Ressources/Images/sol.png")).getScaledInstance(95 , 95, Image.SCALE_SMOOTH);
            this.mur = ImageIO.read(new File("Ressources/Images/mur.png")).getScaledInstance(95 , 95, Image.SCALE_SMOOTH);
            this.emplacement = ImageIO.read(new File("Ressources/Images/emplacement.png")).getScaledInstance(95 , 95, Image.SCALE_SMOOTH);
            this.joueur = ImageIO.read(new File("Ressources/Images/personnage.png")).getScaledInstance(95 , 95, Image.SCALE_SMOOTH);
            this.caisse = ImageIO.read(new File("Ressources/Images/caisse.png")).getScaledInstance(95 , 95, Image.SCALE_SMOOTH);
            this.caissev = ImageIO.read(new File("Ressources/Images/caissev.png")).getScaledInstance(95 , 95, Image.SCALE_SMOOTH);

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        for (int i=0; i<9; i++){

            for (int j=0; j<=7; j++){
                String s = this.lab.getCase(j, i).getClass().getSimpleName();
                switch (s){
                    case "Mur":
                        g.drawImage(this.mur, j*95, i*95, null);
                        break;
                    case "Sol":
                        g.drawImage(this.sol, j*95, i*95, null);
                        if (this.lab.getCase(j, i).isOccupe()){
                            g.drawImage(this.caisse, this.lab.getCase(j, i).getOccupantCaisse().getPosX()*95, this.lab.getCase(j, i).getOccupantCaisse().getPosY()*95, null);
                        }
                        break;
                    case "Emplacement":
                        g.drawImage(this.emplacement, j*95, i*95, null);
                        if (this.lab.getCase(j, i).isOccupe()){
                            g.drawImage(this.caissev, this.lab.getCase(j, i).getOccupantCaisse().getPosX()*95, this.lab.getCase(j, i).getOccupantCaisse().getPosY()*95, null);
                        }
                        break;
                }
            }
        }
        g.drawImage(this.joueur, lab.getPersonnage().getPosX()*95, lab.getPersonnage().getPosY()*95, null);

        int nb =0;
        for (int i=0; i<9; i++) {

            for (int j = 0; j <= 7; j++) {
                if (this.lab.getCase(j, i).getClass().getSimpleName() == "Emplacement" && this.lab.getCase(j, i).isOccupe()){
                    if (this.lab.getCase(j, i).getOccupantCaisse().estBienPlace()){
                        nb++;
                    }
                }
            }
        }
        if (nb == this.lab.getNbCaisse()){
            this.lab.win();
        }
    }


    @Override
    public void actualiser(Sujet s) {
        this.lab = (Labyrinthe) s;
        this.repaint();
    }
}
