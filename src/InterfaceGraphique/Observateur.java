package InterfaceGraphique;

import Jeu.Sujet;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Interface observateur pour la model MVC
 */
public interface Observateur {

    public void actualiser(Sujet s); //Méthode qui permet d'actualiser la l'observateur
}
