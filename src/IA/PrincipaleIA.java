package IA;

import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;
/*
Class principale qui permet de lancer l'IA lors de l'appui sur le bouton
 */
public class PrincipaleIA {

/*
Main, qui permet de créer et lancer l'IA
 */
    public static void main(String[] args ){
        Labyrinthe lab = new Labyrinthe(1); // Juste pour enlever l'erreur, main appeler quand on appui sur la touche
        IA ia = new IA(lab);
    }
}
