package IA;

import Jeu.*;

import java.util.ArrayList;

/*
Class représentant l'etat du jeu à un moment précis
(l'etat du labyrinthe)
 */
public class Etat {

    private ArrayList<Caisse> listeCaisses; //liste de caisses du labyrinthe courant
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
    public boolean compareEtat(Etat e) {
        if (this.personnage.getPosX() != e.personnage.getPosX() || this.personnage.getPosY() != e.personnage.getPosY()) {
            return false;
        } else {
            for (int i = 0; i < listeCaisses.size(); i++) {
                if (this.listeCaisses.get(i).getPosX() != e.listeCaisses.get(i).getPosX() || this.listeCaisses.get(i).getPosY() != e.listeCaisses.get(i).getPosY()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean win(Etat e) {
        for (int i = 0; i < listeCaisses.size(); i++) {
            if (this.listeCaisses.get(i).getPosX() != e.listeCaisses.get(i).getPosX() || this.listeCaisses.get(i).getPosY() != e.listeCaisses.get(i).getPosY()) {
                return false;
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
                xSuiv = this.personnage.getPosX()+1;
                ySuiv = this.personnage.getPosY();
                xSuiv2 = this.personnage.getPosX()+2;
                ySuiv2 = this.personnage.getPosY();
                break;
            case "gauche":
                xSuiv = this.personnage.getPosX()-1;
                ySuiv = this.personnage.getPosY();
                xSuiv2 = this.personnage.getPosX()-2;
                ySuiv2 = this.personnage.getPosY();
                break;
            case "haut":
                xSuiv = this.personnage.getPosX();
                ySuiv = this.personnage.getPosY()-1;
                xSuiv2 = this.personnage.getPosX();
                ySuiv2 = this.personnage.getPosY()-2;
                break;
            case "bas":
                xSuiv = this.personnage.getPosX();
                ySuiv = this.personnage.getPosY()+1;
                xSuiv2 = this.personnage.getPosX();
                ySuiv2 = this.personnage.getPosY()+2;
                break;
        }
        Case[][] rep = new Case[8][9];
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j <= 7; j++) {
                Case c = this.grille[j][i];
                switch (c.getClass().getSimpleName()) {
                    case "Sol":
                        if (c.getOccupantCaisse() !=null) rep[j][i] = new Sol(j ,i, new Caisse(j, i, false), true);
                        else rep[j][i] = new Sol(j ,i, null, false);
                        break;
                    case "Emplacement":
                        if (c.getOccupantCaisse() !=null) rep[j][i] = new Emplacement(j ,i, new Caisse(j, i, false), true);
                        else rep[j][i] = new Emplacement(j ,i, null, false);
                        break;
                    case "Mur":
                        rep[j][i] = new Mur(j, i);
                        break;
                }
            }
        }
        Case move = this.grille[xSuiv][ySuiv];
        if (!move.isMur()){
            if(move.isOccupe()){
                Case move2 = this.grille[xSuiv2][ySuiv2];
                if (!move.isOccupe() && !move2.isMur()){
                    Personnage p = new Personnage(xSuiv, ySuiv);
                    Caisse c = rep[xSuiv][ySuiv].getOccupantCaisse();

                    rep[xSuiv][ySuiv].setOccupe(false);
                    rep[xSuiv][ySuiv].setOccupantCaisse(null);

                    c.setPosition(xSuiv2, ySuiv2);
                    rep[xSuiv2][ySuiv2].occuperCaisse(c);
                    return new Etat(p, rep);
                }
            }else{
                Personnage p = new Personnage(xSuiv, ySuiv);
                return new Etat(p, rep);
            }
        }
        return null;
    }

    /*
    Methode qui vas creer la liste des caisses d'un etat
    @param g, la grille du jeu courant
    @return Caisse[], la liste des caisses courante
     */
    public ArrayList<Caisse> creerListeCaisse(Case[][] g){
        ArrayList<Caisse> rep = new ArrayList<Caisse>();
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j <= 7; j++) {
                String s = g[j][i].getClass().getSimpleName();
                switch (s) {
                    case "Sol":
                        Caisse c = g[j][i].getOccupantCaisse();
                        if (c != null) rep.add(new Caisse(j, i, false));
                        break;
                    case "Emplacement":
                        Caisse c2 = g[j][i].getOccupantCaisse();
                        if (c2 != null) rep.add(new Caisse(j, i, true));
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

    public ArrayList<Caisse> getListeCaisses(){
        return this.listeCaisses;
    }
}
