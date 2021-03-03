package IA;

import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;
import Jeu.Personnage;

import java.util.ArrayList;

public class Probleme {

    private Case[][] grille;


    public Probleme(Labyrinthe lab){
        this.grille = lab.getGrille();
    }


    public Etat chercherProchainMouvement(String direction, Etat e){
        Personnage perso = e.getPersonnage();

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
            ArrayList<Caisse> listeC = e.getListeCaisses();
            Caisse move1 = null;
            Caisse move2 = null;
            for (int i=0; i<listeC.size(); i++){
                Caisse c = listeC.get(i);
                if (c.getPosX() == xSuiv && c.getPosY() == ySuiv){
                    move1 = c;
                }else if (c.getPosX() == xSuiv2 && c.getPosY() == ySuiv2){
                    move2 = c;
                }
            }
            
            if(move1 != null){

                if (move2 == null && !rep[xSuiv2][ySuiv2].isMur()){
                    Personnage p = new Personnage(xSuiv, ySuiv);

                    move1.setPosition(xSuiv2, ySuiv2);
                    if (rep[xSuiv2][ySuiv2].getClass().getSimpleName().equals("Emplacement")) {
                        move1.setBienPlace(true);
                    }

                    return new Etat(p, rep);
                }
            }else{
                Personnage p = new Personnage(xSuiv, ySuiv);
                return new Etat(p, rep);
            }
        }
        return null;
    }
}
