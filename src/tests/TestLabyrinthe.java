package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Jeu.Case;
import Jeu.Labyrinthe;

public class TestLabyrinthe {
	
	private Labyrinthe l;
	
	@Before
	public void avant() {
		l = new Labyrinthe(1);
	}
	
	@Test
	public void testMovePossible() {
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 1",1, x);
		assertEquals("ça devrait etre 1",1, y);
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 1",1, y);
	}
	
	@Test
	public void testMoveImpossible() {
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 1",1, x);
		assertEquals("ça devrait etre 1",1, y);
		
		l.move("haut");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 1",1, x);
		assertEquals("ça devrait etre 1",1, y);
	}
	
	@Test
	public void testMoveAvecCaissePossible() {
		l.move("bas");
		
		Case c = l.getCase(2, 2);
		
		//La case en coordonnées (2,2) est sensée être occupée par une Caisse
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		l.move("droite");
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 2",2, y);
		
		//On est sensé avoir poussé la Caisse de la position (2,2) à la position (3,2)
		c = l.getCase(3, 2);
		
		assertTrue("ça devrait etre vrai", c.isOccupe());
	}
	
	@Test
	public void testMoveAvecCaisseImpossible() {
		l.move("bas");
		l.move("bas");
		l.move("droite");
		l.move("haut");
		
		Case c = l.getCase(2, 1);
		
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 2",2, y);
		
		l.move("haut");
		
		
		c = l.getCase(2, 1);
		
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 2",2, y);
	}
	
	@Test
	public void testMoveAvecCaisseContreCaisseImpossible() {
		l.move("bas");
		l.move("bas");
		l.move("droite");
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 3",3, y);
		
		Case c = l.getCase(3, 3);
		
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		c = l.getCase(4, 3);
		
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 3",3, y);
		
		c = l.getCase(3, 3);
		
		assertTrue("ça devrait etre vrai", c.isOccupe());
	}

	@Test
	public void testGetCaseSuivante() {
		//On vérifie que la case est de type Sol
		Case c1 = l.getCaseSuivante(l.getPersonnage().getPosX(), l.getPersonnage().getPosY(), "droite");
		assertEquals("ça devrait etre Sol","Jeu.Sol", c1.getClass().getName());
		
		l.move("bas");
		
		//On vérifie que la case est occupée par une Caisse
		Case c2 = l.getCaseSuivante(l.getPersonnage().getPosX(), l.getPersonnage().getPosY(), "droite");
		assertTrue("ça devrait etre vrai", c2.isOccupe());
		
		//On vérifie que la case est de type Mur
		Case c3 = l.getCaseSuivante(l.getPersonnage().getPosX(), l.getPersonnage().getPosY(), "gauche");
		assertEquals("ça devraietre Mur","Jeu.Mur", c3.getClass().getName());
	}
	
	

}
