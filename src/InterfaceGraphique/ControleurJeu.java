package InterfaceGraphique;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleurJeu implements KeyListener {

    private VueJeu vue;

    public ControleurJeu(VueJeu v){
        this.vue = v;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){

            case KeyEvent.VK_Q:
                this.vue.MAJ("gauche");
                break;
            case KeyEvent.VK_D:
                this.vue.MAJ("droit");
                break;
            case KeyEvent.VK_Z:
                this.vue.MAJ("haut");
                break;
            case KeyEvent.VK_S:
                this.vue.MAJ("bas");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
