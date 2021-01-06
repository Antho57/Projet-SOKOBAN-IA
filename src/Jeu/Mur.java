package Jeu;

import java.awt.Image;

public class Mur extends Case {

	private Image image;

	public Mur(int x, int y, Image im) {
		super(x, y);
		this.image = im;
		this.setMur(true);
	}
	
	public Image getImage(){
		return this.image;
	}

}
