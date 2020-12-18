package Jeu;

import java.util.ArrayList;

public class Labyrinthe {
	private Personnage p;
	private ArrayList<Caisse> caisses;
	private ArrayList<ArrayList<Case>> grille;
	
	
	
	
	public Labyrinthe(Personnage p, ArrayList<Caisse> caisses, ArrayList<ArrayList<Case>> grille) {
		super();
		this.p = p;
		this.caisses = caisses;
		this.grille = grille;
	}
	
	
	
}
