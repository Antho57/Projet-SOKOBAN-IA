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
							//caisses lorsqu’elles ne sont pas dans une cible de rangement
							Image c = null;
							this.grille[j][i] = new Sol(j, i, c);
							this.allCaisse[nbCaisse] = new Caisse(j, i, false);
							this.nbCaisse++;
							break;
						case "*":
							//caisses lorsqu’elles sont sur une case cible
							Image cc= null;
							this.grille[j][i] = new Emplacement(j, i, cc);
							this.allCaisse[this.nbCaisse] = new Caisse(j, i, true);
							this.nbCaisse++;
							break;
						case "@":
							//personnage (personnage mobile) lorsqu’il n’est pas sur une cible de rangement
							Image p= null;
							this.grille[j][i] = new Sol(j, i, p);
							this.p = new Personnage(j, i);
							break;
						case "+":
							//personnage lorsqu’il est sur une case cible
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

	public void move(String dep) {
		int x= this.p.getPosX();
		int y= this.p.getPosY();
		switch (dep){
			case "haut":
				if(!this.grille[y-1][x].isMur()) {
					if (this.grille[y-1][x].isOccupe() && (!this.grille[y-2][x].isMur() && !this.grille[y-2][x].isOccupe()) && y>=3) {
						boolean trouve = false;
						int i = 0;
						while(i < this.nbCaisse && trouve ==  false) {
							if(this.allCaisse[i].getPosY()== y-1 && this.allCaisse[i].getPosX() == x ){
								trouve = true;
							}
							i++;
						}
						this.p.setPosition(x, y-1);
						this.allCaisse[i-1].setPosition(x, y-2);
						this.notifierObservateurs();
					}else if (y > 1 && !this.grille[y-1][x].isOccupe()) {
						
					
						this.p.setPosition(y-1, x);
					this.notifierObservateurs();
				}}
				break;
			case "bas":
				
				if(!this.grille[y+1][x].isMur()) {
					if (this.grille[y+1][x].isOccupe() && (!this.grille[y+2][x].isMur() && !this.grille[y+2][x].isOccupe()) && y<=6) {
						boolean trouve = false;
						int i = 0;
						while(i < this.nbCaisse && trouve ==  false) {
							if(this.allCaisse[i].getPosY()== y+1 && this.allCaisse[i].getPosX() == x ){
								trouve = true;
							}
							i++;
						}
						this.p.setPosition(x, y+1);
						this.allCaisse[i-1].setPosition(x, y+2);
						this.notifierObservateurs();
					}else if (y <= 7 && !this.grille[y+1][x].isOccupe()) {
						
					
						this.p.setPosition(y+1, x);
					this.notifierObservateurs();
				}}
				
				break;
			case "gauche":
				if(!this.grille[y][x-1].isMur()) {
					if (this.grille[y][x-1].isOccupe() && (!this.grille[y][x-2].isMur() && !this.grille[y][x-2].isOccupe()) && x>=3) {
						boolean trouve = false;
						int i = 0;
						while(i < this.nbCaisse && trouve ==  false) {
							if(this.allCaisse[i].getPosY()== y && this.allCaisse[i].getPosX() == x-1 ){
								trouve = true;
							}
							i++;
						}
						this.p.setPosition(x-1, y);
						this.allCaisse[i-1].setPosition(x-2, y);
						this.notifierObservateurs();
					}else if(x >= 2 && !this.grille[y][x-1].isOccupe()) {
					
						this.p.setPosition(x-1, y);
						this.notifierObservateurs();
					}
				}
				break;
			case "droite":
				
				if(!this.grille[y][x+1].isMur()) {
					if (this.grille[y][x+1].isOccupe() && (!this.grille[y][x+2].isMur() && !this.grille[y][x+2].isOccupe()) && x<=5) {
						boolean trouve = false;
						int i = 0;
						while(i < this.nbCaisse && trouve ==  false) {
							if(this.allCaisse[i].getPosY()== y && this.allCaisse[i].getPosX() == x+1 ){
								trouve = true;
							}
							i++;
						}
						this.p.setPosition(x+1, y);
						this.allCaisse[i-1].setPosition(x+2, y);
						this.notifierObservateurs();
					}else if(x <= 6 && !this.grille[y][x+1].isOccupe()) {
					
						this.p.setPosition(x+1, y);
						this.notifierObservateurs();
					}
				}
				
				break;
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
}
