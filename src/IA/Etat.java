package IA;

import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;
import Jeu.Personnage;
/*
Class représentant l'etat du jeu à un moment précis
(l'etat du labyrinthe)
 */
public class Etat {

    private Caisse[] listeCaisses; //liste de caisses du labyrinthe courant
    private Personnage personnage; //personnage du labyrinthe courant
    private Case[][] grille; //La grille du labyrinthe

    /*
    Constructeur de l'etat courant du labyrinthe
     */
    public Etat(Personnage p, Caisse[] c, Case[][] grille){
        this.listeCaisses = c;
        this.personnage = p;
        this.grille = grille;
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

    public Personnage getPersonnage(){
        return this.personnage;
    }

    public Etat[] chercherProchainsMouvements(){
        Etat[] liste = new Etat[0];
        String[] mouvements = new String[0];
        if (this.grille[this.personnage.getPosX()+1][this.personnage.getPosY()].getClass().getSimpleName() != "Mur"){
            
        }
        return null;
    }
}
