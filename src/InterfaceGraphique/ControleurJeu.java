package InterfaceGraphique;

import Jeu.Labyrinthe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class qui permet de gérer l'appui sur une touche
 */
public class ControleurJeu implements KeyListener {

    private Labyrinthe lab; //labyrinthe courant

    /*
    Constructeur du controleur du jeu
    @param l, labyrinthe courant
     */
    public ControleurJeu(Labyrinthe l){

        this.lab = l;
    }

    /*
    Méthode keyTyped
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*
    Méthode keyPressed
    @param e, evenement
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){

            case KeyEvent.VK_Q:
                this.lab.move("gauche");
                break;
            case KeyEvent.VK_D:
                this.lab.move("droite");
                break;
            case KeyEvent.VK_Z:
                this.lab.move("haut");
                break;
            case KeyEvent.VK_S:
                this.lab.move("bas");
                break;
        }
    }

    /*
    Méthode keyReleased
    @param e, evenement
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
