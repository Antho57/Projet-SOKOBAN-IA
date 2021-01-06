package Jeu;

public abstract class Case {
	int x;
	int y;



	private boolean occupe;
	private boolean isMur;
	
	public Case(int x, int y) {
		this.x = x;
		this.y = y;
		this.occupe = false;
		this.isMur = false;
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
}
