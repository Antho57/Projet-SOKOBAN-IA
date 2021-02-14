package IA;

import Jeu.Caisse;
import Jeu.Personnage;
/*
Class représentant l'etat du jeu à un moment précis
(l'etat du labyrinthe)
 */
public class Etat {

    private Caisse[] listeCaisses; //liste de caisses du labyrinthe courant
    private Personnage personnage; //personnage du labyrinthe courant

    /*
    Constructeur de l'etat courant du labyrinthe
     */
    public Etat(Personnage p, Caisse[] c){
        this.listeCaisses = c;
        this.personnage = p;
    }
}
