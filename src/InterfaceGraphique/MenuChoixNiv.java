package InterfaceGraphique;
import Jeu.Labyrinthe;
import Jeu.Personnage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuChoixNiv extends JPanel {

    private Fenetre f;
    private int niveau;
    private int difficulte;

    public MenuChoixNiv(Fenetre f){
        this.f = f;
        this.setSize(new Dimension(760, 955));
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(78, 48, 150));


        JPanel jp = new JPanel();
        jp.setSize(new Dimension(760, 200));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 70));
        jp.setBackground(new Color(78, 48, 150));


        JLabel titre = new JLabel("Choix du niveau");
        titre.setFont(new Font("Sans-Serif", Font.BOLD, 50));
        titre.setForeground(new Color(195, 195, 195));
        jp.add(titre);
        this.add(jp);


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(760, 200));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 100));
        jp2.setBackground(new Color(78, 48, 150));

        JPanel jp3 = new JPanel();
        jp3.setLayout(new GridLayout(1, 2));


        String[] niveaux = {"Niveau 1", "Niveau 2", "Niveau 3", "Niveau 4", "Niveau 5"};
        String[] difficulte = {"Facile", "Normale", "Difficile"};

        JComboBox jcb1 = new JComboBox(niveaux);
        jcb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                choixNiveau(jcb.getSelectedIndex());
            }
        });

        JComboBox jcb2= new JComboBox(difficulte);
        jcb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                choixDifficulte(jcb.getSelectedIndex());
            }
        });
        jcb1.setPreferredSize(new Dimension(300, 50));

        jp3.add(jcb1);
        jp3.add(jcb2);
        jp2.add(jp3);
        this.add(jp2);

        JPanel jp4 = new JPanel();
        jp4.setSize(new Dimension(760, 200));
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 40));
        jp4.setBackground(new Color(78, 48, 150));


        JButton jb = new JButton("Jouer");
        jb.setPreferredSize(new Dimension(300, 50));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jouer();
            }
        });

        jp4.add(jb);
        this.add(jp4);

    }

    public void jouer(){

        int[] choix= {this.niveau, this.difficulte};
        MenuJeu m = new MenuJeu(this.f, choix, new Labyrinthe(1));
        this.f.changer(m);

    }

    public void choixNiveau (int n){
        this.niveau=n;
    }

    public void choixDifficulte (int d){
        this.difficulte=d;
    }

}
