package IA;

import Jeu.Caisse;
import Jeu.Labyrinthe;

import java.util.ArrayList;


/*
Class qui permet de faire fonctionner l'IA
 */
public class IA {

    private ArrayList<Noeud> listeNoeuds; //Liste de noeuds qu'il faut encore explorer
    private Noeud[] listeFerme; //Liste de noeuds que nous avns deja explore
    private Labyrinthe lab; //Le labyrinthe courant
    private Etat objectif; //Etat qui représente l'objectif de l'IA
    private boolean win; //Boolean qui représente la reussite de l'IA


    /*
    Constructeur de l'IA
    @param lab, labyrinthe courant
     */
    public IA(Labyrinthe lab) {
        this.lab = lab;
        this.win = false;
        listeNoeuds = new ArrayList<Noeud>();
        Caisse[] temp = new Caisse[0];
        Caisse[] listeObj = new Caisse[0];

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j <= 7; j++) {
                String s = this.lab.getCase(j, i).getClass().getSimpleName();
                switch (s) {
                    case "Sol":
                        temp[temp.length] = this.lab.getCase(j, i).getOccupantCaisse();
                        break;
                    case "Emplacement":
                        temp[temp.length] = this.lab.getCase(j, i).getOccupantCaisse();
                        listeObj[listeObj.length] = new Caisse(j, i, true);
                        break;
                    default:
                        break;
                }
            }
        }
        Noeud depart = new Noeud(0,0,new Etat(this.lab.getPersonnage(),temp, this.lab.getGrille()), null);
        this.listeNoeuds.add(depart);
        this.objectif = new Etat(this.lab.getPersonnage(), listeObj, this.lab.getGrille());
        this.chercherSolution(this.objectif, depart);
    }


    public void chercherSolution(Etat objectif, Noeud depart) {
        for(int i =0; i<=listeNoeuds.size(); i++){
            if (listeNoeuds.get(i).getEtat().compareEtat(objectif)){
                this.win = true;
            }
        }
        if (!this.win){
            for (int i = 0; i<=listeNoeuds.size(); i++){
                Noeud n = listeNoeuds.get(i);
                Etat e = n.getEtat();
            }
        }




    }

}
