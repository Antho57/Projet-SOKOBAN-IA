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
	 * Methode qui compare deux Etats
	 * 
	 * @param e1, Etat numero 1
	 * @param e2, Etat numero 2
	 */
	@Override
	public boolean equals(Object obj) {
		Etat e = (Etat) obj;
		if (this.getPersonnage().getPosX() != e.getPersonnage().getPosX()
				|| this.getPersonnage().getPosY() != e.getPersonnage().getPosY()) {
			return false;
		} else {
			// personnage egaux
			
			// comme les etats ont toujours des listes de caisse triees (cf constructeur)
			// => on peut tester egalite des listes
			ArrayList<Caisse> liste = this.getListeCaisses();
			ArrayList<Caisse> listeE = e.getListeCaisses();
			return liste.equals(listeE);
		}

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

}
