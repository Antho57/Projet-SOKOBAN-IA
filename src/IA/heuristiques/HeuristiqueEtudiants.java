package IA.heuristiques;

import java.util.ArrayList;
import java.util.List;

import IA.Etat;
import Jeu.Caisse;
import Jeu.Personnage;

/**
 * 
 * Heuristique finale développée par les étudiants dans la cadre du projet tut
 * 
 * @author Nicol et al
 *
 */
public class HeuristiqueEtudiants implements Heuristique {

	@Override
	public int calculerHeuristique(Etat etat, List<int[]> emplacements) {
		ArrayList<Caisse> liste = etat.getListeCaisses();
		Personnage p = etat.getPersonnage();
		int valeurLaPlusPetite = 100;
		for (int i = 0; i < liste.size(); i++) {
			if (!liste.get(i).estBienPlace()) {
				int valCourante = (Math.abs(liste.get(i).getPosX() - p.getPosX())
						+ Math.abs(liste.get(i).getPosY() - p.getPosY()));
				int distanceCaisseEmplacement = 30;
				for (int j = 0; j < emplacements.size(); j++) {
					int distanceCourante = (Math.abs(liste.get(i).getPosX() - emplacements.get(j)[0])
							+ Math.abs(liste.get(i).getPosY() - emplacements.get(j)[1]));
					if (distanceCourante < distanceCaisseEmplacement)
						distanceCaisseEmplacement = distanceCourante;
				}
				valCourante += distanceCaisseEmplacement;
				if (valCourante < valeurLaPlusPetite)
					valeurLaPlusPetite = valCourante;
			}
		}
		return valeurLaPlusPetite;

	}

}
