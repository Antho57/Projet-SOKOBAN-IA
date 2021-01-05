package Jeu;

import java.awt.Image;

public class Caisse {

	private Case position;
	private int posX;
	private int posY;
	private boolean bienPlace;

	public Caisse(int X, int Y, boolean p) {
		this.posX = X;
		this.posY = Y;
		this.bienPlace = p;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {

		return this.posY;
	}

	public void setPosition(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	public void setBienPlace(boolean p){
		this.bienPlace = p;
	}

	public boolean estBienPlace(){
		return this.bienPlace;
	}

}
