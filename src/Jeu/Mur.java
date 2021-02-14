package Jeu;

import java.awt.Image;

/*
Class qui représente un mur dans le labyrinthe
un type de case spécifique
 */
public class Mur extends Case {

	/*
	Constructeur du mur
	@param x, emplacement en x du mur
	@param y, emplacement en y du mur
	 */
	public Mur(int x, int y) {
		super(x, y, null);
		this.setMur(true);
	}


}
