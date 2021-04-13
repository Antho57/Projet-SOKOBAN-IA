package IA.heuristiques;

import java.util.List;

import IA.Etat;

/**
 * interface destinee a representer une heuristique à transmettre à A*
 */
public interface Heuristique {

	/**
	 * calcule la valeur heuristique associée à l'état s
	 *
	 * @param s            état dont on souhaite calculer l'heuristique
	 * @param emplacements la liste des emplacements possibles
	 * @return valeur de l'heuristique
	 */
	public int calculerHeuristique(Etat etat, List<int[]> emplacements);

}
