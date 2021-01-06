package Jeu;

import InterfaceGraphique.Observateur;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Labyrinthe implements Sujet{
	
	private Personnage p;
	private Case[][] grille;
	private Caisse[] allCaisse;
	private int nbCaisse;
	private int mouvements;

	private ArrayList<Observateur> observateurs;
	
	
	public Labyrinthe(int niv) {
		this.p = null;
		this.grille = new Case[8][9];
		this.allCaisse = new Caisse[20];
		this.nbCaisse =0;
		this.mouvements = 0;
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
							Image m = null;
							this.grille[j][i] = new Mur(j, i, m);
							break;
						case ".":
							//cases cibles (zones de rangement des caisses)
							Image e= null;
							this.grille[j][i] = new Emplacement(j, i, e);
							break;
						case "$":
							//caisses lorsquâ€™elles ne sont pas dans une cible de rangement
							Image c = null;
							this.grille[j][i] = new Sol(j, i, c);
							this.allCaisse[nbCaisse] = new Caisse(j, i, false);
							this.nbCaisse++;
							break;
						case "*":
							//caisses lorsquâ€™elles sont sur une case cible
							Image cc= null;
							this.grille[j][i] = new Emplacement(j, i, cc);
							this.allCaisse[this.nbCaisse] = new Caisse(j, i, true);
							this.nbCaisse++;
							break;
						case "@":
							//personnage (personnage mobile) lorsquâ€™il nâ€™est pas sur une cible de rangement
							Image p= null;
							this.grille[j][i] = new Sol(j, i, p);
							this.p = new Personnage(j, i);
							break;
						case "+":
							//personnage lorsquâ€™il est sur une case cible
							Image pc= null;
							this.grille[j][i] = new Emplacement(j, i, pc);
							this.p = new Personnage(j, i);
							break;
						case " ":
							//sol simple (sans caisse ni personnage)
							Image s= null;
							this.grille[j][i] = new Sol(j, i, s);
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
		Boolean res;
		if (test.isOccupe()) res = true;
		else res = false;
		return res;
	}
	
	
	
	
	public void move(String direction) {
		// récupération de la position du personnage
		int x= this.p.getPosX();
		int y= this.p.getPosY();
		Case caseSuivante;
		// récupération de la case suivante en fonction de sa direction si le deplacement est possible
		caseSuivante = this.getCaseSuivante(x, y, direction);
		int xSuiv = caseSuivante.getX();
		int ySuiv = caseSuivante.getY();
		// vérification de la case suivante (case libre, mur ou caisse)
		if (!this.isMur(caseSuivante)) {
		if (!this.isOccupe(caseSuivante)  ) {
			this.p.setPosition(xSuiv,ySuiv);
			this.notifierObservateurs();
			// si caisse, vérification de la possibilté de déplacement de celle ci
		}else if(!this.isOccupe(this.getCaseSuivante(xSuiv, ySuiv, direction))&& !this.isMur(this.getCaseSuivante(xSuiv, ySuiv, direction))) {
			this.p.setPosition(xSuiv,ySuiv);
			caseSuivante.setOccupe(false);
			this.getCaseSuivante(xSuiv, ySuiv, direction).setOccupe(true);
			this.notifierObservateurs();
		}
		}
	}

	public Case getCase(int x, int y){
		return this.grille[x][y];
	}

	public Personnage getPersonnage(){
		return this.p;
	}

	public Caisse[] getAllCaisse(){
		return this.allCaisse;
	}

	public int getNbCaisse(){
		return this.nbCaisse;
	}

	public int getMouvements(){
		return this.mouvements;
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
	public static void main(String[] args) {
		Labyrinthe l = new Labyrinthe(1);
		
		for (int i = 0; i < 8; i++) {
			for (int j =0; j < 9; j ++) {
				System.out.println(l.getCase(i,j).isOccupe());
			}
		}
	}
}
