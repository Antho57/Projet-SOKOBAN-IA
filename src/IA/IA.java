package IA;

import InterfaceGraphique.Observateur;
import Jeu.*;

import java.util.*;

import IA.heuristiques.Heuristique;

/**
 * Class qui permet de faire fonctionner l'IA
 */
public class IA implements Sujet {

	// TODO affichage debug ou non
	public static boolean DEBUG = false;

	// TODO plutot une liste triée pour ne pas la parcourir
	private ArrayList<Noeud> listeOuverte; // Liste de noeuds qu'il faut encore explorer

	// TODO transformer en HashSet (besoin de hashcode de noeud)
	private ArrayList<Noeud> listeFerme; // Liste de noeuds que nous avons deja explore

	// TODO dans la classe probleme
	private Labyrinthe lab; // Le labyrinthe courant

	// TODO plutot des variables que des attributs
	private boolean win; // Boolean qui représente la reussite de l'IA
	private Noeud fin; // Noeud qui vas correspondre à l'objectif

	// MVC
	private ArrayList<Observateur> observateurs; // Lise d'observateur pour le model MVC

	/**
	 * heuristique utilisée
	 */
	private Heuristique heuristique;

	/**
	 * Constructeur de l'IA
	 * 
	 * @param lab         labyrinthe courant
	 * @param heuristique heuristique utilisee
	 */
	public IA(Labyrinthe lab, Heuristique heuristique) {
		this.listeOuverte = new ArrayList<Noeud>();
		this.lab = lab;
		this.win = false;
		listeFerme = new ArrayList<Noeud>();
		this.observateurs = new ArrayList<Observateur>();

		// stocke heuristique
		this.heuristique = heuristique;

		// creation de la liste des caisses et de la liste des emplacements
		ArrayList<Caisse> rep = new ArrayList<Caisse>();
		ArrayList<int[]> listeEmplacement = new ArrayList<int[]>();
		// remplissage initial des listes
		// TODO plutot méthode de labyrinthe
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j <= 7; j++) {
				switch (this.lab.getGrille()[j][i].getClass().getSimpleName()) {
				case "Sol":
					if (this.lab.getGrille()[j][i].getOccupantCaisse() != null)
						rep.add(new Caisse(j, i, false));
					break;
				case "Emplacement":
					if (this.lab.getGrille()[j][i].getOccupantCaisse() != null)
						rep.add(new Caisse(j, i, true));
					int[] tab = { j, i };
					listeEmplacement.add(tab);
					break;
				}
			}
		}
		Etat etat = new Etat(this.lab.getPersonnage(), rep);
		Noeud depart = new Noeud(0, listeEmplacement, etat, null, null, this.heuristique);
		this.listeOuverte.add(depart);
	}

	/**
	 * Methode de recherche de solution pour le niveau courant
	 * 
	 * @param objectif, l'Etat qui représente l'objectif
	 */
	public ArrayList<String> chercherSolution() {
		int taille = this.listeOuverte.size();
		int nbIterations = 0;

		// tant qu'il reste des elements à tester et qu'on a pas trouve la sortie
		while (!this.win && listeOuverte.size() > 0) {

			// TODO tres tres bourrin de trier a chaque fois + reverse
			Collections.sort(this.listeOuverte);
			Collections.reverse(this.listeOuverte);

			// gere le nombre d'iterations
			nbIterations++;
			if (DEBUG && nbIterations % 1000 == 0)
				System.out.println("  - it " + nbIterations);

			// Récupère le Noeud et le supprime de la liste
			Noeud n = listeOuverte.get(0);
			this.listeOuverte.remove(0);

			// Vérifie si le Noeud correpsond à un noeud dans la liste fermé
			boolean idem = listeFerme.contains(n);
			
			// Si le Noeud n'est pas déja dans la liste fermé
			if (!idem) {

				// On ajoute le Noeud à la liste fermé
				this.listeFerme.add(n);
				Etat e = n.getEtat();

				// Liste des mouvements a tester
				String[] mouvements = { "Droite", "Gauche", "Haut", "Bas" };

				// Recherche et ajout des prochains Noeud dans la listeOuverte
				for (int k = 0; k < mouvements.length; k++) {
					String m = mouvements[k];
					Etat etat = this.chercherProchainMouvement(m, e);
					if (etat != null) {
						int nbDep = n.getDeplacements() + 1;
						ArrayList<int[]> emplacements = n.getListeEmplacement();
						Noeud noeud = new Noeud(nbDep, emplacements, etat, n, m, this.heuristique);
						this.listeOuverte.add(noeud);

						// test si le nouveau noeud est gagnant
						if (etat.estGagnant()) {
							this.win = true;
							this.fin = noeud;
						}
					}
				}
				if (this.listeOuverte.size() - taille >= 200) {
					notifierObservateurs();
					taille = this.listeOuverte.size();
				}
			}

			// si on a ajoute un noeud dans la liste ouverte
			if (this.win) {
				// Récupère le Noeud final (correspond à la victoire)
				System.out.println("Nombre de tests : " + nbIterations);
				ArrayList<String> rep = new ArrayList<String>();
				int j = 0;
				Noeud temps = this.fin;
				// Parcoure les Noeuds précédents pour récupérer les mouvements
				while (temps.getPrecedent() != null) {
					rep.add(0, temps.getMouvement());
					temps = temps.getPrecedent();
				}
				this.lab.setSolution(rep);
				this.notifierObservateurs();
				return rep;
			}
		}
		System.out.println("Perdu");
		return null;
	}

	/**
	 * Methode qui permet de chercher le prochain Etat dans la direction passé en
	 * paramettre
	 * 
	 * @return Etat, le prochain Etat
	 * @param direction la direction choisie
	 * @param e         l'Etat courant
	 */
	public Etat chercherProchainMouvement(String direction, Etat e) {
		Personnage perso = e.getPersonnage();

		ArrayList<Caisse> liste = (ArrayList<Caisse>) e.getListeCaisses().clone();

		int xSuiv = perso.getPosX();
		int ySuiv = perso.getPosY();

		int xSuiv2 = xSuiv;
		int ySuiv2 = ySuiv;

		// TODO en faire une methode de case suivante
		switch (direction) {
		case "Droite":
			xSuiv += 1;
			xSuiv2 += 2;
			break;
		case "Gauche":
			xSuiv -= 1;
			xSuiv2 -= 2;
			break;
		case "Haut":
			ySuiv -= 1;
			ySuiv2 -= 2;
			break;
		case "Bas":
			ySuiv += 1;
			ySuiv2 += 2;
			break;
		}

		Caisse move = null;
		Caisse move2 = null;

		for (int i = 0; i < liste.size(); i++) {
			Caisse c = liste.get(i);
			if (move == null && c.getPosX() == xSuiv && c.getPosY() == ySuiv) {
				move = new Caisse(xSuiv, ySuiv, c.estBienPlace());
			} else if (move2 == null && c.getPosX() == xSuiv2 && c.getPosY() == ySuiv2) {
				move2 = new Caisse(xSuiv2, ySuiv2, c.estBienPlace());
			}
		}

		Case[][] grille = this.lab.getGrille();
		if (!grille[xSuiv][ySuiv].isMur()) {
			if (move != null) {
				if (move2 == null && !grille[xSuiv2][ySuiv2].isMur()) {
					Personnage p = new Personnage(xSuiv, ySuiv);
					move.setPosition(xSuiv2, ySuiv2);
					if (grille[xSuiv2][ySuiv2].getClass().getSimpleName().equals("Emplacement")) {
						move.setBienPlace(true);
					} else {
						move.setBienPlace(false);
					}
					int size = liste.size();

					for (int i = 0; i < size; i++) {
						Caisse cais = liste.get(i);
						if (cais.getPosX() == xSuiv && cais.getPosY() == ySuiv) {
							liste.remove(i);
							liste.add(i, move);
						}
					}
					return new Etat(p, liste);
				}
			} else {
				Personnage p = new Personnage(xSuiv, ySuiv);
				return new Etat(p, liste);
			}
		}
		return null;
	}

	// ##################################################
	// Getter
	// ##################################################

	public ArrayList<Noeud> getListeOuverte() {
		return this.listeOuverte;
	}

	public ArrayList<Noeud> getListeFerme() {
		return this.listeFerme;
	}

	// ##################################################
	// MVC
	// ##################################################

	/**
	 * Méthode qui permet d'enregistrer un observateur
	 * 
	 * @param o, observateur qu'il faut enregistrer
	 */
	@Override
	public void enregistrerObservateur(Observateur o) {
		this.observateurs.add(o);

	}

	/**
	 * Méthode qui permet de supprimer un observateur de la liste
	 * 
	 * @param o, l'observateur que l'on veut supprimer
	 */
	@Override
	public void supprimerObservateur(Observateur o) {
		int i = this.observateurs.indexOf(o);
		if (i >= 0)
			this.observateurs.remove(i);
	}

	/**
	 * Méthode qui permet de notifier tous les observateurs de la liste après la
	 * modification d'un attribut de la class
	 */
	@Override
	public void notifierObservateurs() {
		if (!this.observateurs.isEmpty()) {
			for (int i = 0; i < this.observateurs.size(); i++) {
				Observateur obs = this.observateurs.get(i);
				obs.actualiser(this);
			}
		}

	}

}
