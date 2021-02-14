package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Jeu.Case;
import Jeu.Labyrinthe;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class de test pour le labyrinthe
 */
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
		assertEquals("�a devrait etre 1",1, x);
		assertEquals("�a devrait etre 1",1, y);
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//On v�rifie que le personnage a boug�
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 1",1, y);
	}
	
	/**
	 * methode qui teste la methode move() si le mouvement est impossible
	 */
	@Test
	public void testMoveCollisionMur() {
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		//On teste les positions initiales du personnage
		assertEquals("�a devrait etre 1",1, x);
		assertEquals("�a devrait etre 1",1, y);
		
		l.move("haut");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//On v�rifie que le personnage n'a pas boug�
		assertEquals("�a devrait etre 1",1, x);
		assertEquals("�a devrait etre 1",1, y);
	}
	
	/**
	 * methode qui teste la methode move() dans le cas ou le joueur doit pousser une Caisse et que ce mouvement est possible
	 */
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
		
		//On v�rifie qu'on a bien d�plac� la Caisse de la position (2,2) � la position (2,1)
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		int x = l.getPersonnage().getPosX();
		int y = l.getPersonnage().getPosY();
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 2",2, y);
		
		l.move("haut");
		
		
		c = l.getCase(2, 1);
		
		//On v�rifie que la Caisse n'a pas boug�
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//Et le Personnage non plus
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 2",2, y);
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
		
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 3",3, y);
		
		Case c = l.getCase(3, 3);
		
		//On v�rifie qu'il y a une Caisse
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		c = l.getCase(4, 3);
		
		//Et qu'elle est suivie par une autre
		assertTrue("�a devrait etre vrai", c.isOccupe());
		
		l.move("droite");
		
		x = l.getPersonnage().getPosX();
		y = l.getPersonnage().getPosY();
		
		//On v�rifie que le Personnage n'a pas boug�
		assertEquals("�a devrait etre 2",2, x);
		assertEquals("�a devrait etre 3",3, y);
		
		c = l.getCase(3, 3);
		
		//Et la Caisse non plus
		assertTrue("�a devrait etre vrai", c.isOccupe());
	}
	
	/**
	 * methode qui teste la methode getCaseSuivante()
	 */
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
