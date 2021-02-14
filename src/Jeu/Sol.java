package Jeu;

import java.awt.Image;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class qui représente le sol du labyrinthe
un type de case spécifique
 */
public class Sol extends Case{
	
	/*
	Constructeur du sol
	@param x, la position en x du sol
	@param y, la position en y du sol
	@param c, la caisse positionné sur ce sol, null si il n'y en a pas
	@param oCai, indique si le sol est occupé
	 */
	public Sol(int x, int y, Caisse c, boolean oCai) {
		super(x, y, c);
		this.setOccupe(oCai);
	}

}
