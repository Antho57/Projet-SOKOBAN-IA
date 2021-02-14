package Jeu;

import java.awt.Image;

/*
Class qui représente les caisses présentes dans le lalbyrinthe
 */
public class Caisse {


	private Case position; //Case sur laquelle la caisse est positionné
	private int posX; //position en x de la caisse
	private int posY; //position en y de la caisse
	private boolean bienPlace; //boolean qui indique si la caisse est placé sur un emplacement

	/*
	Constructeur de la caisse
	@param x, position en x de la caisse
	@param y, position en y de la caisse
	 */
	public Caisse(int X, int Y, boolean p) {
		this.posX = X;
		this.posY = Y;
		this.bienPlace = p;
	}

	/*
	Méthode qui renvoi la position x de la caisse
	@return int, la position en x de la caisse
	 */
	public int getPosX() { return this.posX; }

	/*
	Méthode qui renvoi la position y de la caisse
	@return int, la position en y de la caisse
	 */
	public int getPosY() { return this.posY; }

	/*
	Méthode qui change la position de la caisse
	@param x, la nouvelle position en x de la caisse
	@param y, la nouvelle position en y de la caisse
	 */
	public void setPosition(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	/*
	Méthode qui permet de notifier que la caisse est bien placé ou non
	@param p, boolean qui indique si la caisse est bien placé ou non
	 */
	public void setBienPlace(boolean p){
		this.bienPlace = p;
	}

	/*
	Méthode qui permet de savoir si la caisse est placé sur un emplacement
	@return boolean, attribut qui indique si la caisse est bien placé
	 */
	public boolean estBienPlace(){
		return this.bienPlace;
	}

}
