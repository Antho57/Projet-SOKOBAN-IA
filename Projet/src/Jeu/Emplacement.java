package Jeu;

import java.awt.Image;

public class Emplacement extends Case{
	

	private boolean rempli;
	private Image image;
	
	
	public Emplacement(int x, int y, Image im) {
		super(x, y);
		this.rempli = false;
		this.image = im;
	}
}
