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
		
		assertEquals("�a devrait etre 1",1, x);
		assertEquals("�a devrait etre 1",1, y);
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 1",1, y);
	}
	
	@Test
	public void testMoveImpossible() {
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 1",1, x);
		assertEquals("�a devrait etre 1",1, y);
		
		l.move("haut");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 1",1, x);
		assertEquals("�a devrait etre 1",1, y);
	}
	
	@Test
	public void testMoveAvecCaissePossible() {
		l.move("bas");
		
		Case c = l.getCase(2, 2);
		
		//La case en coordonn�es (2,2) est sens�e �tre occup�e par une Caisse
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		l.move("droite");
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 2",2, y);
		
		//On est sens� avoir pouss� la Caisse de la position (2,2) � la position (3,2)
		c = l.getCase(3, 2);
		
		assertTrue("�a devrait etre vrai", c.isOccupe());
	}
	
	@Test
	public void testMoveAvecCaisseImpossible() {
		l.move("bas");
		l.move("bas");
		l.move("droite");
		l.move("haut");
		
		Case c = l.getCase(2, 1);
		
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 2",2, y);
		
		l.move("haut");
		
		
		c = l.getCase(2, 1);
		
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 2",2, y);
	}
	
	@Test
	public void testMoveAvecCaisseContreCaisseImpossible() {
		l.move("bas");
		l.move("bas");
		l.move("droite");
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 3",3, y);
		
		Case c = l.getCase(3, 3);
		
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		c = l.getCase(4, 3);
		
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 3",3, y);
		
		c = l.getCase(3, 3);
		
		assertTrue("�a devrait etre vrai", c.isOccupe());
	}

	@Test
	public void testGetCaseSuivante() {
		//On v�rifie que la case est de type Sol
		Case c1 = l.getCaseSuivante(l.getPersonnage().getPosX(), l.getPersonnage().getPosY(), "droite");
		assertEquals("�a devrait etre Sol","Jeu.Sol", c1.getClass().getName());
		
		l.move("bas");
		
		//On v�rifie que la case est occup�e par une Caisse
		Case c2 = l.getCaseSuivante(l.getPersonnage().getPosX(), l.getPersonnage().getPosY(), "droite");
		assertTrue("�a devrait etre vrai", c2.isOccupe());
		
		//On v�rifie que la case est de type Mur
		Case c3 = l.getCaseSuivante(l.getPersonnage().getPosX(), l.getPersonnage().getPosY(), "gauche");
		assertEquals("�a devraietre Mur","Jeu.Mur", c3.getClass().getName());
	}
	
	

}
