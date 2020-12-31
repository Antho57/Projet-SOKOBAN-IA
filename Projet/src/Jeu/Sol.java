package Jeu;

import java.awt.Image;

public class Sol extends Case{
	

	private boolean occupe;
	private Image image;
	
	public Sol(int x, int y, Image im) {
		super(x, y);
		this.occupe = false;
		this.image = im;
	}
	
	public Sol(int x, int y, boolean b) {
		super(x, y);
		this.occupe = b;
	}
}
