

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;

import InterfaceGraphique.Fenetre;

/**
 * Class principale qui permet de lancer le jeu
 */

public class Principale {

    /**
     * Methode principale pour lancer le jeu
     */

    public static void main(String[] args) {

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Ressources/font/BUBBLEBOY2.ttf")));
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }

        Fenetre jeu = new Fenetre();
    }
    
}
