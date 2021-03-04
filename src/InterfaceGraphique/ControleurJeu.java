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
            	Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("gauche");  // display the data
                    }
                }, 100);
                break;
            case KeyEvent.VK_D:
            	Timer timer1 = new Timer();
                timer1.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("droite");  // display the data
                    }
                }, 100);
                break;
            case KeyEvent.VK_Z:
            	Timer timer2 = new Timer();
                timer2.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("haut");  // display the data
                    }
                }, 100);
                break;
            case KeyEvent.VK_S:
            	Timer timer3 = new Timer();
                timer3.schedule(new TimerTask()
                {
                    public void run()
                    {
                        lab.move("bas");  // display the data
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
