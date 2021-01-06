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
                this.lab.move("gauche");
                break;
            case KeyEvent.VK_D:
                this.lab.move("droit");
                break;
            case KeyEvent.VK_Z:
                this.lab.move("haut");
                break;
            case KeyEvent.VK_S:
                this.lab.move("bas");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
