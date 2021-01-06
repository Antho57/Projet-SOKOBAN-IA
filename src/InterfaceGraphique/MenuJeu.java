package InterfaceGraphique;

import Jeu.Labyrinthe;

import javax.swing.*;
import java.awt.*;

public class MenuJeu extends JPanel {

    private Fenetre f;
    private int niveau;
    private int difficulte;
    private Labyrinthe Lab;
    private JPanel inf;

    public MenuJeu(Fenetre f, int[] choix, Labyrinthe l){
        this.f = f;
        this.Lab = l;
        this.niveau = choix[0];
        this.difficulte = choix[1];
        this.setSize(new Dimension(767, 925));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(78, 48, 150));

        this.inf = new VueInferieureJoueur(this.Lab, this);
        this.add(this.inf, BorderLayout.SOUTH);

        VueSuperieure vs = new VueSuperieure(this.Lab, this, this.niveau, this.difficulte);
        this.add(vs, BorderLayout.NORTH);

        VueJeu jp2 = new VueJeu(Lab);
        ControleurJeu ctrl = new ControleurJeu(Lab);
        f.addKeyListener(ctrl);
        f.requestFocus();
        Lab.enregistrerObservateur(jp2);

        this.add(jp2, BorderLayout.CENTER);
    }

    public void retour(){

        MenuPrincipale m = new MenuPrincipale(this.f);
        this.f.changer(m);

    }

    public void choixNiveau(){

    }

    public void modeIA(){
        VueInferieureIA v = new VueInferieureIA(this.Lab, this);
        this.remove(this.inf);
        this.add(v, BorderLayout.SOUTH);
        this.inf = v;
        this.revalidate();
        this.repaint();

    }

    public void modeJoueur(){
        VueInferieureJoueur v = new VueInferieureJoueur(this.Lab, this);
        this.remove(this.inf);
        this.add(v, BorderLayout.SOUTH);
        this.inf = v;
        this.revalidate();
        this.repaint();
    }
}
