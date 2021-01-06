package Jeu;

import java.awt.Image;

public class Sol extends Case{
	

	public Sol(int x, int y, Caisse c, boolean oCai) {
		super(x, y, c);
		this.setOccupe(oCai);
	}

}
