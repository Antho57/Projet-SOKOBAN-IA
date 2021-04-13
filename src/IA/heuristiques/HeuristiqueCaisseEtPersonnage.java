package IA.heuristiques;

import java.util.ArrayList;
import java.util.List;

import IA.Etat;
import Jeu.Caisse;
import Jeu.Personnage;

/**
 * classe qui caclcule l'heuristique la plus complete.<br>
 * 
 * Cette heuristique fait la somme de
 * <li>la distance entre le personnage et la caisse la plus proche
 * <li>la distance entre les caisses et les emplacements
 * 
 * @author vthomas
 *
 */
public class HeuristiqueCaisseEtPersonnage implements Heuristique{

	/**
	 * surchage de la methode de calcul d'une heuristique
	 * 
	 * @param etat etat dont on veut calculer la valeur heuristique
	 * @return la valeur de l'heuristique (distance personne + distance caisse)
	 */
	public int calculerHeuristique(Etat etat, List<int[]> emplacements) {

		// recupere la liste des caisses
		ArrayList<Caisse> liste = etat.getListeCaisses();
		Personnage p = etat.getPersonnage();

		// heurstique calculee
		int heuristique = 0;

		// ########################################
		// 1. calcule la distance entre la caisse la plus proche et le personnage

		// distance_minimale
		int distanceMin = 0;

		// pour chaque caisse
		for (Caisse c : liste) {
			// si la caisse n'est pas sur un emplacement
			if (!c.estBienPlace()) {
				// calcule la distance entre caisse et le personnage
				int distanceCaissePerso = distanceCaissePerso(p, c);
				// si plus petite (ou pas encore de distance), stocke cette distance
				if (distanceCaissePerso < distanceMin || distanceMin == 0)
					distanceMin = distanceCaissePerso;
			}
		}

		// heuristique = distance
		// (distance pour être a coté + 1 car caisse mal placee = distanceMin)
		heuristique = distanceMin;

		// ########################################

		// 2. ajoute la distance de chaque caisse a l'emplacement le plus proche

		// pour chaque caisse ajoute à l'heuristique sa distance à l'emplacement le plus
		// proche
		for (Caisse c : liste) {

			// distance minEmplacements (initialisation au premier emplacement)
			int dMinEmplacements = distanceCaisseEmplacement(c, emplacements.get(0));

			// cherche distance a l'emplacement le plus proche
			// (parcours des emplacements)
			for (int[] emplacement : emplacements) {

				int distanceCaisseEmplacement = distanceCaisseEmplacement(c, emplacement);
				if (distanceCaisseEmplacement < dMinEmplacements)
					dMinEmplacements = distanceCaisseEmplacement;
			}

			// ajoute la distance de cette caisse à l'emplacement le plus proche
			heuristique = heuristique + dMinEmplacements;

		}

		// ########################################

		return heuristique;

	}

	/**
	 * distance de manhattan entre une caisse et un emplacement
	 * 
	 * @param c           caisse
	 * @param emplacement emplacement
	 * @return distance entre c et emplacement
	 */
	private int distanceCaisseEmplacement(Caisse c, int[] emplacement) {
		return Math.abs(c.getPosX() - emplacement[0]) + Math.abs(c.getPosY() - emplacement[1]);
	}

	/**
	 * distance entre une caisse et un personnage
	 * 
	 * @param p personnage
	 * @param c caisse
	 * @return distance manhattan entre la caisse et le personnage
	 */
	private int distanceCaissePerso(Personnage p, Caisse c) {
		return Math.abs(c.getPosX() - p.getPosX()) + Math.abs(c.getPosY() - p.getPosY());
	}
}
