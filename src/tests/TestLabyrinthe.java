package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Jeu.Case;
import Jeu.Labyrinthe;

public class TestLabyrinthe {
	
	private Labyrinthe l;
	
	/**
	 * methode qui prepare les donnees
	 */
	@Before
	public void avant() {
		l = new Labyrinthe(1);
	}
	
	/**
	 * methode qui teste la methode move() si le mouvement est possible
	 */
	@Test
	public void testMovePossible() {
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		//On teste les positions initiales du personnage
		assertEquals("ça devrait etre 1",1, x);
		assertEquals("ça devrait etre 1",1, y);
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//On vérifie que le personnage a bougé
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 1",1, y);
	}
	
	/**
	 * methode qui teste la methode move() si le mouvement est impossible
	 */
	@Test
	public void testMoveCollisionMur() {
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		//On teste les positions initiales du personnage
		assertEquals("ça devrait etre 1",1, x);
		assertEquals("ça devrait etre 1",1, y);
		
		l.move("haut");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//On vérifie que le personnage n'a pas bougé
		assertEquals("ça devrait etre 1",1, x);
		assertEquals("ça devrait etre 1",1, y);
	}
	
	/**
	 * methode qui teste la methode move() dans le cas ou le joueur doit pousser une Caisse et que ce mouvement est possible
	 */
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
	
	/**
	 * methode qui teste la methode move() dans le cas ou le joueur doit pousser une Caisse et que ce mouvement est impossible a cause d une collision avec un Mur
	 */
	@Test
	public void testMoveAvecCaisseCollisionMur() {
		l.move("bas");
		l.move("bas");
		l.move("droite");
		l.move("haut");
		
		Case c = l.getCase(2, 1);
		
		//On vérifie qu'on a bien déplacé la Caisse de la position (2,2) à la position (2,1)
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 2",2, y);
		
		l.move("haut");
		
		
		c = l.getCase(2, 1);
		
		//On vérifie que la Caisse n'a pas bougé
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//Et le Personnage non plus
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 2",2, y);
	}
	
	/**
	 * methode qui teste la methode move() dans le cas ou le joueur doit pousser une Caisse et que ce mouvement est impossible a cause d une collision avec une autre Caisse
	 */
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
		
		//On vérifie qu'il y a une Caisse
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		c = l.getCase(4, 3);
		
		//Et qu'elle est suivie par une autre
		assertTrue("ça devrait etre vrai", c.isOccupe());
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//On vérifie que le Personnage n'a pas bougé
		assertEquals("ça devrait etre 2",2, x);
		assertEquals("ça devrait etre 3",3, y);
		
		c = l.getCase(3, 3);
		
		//Et la Caisse non plus
		assertTrue("ça devrait etre vrai", c.isOccupe());
	}
	
	/**
	 * methode qui teste la methode getCaseSuivante()
	 */
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
