package InterfaceGraphique;

import Jeu.Labyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class VueInferieurIA qui représente la vue du mode IA
 */
public class VueInferieureIA extends JPanel{

    public Labyrinthe modele; //Labyrinthe courant
    private MenuJeu menu; //menu génerale du jeu

    /*
    Constructeur de la VueInferieurIA
    @param modele, labyrinthe courant
    @param menu, menu génerale du jeu
     */
    public VueInferieureIA(Labyrinthe modele, MenuJeu menu){

        this.modele = modele;
        this.menu = menu;

        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,4));
        this.setBackground(new Color(134, 134, 134));


        JButton jb1 = new JButton("JOUEUR");
        jb1.setFont(new Font("BUBBLEBOY2",Font.BOLD, 10));
        jb1.setPreferredSize(new Dimension(40, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.modeJoueur();
            }
        });
        this.add(jb1);


        JSlider S = new JSlider();
        JLabel titre = new JLabel("");
        titre.setFont(new Font("Sans-Serif",Font.BOLD, 20));
        titre.setForeground(new Color(195, 195, 195));
        this.add(S);

        JButton jb2 = new JButton("AUtO");
        jb2.setFont(new Font("BUBBLEBOY2",Font.BOLD, 10));
        jb2.setPreferredSize(new Dimension(40, 40));
        this.add(jb2);
    }


    /*
    Méthode qui actualise la vue
     */
    public void actualiser(){

    }

}
