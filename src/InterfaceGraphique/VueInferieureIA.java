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


        JButton jb1 = new JButton("Joueur");
        jb1.setFont(new Font("Sans-serif",Font.BOLD, 14));
        jb1.setPreferredSize(new Dimension(40, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.modeJoueur();
            }
        });
        this.add(jb1);


//        JSlider S = new JSlider();
//        JLabel titre = new JLabel("");
//        titre.setFont(new Font("Sans-Serif",Font.BOLD, 20));
//        titre.setForeground(new Color(195, 195, 195));
//        this.add(S);
        JPanel jpDirection = new JPanel();
        jpDirection.setSize(new Dimension(900, 100));
        jpDirection.setLayout(new GridLayout(1,2));
        jpDirection.setBackground(new Color(134, 134, 134, 0));

        JButton jbGauche = new JButton("<");
        jbGauche.setFont(new Font("Sans-serif",Font.BOLD, 20));
        jbGauche.setPreferredSize(new Dimension(40, 40));

        jbGauche.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.avancerIA("Gauche");
            }
        });

        JButton jbDroite = new JButton(">");
        jbDroite.setFont(new Font("Sans-serif",Font.BOLD, 20));
        jbDroite.setPreferredSize(new Dimension(40, 40));

        jbDroite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.avancerIA("Droite");
            }
        });

        jpDirection.add(jbGauche);
        jpDirection.add(jbDroite);

        this.add(jpDirection);


        JButton jb2 = new JButton("Auto");
        jb2.setFont(new Font("Sans-serif",Font.BOLD, 14));
        jb2.setPreferredSize(new Dimension(40, 40));
        this.add(jb2);
    }


    /*
    Méthode qui actualise la vue
     */
    public void actualiser(){

    }

}
