package IA;

import IA.heuristiques.Heuristique;
import IA.heuristiques.HeuristiqueCaisseEtPersonnage;
import IA.heuristiques.HeuristiqueDijkstra;
import Jeu.Caisse;
import Jeu.Case;
import Jeu.Labyrinthe;

/*
Class principale qui permet de lancer l'IA lors de l'appui sur le bouton
 */
public class PrincipaleIA {

	/*
	 * Main, qui permet de créer et lancer l'IA
	 */
	public static void main(String[] args) {

		Labyrinthe lab = new Labyrinthe(0); // Juste pour enlever l'erreur, main appeler quand on appui sur la touche

		// ajoute une heuristique nulle
		Heuristique heuristique = new HeuristiqueCaisseEtPersonnage();

		// creation de l'IA
		IA ia = new IA(lab, heuristique);

		// temp au début de l'execution
		double timeStart = System.currentTimeMillis();
		// lancement de la recherche de l'IA
		ia.chercherSolution();
		// temp à la fin de l'execution
		double timeEnd = System.currentTimeMillis();
		System.out.println("Temps d'éxécution : " + ((timeEnd - timeStart) / 1000) + " secondes");
	}
}
