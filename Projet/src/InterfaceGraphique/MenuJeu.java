package InterfaceGraphique;

import Jeu.ModeleLabyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJeu extends JPanel {

    private Fenetre f;
    private int niveau;
    private int difficulte;
    private ModeleLabyrinthe modele;
    private JPanel inf;

    public MenuJeu(Fenetre f, int[] choix){
        this.f = f;
        this.niveau = choix[0];
        this.difficulte = choix[1];
        this.setSize(new Dimension(900, 600));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(78, 48, 150));

        this.inf = new VueInferieureJoueur(this.modele, this);
        this.add(this.inf, BorderLayout.SOUTH);

        VueSuperieure vs = new VueSuperieure(this.modele, this, this.niveau, this.difficulte);
        this.add(vs, BorderLayout.NORTH);

        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(900, 200));
        jp2.setBackground(new Color(207, 88, 88));
        this.add(jp2, BorderLayout.CENTER);
    }

    public void retour(){

        MenuPrincipale m = new MenuPrincipale(this.f);
        this.f.changer(m);

    }

    public void choixNiveau(){

    }

    public void modeIA(){
        VueInferieureIA v = new VueInferieureIA(this.modele, this);
        this.remove(this.inf);
        this.add(v, BorderLayout.SOUTH);
        this.inf = v;
        this.revalidate();
        this.repaint();

    }

    public void modeJoueur(){
        VueInferieureJoueur v = new VueInferieureJoueur(this.modele, this);
        this.remove(this.inf);
        this.add(v, BorderLayout.SOUTH);
        this.inf = v;
        this.revalidate();
        this.repaint();
    }
}
