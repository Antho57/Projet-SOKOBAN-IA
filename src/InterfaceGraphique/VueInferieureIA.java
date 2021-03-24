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
                menu.getWindow().requestFocus();
                modele.viderSolution();
            }
        });
        this.add(jb1);

        JPanel jpDirecton1 = new JPanel();
        jpDirecton1.setSize(new Dimension(900, 100));
        jpDirecton1.setLayout(new GridLayout(1,2));
        jpDirecton1.setBackground(new Color(134, 134, 134, 0));

        JButton jbFullGauche = new JButton("<<");
        jbFullGauche.setFont(new Font("Sans-serif",Font.BOLD, 20));
        jbFullGauche.setPreferredSize(new Dimension(40, 40));

        jbFullGauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.mouvementCompletIA("Gauche");
            }
        });

        jpDirecton1.add(jbFullGauche);

        JButton jbGauche = new JButton("<");
        jbGauche.setFont(new Font("Sans-serif",Font.BOLD, 20));
        jbGauche.setPreferredSize(new Dimension(40, 40));

        jbGauche.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                modele.mouvementIA("Gauche");
            }
        });
        jpDirecton1.add(jbGauche);

        this.add(jpDirecton1);

        JPanel jpDirecton2 = new JPanel();
        jpDirecton2.setSize(new Dimension(900, 100));
        jpDirecton2.setLayout(new GridLayout(1,2));
        jpDirecton2.setBackground(new Color(134, 134, 134, 0));

        JButton jbDroite = new JButton(">");
        jbDroite.setFont(new Font("Sans-serif",Font.BOLD, 20));
        jbDroite.setPreferredSize(new Dimension(40, 40));

        jbDroite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                modele.mouvementIA("Droite");
            }
        });

        jpDirecton2.add(jbDroite);

        JButton jbFullDroite = new JButton(">>");
        jbFullDroite.setFont(new Font( "Sans-serif",Font.BOLD, 20));
        jbFullDroite.setPreferredSize(new Dimension(40, 40));

        jbFullDroite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.mouvementCompletIA("Droite");
            }
        });

        jpDirecton2.add(jbFullDroite);

        this.add(jpDirecton2);
    }


    /*
    Méthode qui actualise la vue
     */
    public void actualiser(){

    }

}
