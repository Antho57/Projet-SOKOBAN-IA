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
    private static int nb;


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
        this.listeOuverte.add(depart);
        this.chercherSolution();
    }


    /*
    Methode de recherche de solution pour le niveau courant
    @param objectif, l'Etat qui représente l'objectif
     */
    public void chercherSolution() {
        while (!this.win && listeOuverte.size()> 0) {
            int v =0;
            while (v<this.listeOuverte.size() && !this.win) {

                //peut etre un bug de comparaison
                if (this.listeOuverte.get(v).getEtat().estGagnant()) {
                    this.win = true;
                    this.fin = this.listeOuverte.get(v);
                }
                v++;
            }
            if (!this.win) {
                //Récupère le Noeud et le supprime de la liste
                this.nb++;
                //System.out.println(nb);
                Noeud n = listeOuverte.get(0);
                this.listeOuverte.remove(0);

                //Vérifie si le Noeud correpsond à un noeud dans la liste fermé
                boolean idem = listeFerme.contains(n);
                //Si le Noeud n'est pas déja dans la liste fermé
                if (!idem) {

                    //On ajoute le Noeud à la liste fermé
                    this.listeFerme.add(n);
                    //System.out.println(listeFerme.size());
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
                System.out.println("Nombre de tests : " +nb);

                //Parcoure les Noeuds précédents pour récupérer les mouvements
                while (n.getPrecedent() != null) {
                    System.out.println(n.getMouvement());
                    n = n.getPrecedent();
                }
            }
        }
    }

}
