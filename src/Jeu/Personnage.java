package Jeu;

import java.awt.Image;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
 * Class qui représente le personnage dans le labyrinthe
 */
public class Personnage {

	private int posX; // position en x du personnage
	private int posY; // posotion en y du personnage

	/*
	 * Constructeur du personnage
	 * 
	 * @param x, emplacement en x du personnage
	 * 
	 * @param y, emplacement en y du personnage
	 */
	public Personnage(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	/*
	 * Méthode qui renvoi la position x du personnage
	 * 
	 * @return int, la position en x
	 */
	public int getPosX() {
		return this.posX;
	}

	/*
	 * Méthode qui renvoi la position y du personnage
	 * 
	 * @return int, la position en y
	 */
	public int getPosY() {
		return this.posY;
	}

	/*
	 * Méthode qui change la position du personnage
	 * 
	 * @param x, la nouvelle position en x du personnage
	 * 
	 * @param y, la nouvelle position en y du personnage
	 */
	public void setPosition(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	// ##################################
	// equals hashcode
	// ##################################

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnage other = (Personnage) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	public int compareTo(Object o) {
		Personnage p = (Personnage) o;
		if (this.getPosX() < p.getPosX())
			return 1;
		else if (this.getPosX() == p.getPosX()) {
			if (this.getPosY() < p.getPosY())
				return 1;
			else if (this.getPosY() == p.getPosY())
				return 0;
			else
				return -1;
		} else
			return -1;
	}
	
	public String toString() {
		return "p"+this.posX+","+this.posY;
	}
}
