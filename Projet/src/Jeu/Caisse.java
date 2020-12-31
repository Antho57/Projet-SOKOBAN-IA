package Jeu;

import java.awt.Image;

public class Caisse {
	
	private Case position;
	private Image image;
	
	public Caisse(Case pos, Image im) {
		this.position = pos;
		this.image = im;
	}
}
