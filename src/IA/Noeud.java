package IA;

import Jeu.Caisse;
import Jeu.Personnage;

import java.util.ArrayList;

import IA.heuristiques.Heuristique;

/*
Class qui represente un noeud dans la recherche
 */
public class Noeud implements Comparable{

    private int deplacements; //Nombre de déplacements deja effectués
    private int cout; //Cout pour arriver à la fin, deplacement + heuristique
    private int heuristique; //Heuristique, mouvements restants pour arriver jusqu'à la fin
    private Etat etat; //Etat courant du labyrinthe pour ce noeud
    private Noeud precedent; //Noeud precedent afin de récupérer le chemin parcouru
    private String mouvement; //Mouvement qui a mené à ce noeud
    private ArrayList<int[]> listeEmplacement; //Liste des emplacements dans le niveau courant


    /*
    Constructeur du noeud
    @param dep, depalcement deja effectués
    @param heuristique, mouvements qu'il reste a faire pour finir le niveau
    @param e, Etat courant du labyrinthe
     */
    public Noeud(int dep, ArrayList<int[]> l, Etat e, Noeud prec, String mouv, Heuristique h){
        this.etat = e;
        this.precedent = prec;
        this.mouvement = mouv;
        this.deplacements = dep;
        this.listeEmplacement = l;
        this.heuristique = h.calculerHeuristique(this.etat,this.listeEmplacement);
        this.cout = dep + this.heuristique;
    }
    
    
    /*
    Methode qui compare deux Noeuds
    @param n1, Noeud numero 1
    @param n2, Noeud numero 2
     */
    @Override
    public int compareTo(Object obj) {
        Noeud n = (Noeud)obj;
        if (this.cout < n.cout)
            return 1;
        else if(this.cout == n.cout) {
            if (this.deplacements > n.deplacements)
                return 1;
            else if (this.deplacements == n.deplacements)
                return 0;
            else return -1;
        }else return -1;
    }

    @Override
    public boolean equals(Object obj){
        Noeud n = (Noeud)obj;
        return this.etat.equals(n.etat);
    }

     
    /**
    Fonction d'heuristique, qui vas permettre de trier les Etat en fonction de leur importance
    @return int, la valeur de l'heuristique pour le Noeud courant
     */
    
    /*
    public int calculerHeuristique(){
        ArrayList<Caisse> liste = this.etat.getListeCaisses();
        Personnage p = this.etat.getPersonnage();
        int valeurLaPlusPetite = 100;
        for(int i=0; i<liste.size(); i++){
            if (!liste.get(i).estBienPlace()){
                int valCourante = (Math.abs(liste.get(i).getPosX()-p.getPosX())+Math.abs(liste.get(i).getPosY()-p.getPosY()));
                int distanceCaisseEmplacement = 30;
                for (int j=0; j<listeEmplacement.size(); j++){
                    int distanceCourante = (Math.abs(liste.get(i).getPosX()-this.listeEmplacement.get(j)[0])+Math.abs(liste.get(i).getPosY()-this.listeEmplacement.get(j)[1]));
                    if (distanceCourante < distanceCaisseEmplacement) distanceCaisseEmplacement = distanceCourante;
                }
                valCourante +=distanceCaisseEmplacement;
                if (valCourante < valeurLaPlusPetite) valeurLaPlusPetite = valCourante;
            }
        }
        return valeurLaPlusPetite;
    }
    */

    /*
    Methode qui renvoi l'Etat courant du Noeud
    @return Etat, l'Etat courant
     */
    public Etat getEtat(){
        return this.etat;
    }

    /*
    Methode qui retourne le nombre de deplacements courant
    @return int, le nombre de deplacements
     */
    public int getDeplacements(){
        return this.deplacements;
    }

    /*
    Methode qui retourne l'heurisique
    @return int, l'heuristique
     */
    public int getHeuristique(){
        return this.heuristique;
    }

    /*
    Methode qui retourne le Noeud precedent
    @return Noeud, le Noeud precedent
     */
    public Noeud getPrecedent(){ return this.precedent; }

    /*
    Methode qui retourne le mouvement fait pour arriver au Noeud courant
    @return String, le mouvement effectué
     */
    public String getMouvement(){ return this.mouvement; }

    public int getCout(){return  this.cout;}

    public ArrayList<int[]> getListeEmplacement(){ return this.listeEmplacement;}
}
