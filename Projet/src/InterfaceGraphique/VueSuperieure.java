package InterfaceGraphique;

import Jeu.ModeleLabyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueSuperieure extends JPanel {

    public ModeleLabyrinthe modele;
    private MenuJeu menu;

    public VueSuperieure(ModeleLabyrinthe modele, MenuJeu menu){

        this.modele = modele;
        this.menu = menu;
        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,4));
        this.setBackground(new Color(134, 134, 134));


        JLabel titre = new JLabel("Deplacements : " /*modele.getMouvements()*/);
        titre.setFont(new Font("Sans-Serif",Font.BOLD, 20));
        titre.setForeground(new Color(195, 195, 195));
        this.add(titre);

        JButton jb1 = new JButton("RETOUR");
        jb1.setPreferredSize(new Dimension(100, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menu.retour();
            }
        });

        JButton jb2 = new JButton("AIDE");
        jb2.setPreferredSize(new Dimension(100, 40));

        this.add(jb2);
        this.add(jb1);
    }


    public void actualiser(){

    }


}
