package Jeu;

public class Emplacement extends Case{
	

	private boolean rempli;
	
	
	public Emplacement(int x, int y, boolean b) {
		super(x, y);
		this.rempli = b;
	}
}
