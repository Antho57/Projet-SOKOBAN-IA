package IA.heuristiques;

import java.util.ArrayList;
import java.util.List;

import IA.Etat;
import Jeu.Caisse;
import Jeu.Personnage;

/**
 * 
 * Heuristique Caisse developpe
 * 
 * @author Nicol et al.
 *
 */
public class HeuristiqueEtudiantCaisse implements Heuristique {

	/**
	 * Heuristique developpee par les etudiants sur caisses
	 */
	public int calculerHeuristique(Etat etat, List<int[]> emplacements) {
		ArrayList<Caisse> liste = etat.getListeCaisses();
		Personnage p = etat.getPersonnage();
		int valeurLaPlusPetite = 1000;
		for (int i = 0; i < liste.size(); i++) {
			if (!liste.get(i).estBienPlace()) {
				int valCourante = (Math.abs(liste.get(i).getPosX() - p.getPosX())
						+ Math.abs(liste.get(i).getPosY() - p.getPosY()));
				if (valCourante < valeurLaPlusPetite)
					valeurLaPlusPetite = valCourante;
			}
		}
		return valeurLaPlusPetite;
	}

}
