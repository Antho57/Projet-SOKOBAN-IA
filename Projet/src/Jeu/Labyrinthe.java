package Jeu;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Labyrinthe {
	
	private Personnage p;
	private Case[][] grille;
	
	
	public Labyrinthe(Personnage per, Case[][] grille) {
		this.p = per;
		this.grille = grille;

		try{
			InputStream test = new FileInputStream("Ressources/Niveaux/sokoban01.xsb");
			InputStreamReader stream = new InputStreamReader(test);

			BufferedReader br = new BufferedReader(stream);

			for(int i =0; i < this.grille.length; i++){

				String ligne = br.readLine();
				String[] tab_ligne = new String[ligne.length()];

				for (int k=0; k <ligne.length(); k++){
					tab_ligne[k] = String.valueOf(ligne.charAt(k));
				}

				for (int j=0; j < this.grille[1].length; j++){

					switch (tab_ligne[j]){

						case "#":
							//murs
							Image m = null;
							this.grille[i][j] = new Mur(i, j, m);

							break;
						case ".":
							//cases cibles (zones de rangement des caisses)
							Image e= null;
							this.grille[i][j] = new Emplacement(i, j, e);

							break;
						case "$":
							//caisses lorsqu’elles ne sont pas dans une cible de rangement
							Image c = null;
							this.grille[i][j] = new Sol(i, j, c);
							//this.grille[i][j].occuperCaisse(Caisse c);

							break;
						case "*":
							//caisses lorsqu’elles sont sur une case cible
							Image cc= null;
							this.grille[i][j] = new Emplacement(i, j, cc);
							//this.grille[i][j].occuperCaisse(Caisse c);

							break;
						case "@":
							//personnage (personnage mobile) lorsqu’il n’est pas sur une cible de rangement
							Image p= null;
							this.grille[i][j] = new Sol(i, j, p);
							//this.grille[i][j].occuperPersonnage(Personnage p);

							break;
						case "+":
							//personnage lorsqu’il est sur une case cible
							Image pc= null;
							this.grille[i][j] = new Emplacement(i, j, pc);
							//this.grille[i][j].occuperPersonnage(Personnage p);

							break;
						case " ":
							//sol simple (sans caisse ni personnage)
							Image s= null;
							this.grille[i][j] = new Sol(i, j, s);

							break;
						default:

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
		switch (dep){
			case "haut":
				break;
			case "bas":
				break;
			case "gauche":
				break;
			case "droite":
				break;
		}
	}

	public Case getCase(int x, int y){
		return this.grille[x][y];
	}
}
