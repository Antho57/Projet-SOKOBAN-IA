package IA;

import InterfaceGraphique.Observateur;
import Jeu.*;

import java.util.*;


/*
Class qui permet de faire fonctionner l'IA
 */
public class IA implements Sujet{

    private ArrayList<Noeud> listeOuverte; //Liste de noeuds qu'il faut encore explorer
    private ArrayList<Noeud> listeFerme; //Liste de noeuds que nous avons deja explore
    private Labyrinthe lab; //Le labyrinthe courant
    private Etat objectif; //Etat qui représente l'objectif de l'IA
    private Case[][] grille;//La grille du labyrinthe
    private boolean win; //Boolean qui représente la reussite de l'IA
    private Noeud fin; //Noeud qui vas correspondre à l'objectif
    private static int nb;
    private ArrayList<Observateur> observateurs; //Lise d'observateur pour le model MVC
    private int taille; //taille de la liste fermé


    /*
    Constructeur de l'IA
    @param lab, labyrinthe courant
     */
    public IA(Labyrinthe lab) {
        this.listeOuverte = new ArrayList<Noeud>();
        this.lab = lab;
        this.win = false;
        this.grille = lab.getGrille();
        listeFerme = new ArrayList<Noeud>();
        this.observateurs = new ArrayList<Observateur>();
        ArrayList<int[]> listeEmplacement = new ArrayList<int[]>();

        ArrayList<Caisse> rep = new ArrayList<Caisse>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= 7; j++) {
                switch (this.lab.getGrille()[j][i].getClass().getSimpleName()) {
                    case "Sol":
                        if (this.lab.getGrille()[j][i].getOccupantCaisse() != null) rep.add(new Caisse(j, i, false));
                        break;
                    case "Emplacement":
                        if (this.lab.getGrille()[j][i].getOccupantCaisse() != null) rep.add(new Caisse(j, i, true));
                        int[] tab = {j, i};
                        listeEmplacement.add(tab);
                        break;
                }
            }
        }
        Noeud depart = new Noeud(0,listeEmplacement,new Etat(this.lab.getPersonnage(), rep), null, null);
        this.listeOuverte.add(depart);
    }


    /*
    Methode de recherche de solution pour le niveau courant
    @param objectif, l'Etat qui représente l'objectif
     */
    public ArrayList<String> chercherSolution() {
        taille = this.listeOuverte.size();
        while (!this.win && listeOuverte.size()> 0) {
            Collections.sort(this.listeOuverte);
            Collections.reverse(this.listeOuverte);
            int v =0;
            while (v<this.listeOuverte.size() && !this.win) {

                if (this.listeOuverte.get(v).getEtat().estGagnant()) {
                    this.win = true;
                    this.fin = this.listeOuverte.get(v);
                }
                v++;
            }
            if (!this.win) {
                //Récupère le Noeud et le supprime de la liste
                this.nb++;
                Noeud n = listeOuverte.get(0);
                this.listeOuverte.remove(0);

                //Vérifie si le Noeud correpsond à un noeud dans la liste fermé
                boolean idem = listeFerme.contains(n);
                //Si le Noeud n'est pas déja dans la liste fermé
                if (!idem) {

                    //On ajoute le Noeud à la liste fermé
                    this.listeFerme.add(n);
                    Etat e = n.getEtat();

                    //Liste des mouvements a tester
                    String[] mouvements = {"Droite", "Gauche", "Haut", "Bas"};

                    //Recherche et ajout des prochains Noeud dans la listeOuverte
                    for (int k=0; k<mouvements.length; k++){
                        String m = mouvements[k];
                        Etat etat = this.chercherProchainMouvement(m, e);
                        if (etat != null) this.listeOuverte.add(new Noeud(n.getDeplacements()+1, n.getListeEmplacement(), etat, n, m));
                    }
                    if (this.listeOuverte.size()-this.taille >=200){
                        notifierObservateurs();
                        this.taille = this.listeOuverte.size();
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
//                    System.out.println("Mouvement " +j +" : "+n.getMouvement());
//                    j++;
//                    for (int i=0; i<n.getEtat().getListeCaisses().size(); i++){
//                        System.out.print(" Caisse n" +(i+1) +" : ");
//                        System.out.println(n.getEtat().getListeCaisses().get(i).getPosX() +" - " +n.getEtat().getListeCaisses().get(i).getPosY());
//                    }
                    n = n.getPrecedent();
                }
                this.lab.setSolution(rep);
                this.notifierObservateurs();
                return rep;
            }
        }
        System.out.println("Perdu");
        return null;
    }



    /*
    Methode qui permet de chercher le prochain Etat dans la direction passé en paramettre
    @return Etat, le procjhain Etat
    @param direction , la direction choisie
    @param e, l'Etat courant
     */
    public Etat chercherProchainMouvement(String direction, Etat e){
        Personnage perso = e.getPersonnage();

        ArrayList<Caisse> liste = (ArrayList<Caisse>) e.getListeCaisses().clone();


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
            if (move == null && c.getPosX() == xSuiv && c.getPosY() == ySuiv){
                move =  new Caisse(xSuiv, ySuiv, c.estBienPlace());
            }else if (move2 == null && c.getPosX() == xSuiv2 && c.getPosY() == ySuiv2){
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


    public ArrayList<Noeud> getListeOuverte(){
        return this.listeOuverte;
    }

    public ArrayList<Noeud> getListeFerme(){
        return this.listeFerme;
    }

    /*
	Méthode qui permet d'enregistrer un observateur
	@param o, observateur qu'il faut enregistrer
	 */
    @Override
    public void enregistrerObservateur(Observateur o) {
        this.observateurs.add(o);

    }

    /*
    Méthode qui permet de supprimer un observateur de la liste
    @param o, l'observateur que l'on veut supprimer
     */
    @Override
    public void supprimerObservateur(Observateur o) {
        int i =this.observateurs.indexOf(o);
        if (i>= 0)
            this.observateurs.remove(i);
    }

    /*
    Méthode qui permet de notifier tous les observateurs de la liste
    après la modification d'un attribut de la class
     */
    @Override
    public void notifierObservateurs() {
        if (!this.observateurs.isEmpty()) {
            for (int i = 0; i < this.observateurs.size(); i++) {
                Observateur obs = this.observateurs.get(i);
                obs.actualiser(this);
            }
        }

    }
}
