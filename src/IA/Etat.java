package IA;

import Jeu.*;

import java.util.ArrayList;
import java.util.Comparator;

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
    public Etat(Personnage p, Case[][] g){
        this.personnage = p;
        Case[][] rep = new Case[8][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= 7; j++) {
                Case c = g[j][i];
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


        this.grille = rep;
        this.listeCaisses = this.creerListeCaisse(rep);
    }


    /*
    Methode qui compare deux Etats
    @param e1, Etat numero 1
    @param e2, Etat numero 2
     */
    @Override
    public boolean equals(Object obj) {
        Etat e = (Etat)obj;
        if (this.getPersonnage().getPosX() != e.getPersonnage().getPosX() || this.getPersonnage().getPosY() != e.getPersonnage().getPosY()) {
            return false;
        }else {
            ArrayList<Caisse> liste = this.getListeCaisses();
            ArrayList<Caisse> listeE = e.getListeCaisses();
            int taille = liste.size();
            for (int i = 0; i < taille; i++) {
                if (liste.get(i).getPosX() != listeE.get(i).getPosX() || liste.get(i).getPosY() != listeE.get(i).getPosY()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estGagnant() {
        ArrayList<Caisse> liste = this.getListeCaisses();
        int taille = liste.size();
        for (int i = 0; i < taille; i++) {
            if (!liste.get(i).estBienPlace()){
                return false;
            }
        }
        System.out.println("-----------VICTOIRE------------");
        return true;
    }

    /*
    Methode qui renvoi l'Etat pour un deplacement dans la direction choisi
    @param direction, la direction du deplacement
    @return Etat, l'etat apres le mouvement, null si le mouvement n'est pas possible
     */
    public Etat chercherProchainMouvement(String direction){
        Personnage perso = this.getPersonnage();

        int xSuiv = perso.getPosX();
        int ySuiv = perso.getPosY();

        int xSuiv2 = xSuiv;
        int ySuiv2 = ySuiv;

        switch(direction){
            case "droite":
                xSuiv += 1;
                xSuiv2 += 2;
                break;
            case "gauche":
                xSuiv -= 1;
                xSuiv2 -= 2;
                break;
            case "haut":
                ySuiv -= 1;
                ySuiv2 -= 2;
                break;
            case "bas":
                ySuiv += 1;
                ySuiv2 += 2;
                break;
        }
        Case[][] rep = this.grille;

        Case move = rep[xSuiv][ySuiv];

        if (!move.isMur()){
            if(move.isOccupe()){

                Case move2 = rep[xSuiv2][ySuiv2];

                if (!move2.isOccupe() && !move2.isMur()){
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
                switch (g[j][i].getClass().getSimpleName()) {
                    case "Sol":
                        if (g[j][i].getOccupantCaisse() != null) rep.add(new Caisse(j, i, false));
                        break;
                    case "Emplacement":
                        if (g[j][i].getOccupantCaisse() != null) rep.add(new Caisse(j, i, true));
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
