package Jeu;

import java.awt.Image;

public class Personnage {
	
	private Image image;
	private Case position;
	
	public Personnage(Case pos, Image im) {
		this.position = pos;
		this.image = im;
	}

	public void move(String dep) {
		switch (dep){
			case "haut":
				break;
			case "bas":
				break;
			case "gauche":
				break;
			case "droite":
				break;
		}
	}

	public Case getPosition() {
		return position;
	}

	public void setPosition(Case c){
		this.position = position;
	}
}
