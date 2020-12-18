package InterfaceGraphique;
import javax.swing.*;
import java.awt.*;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/**
 * Class qui permet de créer la fenetre de jeu
 */

public class Fenetre extends JFrame {

    /**
     * Constructeur de la fenetre
     */
    public Fenetre(){

        this.setSize(new Dimension(900, 600));
        this.setTitle("SOKOBAN & IA");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MenuPrincipale mp = new MenuPrincipale(this);
        this.add(mp);

        this.setVisible(true);
    }
}
