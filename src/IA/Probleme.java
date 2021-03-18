package IA;

import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;
import Jeu.Personnage;

import java.util.ArrayList;
/*
Class qui permet de créer les prochains Etats
 */
public class Probleme {

    private Case[][] grille;//La grille du labyrinthe


    /*
    Constructeur de la class Probleme
    @param lab, le labyrinthe de base
     */
    public Probleme(Labyrinthe lab){
        this.grille = lab.getGrille();
    }

    /*
    Methode qui permet de chercher le prochain Etat dans la direction passé en paramettre
    @return Etat, le procjhain Etat
    @param direction , la direction choisie
    @param e, l'Etat courant
     */
    public Etat chercherProchainMouvement(String direction, Etat e){
        Personnage perso = e.getPersonnage();
        ArrayList<Caisse> firstListe= e.getListeCaisses();

        ArrayList<Caisse> liste = new ArrayList<Caisse>();
        for (int i =0; i< firstListe.size(); i++){
            Caisse caisse = firstListe.get(i);
            liste.add(new Caisse(caisse.getPosX(), caisse.getPosY(), caisse.estBienPlace()));
        }


        int xSuiv = perso.getPosX();
        int ySuiv = perso.getPosY();

        int xSuiv2 = xSuiv;
        int ySuiv2 = ySuiv;

        switch(direction){
            case "Droite":
                xSuiv += 1;
                xSuiv2 += 2;
                break;
            case "Gauche":
                xSuiv -= 1;
                xSuiv2 -= 2;
                break;
            case "Haut":
                ySuiv -= 1;
                ySuiv2 -= 2;
                break;
            case "Bas":
                ySuiv += 1;
                ySuiv2 += 2;
                break;
        }

        Caisse move = null;
        Caisse move2 = null;

        for (int i = 0; i < liste.size(); i++) {
            Caisse c = liste.get(i);
            if (c.getPosX() == xSuiv && c.getPosY() == ySuiv ){
                move =  new Caisse(xSuiv, ySuiv, c.estBienPlace());
            }else if (c.getPosX() == xSuiv2 && c.getPosY() == ySuiv2){
                move2 = new Caisse(xSuiv2, ySuiv2, c.estBienPlace());
            }
        }


        if (!this.grille[xSuiv][ySuiv].isMur()){
            if(move != null){
                if (move2 == null && !this.grille[xSuiv2][ySuiv2].isMur()){
                    Personnage p = new Personnage(xSuiv, ySuiv);
                    move.setPosition(xSuiv2, ySuiv2);
                    if (this.grille[xSuiv2][ySuiv2].getClass().getSimpleName().equals("Emplacement")) {
                        move.setBienPlace(true);
                    }else{
                        move.setBienPlace(false);
                    }
                    int size = liste.size();

                    for (int i = 0; i < size; i++){
                        Caisse cais = liste.get(i);
                        if (cais.getPosX() == xSuiv && cais.getPosY() == ySuiv){
                            liste.remove(i);
                            liste.add(i, move);
                        }
                    }
                    return new Etat(p, liste);
                }
            }else{
                Personnage p = new Personnage(xSuiv, ySuiv);
                return new Etat(p, liste);
            }
        }
        return null;
    }
}
