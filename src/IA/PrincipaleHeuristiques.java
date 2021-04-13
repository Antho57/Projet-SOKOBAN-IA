package IA;

import IA.heuristiques.Heuristique;
import IA.heuristiques.HeuristiqueCaisseEtPersonnage;
import IA.heuristiques.HeuristiqueDijkstra;
import IA.heuristiques.HeuristiqueFactory;
import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;

/**
 * Class principale qui permet de lancer l'IA lors de l'appui sur le bouton
 */
public class PrincipaleHeuristiques {

	/**
	 * Main, qui permet de créer et lancer l'IA avec toutes les heuristiques
	 */
	public static void main(String[] args) {

		// les heuristiques à tester
		String[] nomsHeuristiques = { "Dijkstra", "CaissePerso" };

		// pour chaque heuristique
		for (String nom : nomsHeuristiques) {
			// creation du niveau
			Labyrinthe lab = new Labyrinthe(0);

			// recuperation heuristique avec Factory
			Heuristique heuristique = HeuristiqueFactory.getHeuristique(nom);

			// creation de l'IA
			IA ia = new IA(lab, heuristique);

			System.out.println("### Heuristique " + nom);

			// temp au début de l'execution
			double timeStart = System.currentTimeMillis();
			// lancement de la recherche de l'IA
			ia.chercherSolution();
			// temp à la fin de l'execution
			double timeEnd = System.currentTimeMillis();
			System.out.println("Temps d'éxécution : " + ((timeEnd - timeStart) / 1000) + " secondes");

		}
	}
}