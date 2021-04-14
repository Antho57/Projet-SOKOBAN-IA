package IA.heuristiques;

/**
 * generation de factory donne une heuristique à partir de son nom
 * 
 * @author vthomas
 *
 */
public class HeuristiqueFactory {

	/**
	 * retourne une heuristique à partir de son nom
	 * 
	 * @param s chaine de
	 * @return
	 */
	public static Heuristique getHeuristique(String s) {

		// en fonction du nom
		switch (s) {

		// heuristique avec 0
		case "Dijkstra":
		case "dijkstra":
			return new HeuristiqueDijkstra();

		// heuristique avec caisse et perso
		case "CaissePerso":
		case "caissePerso":
		case "caisseperso":
		case "Caisseperso":
			return new HeuristiqueCaisseEtPersonnage();

		// heuristique avec caisse et perso (distance plus elevee caisse mal placee)
		case "CaissePersoPlusGrand":
		case "caissepersoplusgrand":
			return new HeuristiqueCaisseEtPlusGrandPersonnage();

		// heuristique developpee par etudiants
		case "HeuristiqueEtudiants":
			return new HeuristiqueEtudiants();

		// heuristique developpee par etudiants sur les caisses
		case "HeuristiqueEtudiantCaisse":
			return new HeuristiqueEtudiantCaisse();

		default:
			throw new Error("Heuristique " + s + " inconnue");

		}

	}

	/**
	 * all known heuristic names
	 */
	public static String[] getAllNames() {
		String[] res = { "CaissePersoPlusGrand", "CaissePerso", "HeuristiqueEtudiants", "HeuristiqueEtudiantCaisse",
				"Dijkstra" };
		return res;
	}

}
