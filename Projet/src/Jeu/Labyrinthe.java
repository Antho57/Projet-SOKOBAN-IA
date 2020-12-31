package Jeu;

import java.util.ArrayList;

public class Labyrinthe {
	
	private Personnage p;
	private ArrayList<ArrayList<Case>> grille;
	
	
	public Labyrinthe(Personnage p, ArrayList<ArrayList<Case>> grille) {
		this.p = p;
		this.grille = grille;
	}
}
