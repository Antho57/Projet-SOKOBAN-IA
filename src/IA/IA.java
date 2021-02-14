package IA;

import Jeu.Labyrinthe;


/*
Class qui permet de faire fonctionner l'IA
 */
public class IA {

    private Noeud[] listeNoeuds; //Liste de noeuds qu'il faut encore explorer
    private Noeud[] listeFerme; //Liste de noeuds que nous avns deja explore
    private Labyrinthe lab; //Le labyrinthe courant


    /*
    Constructeur de l'IA
    @param lab, labyrinthe courant
     */
    public IA(Labyrinthe lab){
        this.lab = lab;
    }

    /*
    Methode qui compare deux Etats
    @param e1, Etat numero 1
    @param e2, Etat numero 2
     */
    public boolean compareEtat(Etat e1, Etat e2){
        return true;
    }

    /*
    Methode qui compare deux Noeuds
    @param n1, Noeud numero 1
    @param n2, Noeud numero 2
     */
    public boolean compareNoeud(Noeud n1, Noeud n2){
        return true;
    }

}
