package Jeu;

import InterfaceGraphique.Observateur;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Labyrinthe implements Sujet{

	private Personnage p;
	private Case[][] grille;
	private int nbCaisse;
	private int mouvements;
	private boolean win;

	private ArrayList<Observateur> observateurs;


	public Labyrinthe(int niv) {
		this.p = null;
		this.grille = new Case[8][9];
		this.mouvements = 0;
		this.nbCaisse = 0;
		this.win = false;
		this.observateurs = new ArrayList<Observateur>();

		try{
			InputStream test = new FileInputStream("./Ressources/Niveaux/sokoban" +niv +".xsb");
			InputStreamReader stream = new InputStreamReader(test);

			BufferedReader br = new BufferedReader(stream);

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
	}


	public Case getCaseSuivante(int x, int y, String dir) {
		Case res = null;
		switch (dir) {
			case "haut":
				if (y >= 1)
					res = this.grille[x][y-1];
				break;
			case "bas":
				if (y<=7)
					res = this.grille[x][y+1];
				break;
			case "droite":
				if (x <= 6)
					res = this.grille[x+1][y];
				break;
			case "gauche":
				if (x >= 1)
					res = this.grille[x-1][y];
				break;
		}
		return res;
	}

	public boolean isMur(Case test) {
		int x = test.getX();
		int y = test.getY();
		boolean res;
		if (x <=0 || x >= 7 || y <= 0 || y >= 8) res = true;
		else res = false;
		return res;
	}

	public boolean isOccupe(Case test) {
		return test.isOccupe();
	}




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

					if (grille[caseSuivante2.getX()][caseSuivante2.getY()].getClass().getSimpleName() =="Emplacement"){
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

	public Case getCase(int x, int y){
		return this.grille[x][y];
	}

	public Personnage getPersonnage(){
		return this.p;
	}

	public int getNbCaisse(){
		return this.nbCaisse;
	}

	public int getMouvements(){
		return this.mouvements;
	}

	public void win(){
		this.win=true;
	}

	public boolean getWin(){
		return this.win;
	}


	@Override
	public void enregistrerObservateur(Observateur o) {
		this.observateurs.add(o);

	}

	@Override
	public void supprimerObservateur(Observateur o) {
		int i =this.observateurs.indexOf(o);
		if (i>= 0)
			this.observateurs.remove(i);
	}

	@Override
	public void notifierObservateurs() {
		for (int i=0; i<this.observateurs.size(); i++) {
			Observateur obs = this.observateurs.get(i);
			obs.actualiser(this);
		}

	}
}
