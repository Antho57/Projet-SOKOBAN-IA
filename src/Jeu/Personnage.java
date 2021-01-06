package Jeu;

import java.awt.Image;

public class Personnage {

	private int posX;
	private int posY;
	
	public Personnage(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {

		return this.posY;
	}

	public void setPosition(int X, int Y){
		this.posX = X;
		this.posY = Y;
	}
}
