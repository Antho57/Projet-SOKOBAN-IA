package InterfaceGraphique;


import IA.IA;
import Jeu.Labyrinthe;
import Jeu.Sujet;
import IA.LancementIA;
import IA.heuristiques.HeuristiqueCaisseEtPersonnage;
import IA.heuristiques.HeuristiqueCaisseEtPlusGrandPersonnage;

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
Class qui représente la vue inférieure lors du lancement de l'IA
 */
public class VueInferieureLancementIA extends JPanel implements Observateur{


    public Labyrinthe modele; //labyrinthe courant
    private MenuJeu menu; //Menu générale du jeu
    private IA ia; //IA courante qui recherche la solution du labyrinthe



    public VueInferieureLancementIA(Labyrinthe modele, MenuJeu menu){
        this.modele = modele;
        this.menu = menu;
        this.ia = new IA(modele, new HeuristiqueCaisseEtPlusGrandPersonnage());
        this.ia.enregistrerObservateur(this);

        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,3));
        this.setBackground(new Color(134, 134, 134));




        JPanel jpListeOuverte = new JPanel();
        jpListeOuverte.setSize(new Dimension(50, 100));
        jpListeOuverte.setLayout(null);
        jpListeOuverte.setBackground(new Color(26, 45, 85));

        JLabel nbListeOuverte = new JLabel("ListeOuverte : " + this.ia.getListeOuverte().size());
        nbListeOuverte.setFont(new Font("BUBBLEBOY2",Font.BOLD, 14));
        nbListeOuverte.setForeground(new Color(89, 181, 201));
        nbListeOuverte.setBounds(5, -15, 300, 80);
        jpListeOuverte.add(nbListeOuverte);
        this.add(jpListeOuverte);


        JPanel jpListeFerme = new JPanel();
        jpListeFerme.setSize(new Dimension(50, 100));
        jpListeFerme.setLayout(null);
        jpListeFerme.setBackground(new Color(26, 45, 85));

        JLabel nbListeFerme = new JLabel("ListeFerme : " + this.ia.getListeFerme().size());
        nbListeFerme.setFont(new Font("BUBBLEBOY2",Font.BOLD, 14));
        nbListeFerme.setForeground(new Color(89, 181, 201));
        nbListeFerme.setBounds(5, -15, 300, 80);
        jpListeFerme.add(nbListeFerme);
        this.add(jpListeFerme);



        JPanel jpBouttons = new JPanel();
        jpBouttons.setSize(50, 100);
        jpBouttons.setLayout(new GridLayout(1, 2));

        JButton jb = new JButton("Jouer");
        jb.setFont(new Font("Sans-serif",Font.BOLD, 14));
        jb.setPreferredSize(new Dimension(40, 40));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.modeIA();
            }
        });
        jb.setEnabled(false);
        jpBouttons.add(jb);

        Thread t = new Thread(new LancementIA(this.ia, this));
        t.start();

        JButton jb1 = new JButton("Stop");
        jb1.setFont(new Font("Sans-serif",Font.BOLD, 14));
        jb1.setPreferredSize(new Dimension(40, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
                menu.modeJoueur();
                menu.getWindow().requestFocus();
                modele.viderSolution();
            }
        });
        jpBouttons.add(jb1);
        this.add(jpBouttons);


    }


    public void afficherIA(){
        this.menu.modeIA();
    }

    @Override
    public void actualiser(Sujet s) {
        this.ia = (IA) s;
        JLabel[] j = new JLabel[2];
        int i= 0;
        for (Component c : this.getComponents()){
            if (c instanceof JPanel) {
                if (((JPanel) c).getComponent(0) instanceof JLabel){
                    JLabel jl = (JLabel) ((JPanel) c).getComponent(0);
                    j[i] = jl;
                    i++;
                }
            }
        }
        j[0].setText("ListeOuverte : " + this.ia.getListeOuverte().size());
        j[1].setText("ListeFerme : " + this.ia.getListeFerme().size());

        this.repaint();
        this.revalidate();
    }




}
