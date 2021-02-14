package Jeu;

import InterfaceGraphique.Observateur;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Interface sujet qui permet de mettre en place le model MVC
 */
public interface Sujet {

    /*
	Méthode qui permet d'enregistrer un observateur
	@param o, observateur qu'il faut enregistrer
	 */
    public void enregistrerObservateur(Observateur o);

    /*
	Méthode qui permet de supprimer un observateur de la liste
	@param o, l'observateur que l'on veut supprimer
	 */
    public void supprimerObservateur(Observateur o);

    /*
	Méthode qui permet de notifier tous les observateurs de la liste
	après la modification d'un attribut de la class
	 */
    public void notifierObservateurs();
}
