package InterfaceGraphique;

import Jeu.Labyrinthe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleurJeu implements KeyListener {

    private Labyrinthe lab;

    public ControleurJeu(Labyrinthe l){

        this.lab = l;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){

            case KeyEvent.VK_Q:
                System.out.println("gauche");
                this.lab.move("gauche");
                break;
            case KeyEvent.VK_D:
                System.out.println("droite");
                this.lab.move("droite");
                break;
            case KeyEvent.VK_Z:
                System.out.println("haut");
                this.lab.move("haut");
                break;
            case KeyEvent.VK_S:
                System.out.println("bas");
                this.lab.move("bas");
                break;
            default:
                System.out.println("ok");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
