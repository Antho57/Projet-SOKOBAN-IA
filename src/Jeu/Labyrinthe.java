package Jeu;

import IA.IA;
import InterfaceGraphique.Observateur;

import java.io.*;
import java.util.ArrayList;
import java.util.TimerTask;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class qui repérsente le labyrinthe de jeu, qui contient les caisses et le personnage
 */
public class Labyrinthe implements Sujet{

	private Personnage p; //Personnage du jeu
	private Case[][] grille; //Tableau de cases qui représente le labyrinthe
	private int nbCaisse; //Nombre de caisses dans le labyrinthe
	private int mouvements; //Nombre de mouvement effectué par le joueur
	private boolean win; //Boolean qui indique si la partie est gagné
	private IA ia; //Class IA qui résout le niveau en cour
	private ArrayList<String> solution; //Solution du niveau courant donnée par l'IA
	private int emplacement; //Emplacement courant dans la liste de mouvements

	private ArrayList<Observateur> observateurs; //Lise d'observateur pour le model MVC

	/*
	Constructeur du labyrinthe
	@param niv, le niveau du jeu choisi
	 */
	public Labyrinthe(int niv) {
		this.p = null;
		this.grille = new Case[8][9];
		this.mouvements = 0;
		this.nbCaisse = 0;
		this.win = false;
		this.observateurs = new ArrayList<Observateur>();
		this.emplacement = 0;

		try{
			//Récupération du fichier de jeu en .xsb qui représente le labyritnhe a charger
			InputStreamReader stream = new InputStreamReader(new FileInputStream("./Ressources/Niveaux/sokoban" +niv +".xsb"));

			BufferedReader br = new BufferedReader(stream);

			//Lecture et création du tableau qui correspond au jeu choisi
			for(int i =0; i < 9; i++){

				String ligne = br.readLine();
				String[] tab_ligne = new String[ligne.length()];

				for (int k=0; k <8; k++){
					tab_ligne[k] = String.valueOf(ligne.charAt(k));
				}

				for (int j=0; j <= 7; j++){

					switch (tab_ligne[j]){

						case "#":
							//murs
							this.grille[j][i] = new Mur(j, i);
							break;
						case ".":
							//cases cibles (zones de rangement des caisses)
							this.grille[j][i] = new Emplacement(j, i, null, false);
							break;
						case "$":
							//caisses lorsqu’elles ne sont pas dans une cible de rangement
							Caisse caisse = new Caisse(j, i, false);
							this.grille[j][i] = new Sol(j, i, caisse,true);
							this.nbCaisse++;
							break;
						case "*":
							//caisses lorsqu’elles sont sur une case cible
							Caisse caisse2 = new Caisse(j, i, true);
							this.grille[j][i] = new Emplacement(j, i, caisse2, true);
							this.nbCaisse++;
							break;
						case "@":
							//personnage (personnage mobile) lorsqu’il n’est pas sur une cible de rangement
							this.grille[j][i] = new Sol(j, i, null, false);
							this.p = new Personnage(j, i);
							break;
						case "+":
							//personnage lorsqu’il est sur une case cible
							this.grille[j][i] = new Emplacement(j, i, null, false);
							this.p = new Personnage(j, i);
							break;
						case " ":
							//sol simple (sans caisse ni personnage)
							this.grille[j][i] = new Sol(j, i, null, false);
							break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.ia = new IA(this);
	}

	/*
	Methode qui renvoi la prochaine case en fonction de la direction choisi
	@param x, la position en x courante
	@param y, la position en y courante
	@param dir, la direction choisi
	@return Case, la case suivante dans la direction choisi
	 */
	public Case getCaseSuivante(int x, int y, String dir) {
		Case res = null;
		switch (dir) {
			case "Haut":
				if (y >= 1)
					res = this.grille[x][y-1];
				break;
			case "Bas":
				if (y<=7)
					res = this.grille[x][y+1];
				break;
			case "Droite":
				if (x <= 6)
					res = this.grille[x+1][y];
				break;
			case "Gauche":
				if (x >= 1)
					res = this.grille[x-1][y];
				break;
		}
		return res;
	}

	/*
	Méthode qui permet de vérifier si la case passé en parametre est un mur
	@param test, case a tester
	@eturn boolean, indique si la case est un mur ou non
	 */
	public boolean isMur(Case test) {
		int x = test.getX();
		int y = test.getY();
		boolean res;
		if (x <=0 || x >= 7 || y <= 0 || y >= 8) res = true;
		else res = false;
		return res;
	}


	/*
	Méthode qui permet de savoir si la case passé en parametre est occupé
	par une caisse ou par le personnage
	@param test, la case a tester
	@return boolean, indique si la case est occupé ou non
	 */
	public boolean isOccupe(Case test) {
		return test.isOccupe();
	}



	/*
	Méthode qui permet de faire bouger le personnage dans la direction indiqué
	@param direction, la direction choisi
	 */
	public void move(String direction) {
		// r�cup�ration de la position du personnage
		int x= this.p.getPosX();
		int y= this.p.getPosY();
		Case caseSuivante;
		// r�cup�ration de la case suivante en fonction de sa direction si le deplacement est possible
		caseSuivante = this.getCaseSuivante(x, y, direction);
		int xSuiv = caseSuivante.getX();
		int ySuiv = caseSuivante.getY();
		// v�rification de la case suivante (case libre, mur ou caisse)
		if (!this.isMur(caseSuivante)) {
			if (!this.isOccupe(caseSuivante)  ) {
				this.p.setPosition(xSuiv,ySuiv);
				this.mouvements++;
				this.notifierObservateurs();
				// si caisse, v�rification de la possibilt� de d�placement de celle ci
			}else {
				Case caseSuivante2 = this.getCaseSuivante(xSuiv, ySuiv, direction);
				if (!this.isOccupe(caseSuivante2) && !this.isMur(caseSuivante2)) {
					this.p.setPosition(xSuiv, ySuiv);

					Caisse c = grille[xSuiv][ySuiv].getOccupantCaisse();

					grille[xSuiv][ySuiv].setOccupe(false);
					grille[xSuiv][ySuiv].setOccupantCaisse(null);

					c.setPosition(caseSuivante2.getX(), caseSuivante2.getY());
					this.mouvements++;

					if (grille[caseSuivante2.getX()][caseSuivante2.getY()].getClass().getSimpleName().equals("Emplacement")){
						c.setBienPlace(true);
					}else {
						c.setBienPlace(false);
					}

					grille[caseSuivante2.getX()][caseSuivante2.getY()].occuperCaisse(c);

					this.notifierObservateurs();
				}
			}
		}
	}

	/*
	Méthode qui renvoi la case à un emplacement précis
	@param x, l'emplacement en x de la case
	@param y, l'emplacement den y de la case
	@return Case, la case qui correspond au coordonées
	 */
	public Case getCase(int x, int y){
		return this.grille[x][y];
	}

	/*
	Méthode qui retourne le personnage
	@return Personnage, le personne du labyrinthe
	 */
	public Personnage getPersonnage(){
		return this.p;
	}

	/*
	Méthode qui retourne le nombre de caisses dans le labyrinthe
	@return int, le nombre de caisses
	 */
	public int getNbCaisse(){
		return this.nbCaisse;
	}

	/*
	Méthode qui retourne le nombre de mouvements fait par le joueur
	@return int, nombre de mouvements
	 */
	public int getMouvements(){
		return this.mouvements;
	}

	/*
	Méthode qui permet d'indiquer que la partie est gagné
	 */
	public void win(){
		this.win=true;
	}

	/*
	Méthode qui permet de savoir si la partie est gagné
	@return boolean, indique si la partie est gagné ou non
	 */
	public boolean getWin(){
		return this.win;
	}

	/*
	Méthode qui permet d'enregistrer un observateur
	@param o, observateur qu'il faut enregistrer
	 */
	@Override
	public void enregistrerObservateur(Observateur o) {
		this.observateurs.add(o);

	}

	/*
	Méthode qui permet de supprimer un observateur de la liste
	@param o, l'observateur que l'on veut supprimer
	 */
	@Override
	public void supprimerObservateur(Observateur o) {
		int i =this.observateurs.indexOf(o);
		if (i>= 0)
			this.observateurs.remove(i);
	}

	/*
	Méthode qui permet de notifier tous les observateurs de la liste
	après la modification d'un attribut de la class
	 */
	@Override
	public void notifierObservateurs() {
		for (int i=0; i<this.observateurs.size(); i++) {
			Observateur obs = this.observateurs.get(i);
			obs.actualiser(this);
		}

	}

	/*
	Méthode qui permet de créer l'animation quand l'on a gagné la partie
	 */
	public void gagner(){
		this.setPersonnage(1,1);
		this.notifierObservateurs();
		String[] move = {"Droite", "Droite", "Droite", "Droite", "Droite", "Bas", "Bas", "Bas", "Bas", "Bas", "Bas", "Gauche", "Gauche", "Gauche", "Gauche", "Gauche", "Haut", "Haut", "Haut", "Haut", "Haut", "Haut" };
		for (int i=0; i< move.length; i++){
			this.mouvements--;
			this.move(move[i]);
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis()<time+500){

			}
			System.out.println(this.getPersonnage().getPosX() +" - " +this.getPersonnage().getPosY());

		}
	}

	public void setPersonnage(int x, int y){
		this.p.setPosition(x, y);
	}

	public Case[][] getGrille(){
		return this.grille;
	}

	public void chercherSolutionIA(){
		this.solution = this.ia.chercherSolution();
	}

	public void mouvementIA(String direction){
		if (direction.equals("Gauche") && this.emplacement>0) {
			this.mouvements -=2;
			switch (this.solution.get(this.emplacement-1)){
				case "Haut":
					this.move("Bas");
					break;
				case "Bas":
					this.move("Haut");
					break;
				case "Gauche":
					this.move("Droite");
					break;
				case "Droite":
					this.move("Gauche");
					break;
			}
			this.emplacement--;
		}else if (direction.equals("Droite") && this.emplacement<this.solution.size()) {
			this.move(this.solution.get(this.emplacement));
			this.emplacement++;
		}

	}
}
