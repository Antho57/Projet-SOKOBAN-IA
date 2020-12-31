package Jeu;

import java.awt.Image;

public class Personnage {
	
	private Image image;
	private Case position;
	
	public Personnage(Case pos, Image im) {
		this.position = pos;
		this.image = im;
	}

	public void deplacement(String dep) {
		
	}
}
