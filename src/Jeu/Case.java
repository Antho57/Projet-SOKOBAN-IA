package Jeu;

public abstract class Case {
	int x;
	int y;



	private boolean occupe;
	private boolean isMur;
	private Caisse occupantCaisse;
	
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

	public void setOccupantCaisse(Caisse c){
		this.occupantCaisse = c;
	}

	public Caisse getOccupantCaisse() {
		return occupantCaisse;
	}

	public void occuperCaisse(Caisse c){
		this.occupantCaisse=c;
		this.setOccupe(true);
	}
}
