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
    private Probleme pb; //Class probleme pour créer les prochains mouvements


    /*
    Constructeur de l'IA
    @param lab, labyrinthe courant
     */
    public IA(Labyrinthe lab) {
        this.listeOuverte = new ArrayList<Noeud>();
        this.lab = lab;
        this.win = false;
        this.pb = new Probleme(lab);
        listeFerme = new ArrayList<Noeud>();

        ArrayList<Caisse> rep = new ArrayList<Caisse>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= 7; j++) {
                switch (this.lab.getGrille()[j][i].getClass().getSimpleName()) {
                    case "Sol":
                        if (this.lab.getGrille()[j][i].getOccupantCaisse() != null) rep.add(new Caisse(j, i, false));
                        break;
                    case "Emplacement":
                        if (this.lab.getGrille()[j][i].getOccupantCaisse() != null) rep.add(new Caisse(j, i, true));
                        break;
                }
            }
        }
        Noeud depart = new Noeud(0,0,new Etat(this.lab.getPersonnage(), rep), null, null);
        this.listeOuverte.add(depart);
    }


    /*
    Methode de recherche de solution pour le niveau courant
    @param objectif, l'Etat qui représente l'objectif
     */
    public ArrayList<String> chercherSolution() {
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
                    String[] mouvements = {"Droite", "Gauche", "Haut", "Bas"};

                    //Recherche et ajout des prochains Noeud dans la listeOuverte
                    for (int k=0; k<mouvements.length; k++){
                        String m = mouvements[k];
                        Etat etat = pb.chercherProchainMouvement(m, e);
                        if (etat != null) this.listeOuverte.add(new Noeud(n.getDeplacements()+1, n.getHeuristique(), etat, n, m));
                    }
                }
            } else {
                //Récupère le Noeud final (correspond à la victoire)
                Noeud n = this.fin;
                System.out.println("Nombre de tests : " +nb);
                ArrayList<String> rep = new ArrayList<String>();
                int j = 0;
                //Parcoure les Noeuds précédents pour récupérer les mouvements
                while (n.getPrecedent() != null) {
                    rep.add(0, n.getMouvement());
                    System.out.println("Mouvement " +j +" : "+n.getMouvement());
                    j++;
                    for (int i=0; i<n.getEtat().getListeCaisses().size(); i++){
                        System.out.print(" Caisse n" +(i+1) +" : ");
                        System.out.println(n.getEtat().getListeCaisses().get(i).getPosX() +" - " +n.getEtat().getListeCaisses().get(i).getPosY());
                    }
                    n = n.getPrecedent();
                }
                return rep;
            }
        }
        System.out.println("Perdu");
        return null;
    }

}
