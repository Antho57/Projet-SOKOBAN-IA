package Jeu;

public class ModeleLabyrinthe {
	private Labyrinthe laby;
	private int nbDeplacements;

	public ModeleLabyrinthe(Labyrinthe laby) {
		this.laby = laby;
	}


	public int getMouvements(){
		return this.nbDeplacements;
	}
}
