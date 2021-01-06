package Jeu;

import java.awt.Image;

public class Emplacement extends Case{
	

	public Emplacement(int x, int y,  Caisse c, boolean oCai) {
		super(x, y, c);
		this.setOccupe(oCai);
	}


}
