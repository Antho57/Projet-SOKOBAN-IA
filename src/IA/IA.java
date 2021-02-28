package IA;

import Jeu.*;

import java.util.ArrayList;


/*
Class qui permet de faire fonctionner l'IA
 */
public class IA {

    private ArrayList<Noeud> listeNoeuds; //Liste de noeuds qu'il faut encore explorer
    private ArrayList<Noeud> listeFerme; //Liste de noeuds que nous avons deja explore
    private Labyrinthe lab; //Le labyrinthe courant
    private Etat objectif; //Etat qui représente l'objectif de l'IA
    private boolean win; //Boolean qui représente la reussite de l'IA
    private Noeud fin; //Noeud qui vas correspondre à l'objectif


    /*
    Constructeur de l'IA
    @param lab, labyrinthe courant
     */
    public IA(Labyrinthe lab) {
        this.lab = lab;
        this.win = false;
        listeNoeuds = new ArrayList<Noeud>();
        listeFerme = new ArrayList<Noeud>();
        Noeud depart = new Noeud(0,0,new Etat(this.lab.getPersonnage(), this.lab.getGrille()), null, null);
        Case[][] grille = this.lab.getGrille();
        Case[][] obj = new Case[8][9];
        //Case[][] grille2 = new Case[8][9];

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j <= 7; j++) {
                Case c = grille[j][i];
                switch (c.getClass().getSimpleName()) {
                    case "Sol":
                        obj[j][i] = new Sol(j ,i, null, false);
                        break;
                    case "Emplacement":
                        obj[j][i] = new Emplacement(j ,i, new Caisse(j, i, false), true);
                        break;
                    case "Mur":
                        obj[j][i] = new Mur(j, i);
                        break;
                }
            }
        }
        this.listeNoeuds.add(depart);
        this.objectif = new Etat(this.lab.getPersonnage(), obj);
        this.chercherSolution(this.objectif);
    }


    /*
    Methode de recherche de solution pour le niveau courant
    @param objectif, l'Etat qui représente l'objectif
     */
    public void chercherSolution(Etat objectif) {
        while (!this.win) {
            System.out.println(this.win);
            int v = 0;
            while (v < listeNoeuds.size() && !this.win) {
                if (listeNoeuds.get(v).getEtat().win(objectif)) {
                    this.win = true;
                    this.fin = listeNoeuds.get(v);
                }
                v++;
            }
            if (!this.win) {
                int l = listeNoeuds.size();
                for (int i = 0; i < l; i++) {
                    Noeud n = listeNoeuds.get(0);
                    Etat e = n.getEtat();
                    Boolean same = false;
                    int j = 0;
                    while (!same && j < listeFerme.size()) {
                        same = listeFerme.get(j).getEtat().compareEtat(e);
                        j++;
                    }
                    this.listeNoeuds.remove(n);
                    if (!same) {
                        this.listeFerme.add(n);
                        Etat droite = e.chercherProchainMouvement("droite");
                        Etat gauche = e.chercherProchainMouvement("gauche");
                        Etat haut = e.chercherProchainMouvement("haut");
                        Etat bas = e.chercherProchainMouvement("bas");
                        if (droite != null) {
                            this.listeNoeuds.add(new Noeud(n.getDeplacements() + 1, n.getHeuristique(), droite, n, "droite"));
                        }
                        if (gauche != null) {
                            this.listeNoeuds.add(new Noeud(n.getDeplacements() + 1, n.getHeuristique(), gauche, n, "gauche"));
                        }
                        if (haut != null) {
                            this.listeNoeuds.add(new Noeud(n.getDeplacements() + 1, n.getHeuristique(), haut, n, "haut"));
                        }
                        if (bas != null) {
                            this.listeNoeuds.add(new Noeud(n.getDeplacements() + 1, n.getHeuristique(), bas, n, "bas"));
                        }
                    }
                }
            } else {
                Noeud n = this.fin;
                while (n.getPrecedent() != null) {
                    System.out.println(n.getMouvement());
                    n = n.getPrecedent();
                }
                System.out.println(n.getMouvement());
            }
        }
    }

}
