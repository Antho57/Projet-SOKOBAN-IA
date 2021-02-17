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

    public void chercherSolution(Noeud objectif, Noeud depart) {
    	
    }

    

}
