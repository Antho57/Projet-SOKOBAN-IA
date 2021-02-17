package IA;

/*
Class qui represente un noeud dans la recherche
 */
public class Noeud {

    private int deplacements; //Nombre de déplacements deja effectués
    private int cout; //Cout pour arriver à la fin, deplacement + heuristique
    private int heuristique; //Heuristique, mouvements restants pour arriver jusqu'à la fin
    private Etat etat; //Etat courant du labyrinthe pour ce noeud


    /*
    Constructeur du noeud
    @param dep, depalcement deja effectués
    @param heuristique, mouvements qu'il reste a faire pour finir le niveau
    @param e, Etat courant du labyrinthe
     */
    public Noeud(int dep, int heuristique, Etat e){
        this.deplacements = dep;
        this.heuristique = heuristique;
        this.cout = dep + heuristique;
        this.etat = e;
    }
    
    
    /*
    Methode qui compare deux Noeuds
    @param n1, Noeud numero 1
    @param n2, Noeud numero 2
     */
    public int compareNoeud(Noeud n) {
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
}
