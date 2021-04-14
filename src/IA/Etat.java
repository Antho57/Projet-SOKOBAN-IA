package IA;

import Jeu.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Class représentant l'etat du jeu à un moment précis
(l'etat du labyrinthe)
 */
public class Etat {

	private ArrayList<Caisse> listeCaisses; // liste de caisses du labyrinthe courant
	private Personnage personnage; // personnage du labyrinthe courant

	/**
	 * Constructeur de l'etat courant du labyrinthe
	 */
	public Etat(Personnage p, ArrayList<Caisse> c) {
		this.personnage = p;

		// la liste des caisses est triee
		ArrayList<Caisse> liste = (ArrayList<Caisse>) c.clone();
		this.listeCaisses = liste;
		Collections.sort(this.listeCaisses);
	}

	/**
	 * Methode qui retourne true si l'Etat courant est gagnant
	 * 
	 * @return boolean, la reponse
	 */
	public boolean estGagnant() {
		for (int i = 0; i < this.listeCaisses.size(); i++) {
			if (!this.listeCaisses.get(i).estBienPlace()) {
				return false;
			}
		}
		System.out.println("-----------VICTOIRE------------");
		return true;
	}

	/**
	 * Methode qui retourne le personnage de l'Etat courant
	 * 
	 * @return Peronnage, le personnage courant
	 */
	public Personnage getPersonnage() {
		return this.personnage;
	}

	/**
	 * Methode qui retourne la list ede caisse courante
	 * 
	 * @return ArrayList<Caisse>, la liste de caisse courante
	 */
	public ArrayList<Caisse> getListeCaisses() {
		return this.listeCaisses;
	}

	// ###################################
	// Equals et hashcode
	// ###################################

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listeCaisses == null) ? 0 : listeCaisses.hashCode());
		result = prime * result + ((personnage == null) ? 0 : personnage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Etat other = (Etat) obj;
		
		// teste personnage
		if (!personnage.equals(other.personnage))
			return false;
		// comme les caisses sont triées, on compare directement les contenus
		return listeCaisses.equals(other.listeCaisses);
	}

}
