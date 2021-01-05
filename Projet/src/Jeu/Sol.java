package Jeu;

import java.awt.Image;

public class Sol extends Case{
	

	
	private Personnage occupantPersonnage;
	private Caisse occupantCaisse;
	private Image image;
	
	public Sol(int x, int y, Image im) {
		super(x, y);
		
		this.image = im;
		this.occupantPersonnage =null;
		this.occupantCaisse =null;
	}



	public void occuperCaisse(Caisse c){
		this.occupantCaisse=c;
		this.setOccupe(true);
	}

	
	
}
