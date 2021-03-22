package InterfaceGraphique;

import Jeu.Labyrinthe;

import javax.swing.*;
import java.awt.*;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class MenuJeu qui représente le menu générale du jeu
qui contient la VueSupérieur et Inférieur ainsi que la VueJeu
 */
public class MenuJeu extends JPanel {

    private Fenetre window; //fenêtre d'affichage du jeu
    private int niveau; //niveau courant
    private int difficulte; //difficulté du niveau
    private Labyrinthe Lab; //Labyrinthe courant
    private JPanel inf; //VueInférieur
    private JPanel jeu; //Vue du jeu

    /*
    Constructeur MenuJeu
    @param window, fenetre de l'application
    @param choix, tableau qui représente le choix du niveau et de la difficulté
    @param l, labyrinthe courant
     */
    public MenuJeu(Fenetre window, int[] choix, Labyrinthe l){
        this.window = window;
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

        VueJeu jp2 = new VueJeu(Lab, this);
        ControleurJeu ctrl = new ControleurJeu(Lab);
        window.addKeyListener(ctrl);
        window.requestFocus();
        Lab.enregistrerObservateur(jp2);
        Lab.enregistrerObservateur(vs);
        this.jeu = jp2;
        this.add(jp2, BorderLayout.CENTER);
    }

    /*
    Méthode qui permet de revenir au menu principale
     */
    public void retour(){

        MenuPrincipale m = new MenuPrincipale(this.window);
        this.window.changer(m);

    }

    /*
    Méthode qui permet de notifier que la partie est gagné
     */
    public void gagner(){
        VueGagner vg = new VueGagner(this.Lab, this);
        this.window.changer(vg);
    }

    /*
    Méthode qui permet de recommencer le niveau courant
     */
    public void relancer(){

        int[] choix= {this.niveau, this.difficulte};

        MenuJeu m = new MenuJeu(this.window, choix,new Labyrinthe(this.niveau));
        this.window.changer(m);
    }

    /*
    Méthode qui permet de passer au modeIA
     */
    public void modeIA(){
        VueInferieureIA v = new VueInferieureIA(this.Lab, this);
        this.remove(this.inf);
        this.add(v, BorderLayout.SOUTH);
        this.inf = v;
        this.revalidate();
        this.repaint();

    }

    /*
    Méthode qui permet de passer au modeJoueur
     */
    public void modeJoueur(){
        VueInferieureJoueur v = new VueInferieureJoueur(this.Lab, this);
        this.remove(this.inf);
        this.add(v, BorderLayout.SOUTH);
        this.inf = v;
        this.revalidate();
        this.repaint();
    }

//    public void avancerIA(String direction){
//        Lab.mouvementIA(direction);
//    }

    public Fenetre getWindow(){
        return this.window;
    }

}
