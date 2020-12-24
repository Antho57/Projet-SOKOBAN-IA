package InterfaceGraphique;

import Jeu.ModeleLabyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueInferieureJoueur extends JPanel {

    public ModeleLabyrinthe modele;
    private MenuJeu menu;

    public VueInferieureJoueur(ModeleLabyrinthe modele, MenuJeu menu){

        this.modele = modele;
        this.menu = menu;

        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,2));
        this.setBackground(new Color(134, 134, 134));


        JButton jb1 = new JButton("IA");
        jb1.setPreferredSize(new Dimension(40, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.modeIA();
            }
        });
        this.add(jb1);


        JSlider S = new JSlider();
        JLabel titre = new JLabel("");
        titre.setFont(new Font("Sans-Serif",Font.BOLD, 20));
        titre.setForeground(new Color(195, 195, 195));
        this.add(S);
    }


    public void actualiser(){

    }

}
