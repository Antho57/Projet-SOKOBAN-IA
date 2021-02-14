package Jeu;

import java.awt.Image;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class qui représente le personnage dans le labyrinthe
 */
public class Personnage {

	private int posX; //position en x du personnage
	private int posY; //posotion en y du personnage

	/*
	Constructeur du personnage
	@param x, emplacement en x du personnage
	@param y, emplacement en y du personnage
	 */
	public Personnage(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	/*
	Méthode qui renvoi la position x du personnage
	@return int, la position en x
	 */
	public int getPosX() {
		return this.posX;
	}

	/*
	Méthode qui renvoi la position y du personnage
	@return int, la position en y
	 */
	public int getPosY() { return this.posY; }

	/*
	Méthode qui change la position du personnage
	@param x, la nouvelle position en x du personnage
	@param y, la nouvelle position en y du personnage
	 */
	public void setPosition(int X, int Y){
		this.posX = X;
		this.posY = Y;
	}
}
