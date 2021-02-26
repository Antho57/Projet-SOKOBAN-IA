package IA;

import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;

import java.util.ArrayList;


/*
Class qui permet de faire fonctionner l'IA
 */
public class IA {

    private ArrayList<Noeud> listeNoeuds; //Liste de noeuds qu'il faut encore explorer
    private Noeud[] listeFerme; //Liste de noeuds que nous avons deja explore
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
        Case[][] grille = this.lab.getGrille();

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j <= 7; j++) {
                Case c = grille[j][i];
                switch (c.getClass().getSimpleName()) {
                    case "Sol":
                        c.setOccupantCaisse(null);
                        c.setOccupe(false);
                        break;
                    case "Emplacement":
                        c.setOccupe(true);
                        c.setOccupantCaisse(new Caisse(j,i,true));
                        break;
                    default:
                        break;
                }
            }
        }
        Noeud depart = new Noeud(0,0,new Etat(this.lab.getPersonnage(), this.lab.getGrille()), null);
        this.listeNoeuds.add(depart);
        this.objectif = new Etat(this.lab.getPersonnage(), grille);
        this.chercherSolution(this.objectif);
    }


    public void chercherSolution(Etat objectif) {
        for(int i =0; i<=listeNoeuds.size(); i++){
            if (listeNoeuds.get(i).getEtat().compareEtat(objectif)){
                this.win = true;
            }
        }
        if (!this.win){
            for (int i = 0; i<=listeNoeuds.size(); i++){
                Noeud n = listeNoeuds.get(i);
                Etat e = n.getEtat();
                this.listeNoeuds.remove(n);
                Etat droite = e.chercherProchainMouvement("droite");
                Etat gauche = e.chercherProchainMouvement("gauche");
                Etat haut = e.chercherProchainMouvement("haut");
                Etat bas = e.chercherProchainMouvement("bas");
                if (droite != null){
                    this.listeNoeuds.add(new Noeud(n.getDeplacements()+1, n.getHeuristique(), droite, n));
                }
                if (gauche != null){
                    this.listeNoeuds.add(new Noeud(n.getDeplacements()+1, n.getHeuristique(), gauche, n));
                }
                if (haut != null){
                    this.listeNoeuds.add(new Noeud(n.getDeplacements()+1, n.getHeuristique(), haut, n));
                }
                if (bas != null){
                    this.listeNoeuds.add(new Noeud(n.getDeplacements()+1, n.getHeuristique(), bas, n));
                }
            }
            chercherSolution(objectif);
        }
    }

}
