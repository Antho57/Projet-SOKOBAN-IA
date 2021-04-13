package IA.heuristiques;

import java.util.List;

import IA.Etat;

/**
 * une heuristique avec un cout de zero (Dijkstra)
 * 
 * @author vthomas
 *
 */
public class HeuristiqueDijkstra implements Heuristique {

	/**
	 * retourne valeur de 0 (cout minimal => Dijkstra)
	 */
	public int calculerHeuristique(Etat etat, List<int[]> emplacements) {
		return 0;
	}

}
