package Jeu;

import java.awt.Image;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class qui représente un emplacement où il faut placer une caisse
un type de case spécifique
 */
public class Emplacement extends Case{
	
	/*
	Constructeur d'un emplacement
	@param x, valeur en x de l'emplacement
	@param y, valeur en y de l'emplacement
	@param c, Caisse qui est sur cet emplacement, null s'il n'y en a pas
	@param oCai, boolean qui indique si l'emplacemetn est occupé ou pas
	 */
	public Emplacement(int x, int y,  Caisse c, boolean oCai) {
		super(x, y, c);
		this.setOccupe(oCai);
	}

}
