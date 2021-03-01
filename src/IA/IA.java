package IA;

import Jeu.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;


/*
Class qui permet de faire fonctionner l'IA
 */
public class IA {

    private ArrayList<Noeud> listeOuverte; //Liste de noeuds qu'il faut encore explorer
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
        this.listeOuverte = new ArrayList<Noeud>();
        this.lab = lab;
        this.win = false;
        listeFerme = new ArrayList<Noeud>();
        Noeud depart = new Noeud(0,0,new Etat(this.lab.getPersonnage(), this.lab.getGrille()), null, null);
        Case[][] grille = this.lab.getGrille();
        Case[][] obj = new Case[8][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= 7; j++) {
                switch (grille[j][i].getClass().getSimpleName()) {
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
        this.listeOuverte.add(depart);
        this.objectif = new Etat(this.lab.getPersonnage(), obj);
        this.chercherSolution(this.objectif);
    }


    /*
    Methode de recherche de solution pour le niveau courant
    @param objectif, l'Etat qui représente l'objectif
     */
    public void chercherSolution(Etat objectif) {
        while (!this.win && listeOuverte.size()> 0) {
            int v =0;
            while (v<this.listeOuverte.size() && !this.win) {

                //peut etre un bug de comparaison
                if (this.listeOuverte.get(v).getEtat().estGagnant(objectif)) {
                    this.win = true;
                    this.fin = this.listeOuverte.get(v);
                }
                v++;
            }
            if (!this.win) {
                //Récupère le Noeud et le supprime de la liste
                Noeud n = listeOuverte.get(0);
                this.listeOuverte.remove(0);

                //Vérifie si le Noeud correpsond à un noeud dans la liste fermé
                boolean idem = listeFerme.contains(n);
                //Si le Noeud n'est pas déja dans la liste fermé
                if (!idem) {

                    //On ajoute le Noeud à la liste fermé
                    this.listeFerme.add(n);
                    System.out.println(listeFerme.size());
                    Etat e = n.getEtat();

                    //Liste des mouvements a tester
                    String[] mouvements = {"droite", "gauche", "haut", "bas"};

                    //Recherche et ajout des prochains Noeud dans la listeOuverte
                    for (int k=0; k<mouvements.length; k++){
                        String m = mouvements[k];
                        Etat etat = e.chercherProchainMouvement(m);
                        if (etat != null) this.listeOuverte.add(new Noeud(n.getDeplacements()+1, n.getHeuristique(), etat, n, m));
                    }
                }
            } else {
                //Récupère le Noeud final (correspond à la victoire)
                Noeud n = this.fin;

                //Parcour les Noeuds précédents pour récupérer les mouvements
                while (n.getPrecedent() != null) {
                    System.out.println(n.getMouvement());
                    n = n.getPrecedent();
                }
                System.out.println(n.getMouvement());
            }
        }
    }

}
