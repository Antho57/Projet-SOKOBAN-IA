package Jeu;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class abstraite qui représente une case
 */
public abstract class Case {
	private int x; //attribut x d'une case
	private int y; //attribut y d'une case
	private boolean occupe; //attribut qui indique si la case est occupé par une caisse ou le personnage
	private boolean isMur; //attribut qui permet de savoir si la case est un mur ou non
	private Caisse occupantCaisse; //attribut qui correspond à la caisse qui est sur cette case

	/*
	Constructeur d'une caisse
	@param x, position en x de la case
	@param y, position en y de la case
	@param c, caisse qui est sur cette case, null sinon
	 */
	public Case(int x, int y, Caisse c) {
		this.x = x;
		this.y = y;
		this.occupe = false;
		this.isMur = false;
		this.occupantCaisse = c;
	}
	
	
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}



	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}



	/**
	 * @param occupe the occupe to set
	 */
	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}


	/**
	 * @return the occupe
	 */
	public boolean isOccupe() {
		return occupe;
	}
	
	
	/**
	 * @return the isMur
	 */
	public boolean isMur() {
		return isMur;
	}


	/**
	 * @param isMur the isMur to set
	 */
	public void setMur(boolean isMur) {
		this.isMur = isMur;
	}

	public void setOccupantCaisse(Caisse c){ this.occupantCaisse = c; }

	public Caisse getOccupantCaisse() {
		return occupantCaisse;
	}

	public void occuperCaisse(Caisse c){
		this.occupantCaisse=c;
		this.setOccupe(true);
	}
}
