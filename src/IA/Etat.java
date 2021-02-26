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
    public Etat(Personnage p, Case[][] grille){
        this.listeCaisses = this.creerListeCaisse(grille);
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

    /*
    Methode qui renvoi l'Etat pour un deplacement dans la direction choisi
    @param direction, la direction du deplacement
    @return Etat, l'etat apres le mouvement, null si le mouvement n'est pas possible
     */
    public Etat chercherProchainMouvement(String direction){
        int xSuiv = 0;
        int ySuiv = 0;
        int ySuiv2 = 0;
        int xSuiv2 = 0;
        switch(direction){
            case "droite":
                xSuiv = this.personnage.getPosY()+1;
                ySuiv = this.personnage.getPosY();
                xSuiv2 = this.personnage.getPosY()+2;
                ySuiv2 = this.personnage.getPosY();
                break;
            case "gauche":
                xSuiv = this.personnage.getPosY()-1;
                ySuiv = this.personnage.getPosY();
                xSuiv2 = this.personnage.getPosY()-2;
                ySuiv2 = this.personnage.getPosY();
                break;
            case "haut":
                xSuiv = this.personnage.getPosY();
                ySuiv = this.personnage.getPosY()+1;
                xSuiv2 = this.personnage.getPosY();
                ySuiv2 = this.personnage.getPosY()+2;
                break;
            case "bas":
                xSuiv = this.personnage.getPosY();
                ySuiv = this.personnage.getPosY()-1;
                xSuiv2 = this.personnage.getPosY();
                ySuiv2 = this.personnage.getPosY()-2;
                break;
        }
        Case move = this.grille[xSuiv][ySuiv];
        if (!move.isMur()){
            if(move.isOccupe()){
                Case move2 = this.grille[xSuiv2][ySuiv2];
                if (!move.isOccupe() && !move2.isMur()){
                    Case[][] depDroit = this.grille;
                    Personnage p = this.personnage;
                    p.setPosition(xSuiv, ySuiv);
                    Caisse c = grille[xSuiv][ySuiv].getOccupantCaisse();

                    depDroit[xSuiv][ySuiv].setOccupe(false);
                    depDroit[xSuiv][ySuiv].setOccupantCaisse(null);

                    c.setPosition(xSuiv2, ySuiv2);
                    depDroit[xSuiv2][ySuiv2].occuperCaisse(c);
                    return new Etat(p, depDroit);
                }
            }else{
                Personnage p = this.personnage;
                p.setPosition(xSuiv, ySuiv);
                return new Etat(p, this.grille);
            }
        }
        return null;
    }

    /*
    Methode qui vas creer la liste des caisses d'un etat
    @param g, la grille du jeu courant
    @return Caisse[], la liste des caisses courante
     */
    public Caisse[] creerListeCaisse(Case[][] g){
        Caisse[] rep = new Caisse[0];
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j <= 7; j++) {
                String s = g[j][i].getClass().getSimpleName();
                switch (s) {
                    case "Sol":
                        rep[rep.length] = g[j][i].getOccupantCaisse();
                        break;
                    case "Emplacement":
                        rep[rep.length] = g[j][i].getOccupantCaisse();
                        break;
                    default:
                        break;
                }
            }
        }
        return rep;
    }

    /*
    Methode qui retourne le personnage de l'Etat courant
    @return Peronnage, le personnage courant
     */
    public Personnage getPersonnage(){
        return this.personnage;
    }
}
