package InterfaceGraphique;

import Jeu.Labyrinthe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
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
    
    private Timer timer = new Timer();
    

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
            	timer.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("Gauche");  // display the data
                    }
                }, 100);
                break;
            case KeyEvent.VK_D:
            	timer.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("Droite");  // display the data
                    }
                }, 100);
                break;
            case KeyEvent.VK_Z:
            	timer.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("Haut");  // display the data
                    }
                }, 100);
                break;
            case KeyEvent.VK_S:
            	timer.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("Bas");  // display the data
                    }
                }, 100);
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
