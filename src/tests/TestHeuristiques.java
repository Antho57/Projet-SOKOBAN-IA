package tests;

import IA.Etat;
import IA.Noeud;
import Jeu.Caisse;
import Jeu.Personnage;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class de test pour le l'heuristique
 */
public class TestHeuristiques {
	
	private ArrayList<Caisse> c;
	
	/**
	 * methode qui prepare les donnees
	 */
	@Before
	public void avant() {
		c = new ArrayList<Caisse>();
	}
	
	/**
	 * methode qui teste l'heuristique avec une caisse mal placée
	 */
	@Test
	public void testHeuristiqueMalPlace() {
		c.add(new Caisse(5, 5, false));
		c.add(new Caisse(3,3, false));
		Etat e = new Etat(new Personnage(1, 1), this.c);
		Noeud n = new Noeud(0, 0, e, null, null);
		int val = n.calculerHeuristique();

		assertEquals("La valeur de l'heuristique devrait être de 4", 4, val);

	}


	/**
	 * methode qui teste l'heuristique avec une caisse bien placée
	 */
	@Test
	public void testHeuristiqueBienPlace() {
		c.add(new Caisse(5, 5, false));
		c.add(new Caisse(3,3, true));
		Etat e = new Etat(new Personnage(1, 1), this.c);
		Noeud n = new Noeud(0, 0, e, null, null);
		int val = n.calculerHeuristique();

		assertEquals("La valeur de l'heuristique devrait être de 8", 8, val);

	}
}
