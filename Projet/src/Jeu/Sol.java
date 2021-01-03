package Jeu;

import java.awt.Image;

public class Sol extends Case{
	

	private boolean occupe;
	private Personnage occupantPersonnage;
	private Caisse occupantCaisse;
	private Image image;
	
	public Sol(int x, int y, Image im) {
		super(x, y);
		this.occupe = false;
		this.image = im;
		this.occupantPersonnage =null;
		this.occupantCaisse =null;
	}

	public void occuperPersonnage(Personnage p){
		this.occupantPersonnage=p;
		this.occupe = true;
	}

	public void occuperCaisse(Caisse c){
		this.occupantCaisse=c;
		this.occupe = true;
	}
}
