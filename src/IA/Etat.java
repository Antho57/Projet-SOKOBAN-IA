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


    /*
    Methode qui compare deux Etats
    @param e1, Etat numero 1
    @param e2, Etat numero 2
     */
    public boolean compareEtat(Etat e){
        if (this.personnage.getPosX() != e.personnage.getPosX() || this.personnage.getPosY() != e.personnage.getPosY()){
            return false;
        }else {
            for (int i = 0; i <= listeCaisses.length; i++) {
                if (listeCaisses[i].getPosX() != e.listeCaisses[i].getPosX() || listeCaisses[i].getPosY() != e.listeCaisses[i].getPosY()){
                    return false;
                }
            }
        }
        return true;
    }
}
