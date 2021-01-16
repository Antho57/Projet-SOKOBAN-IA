package InterfaceGraphique;

import Jeu.Labyrinthe;
import Jeu.Sujet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueGagner extends JPanel{

    private Labyrinthe lab;

    private MenuJeu mj;

    public VueGagner(Labyrinthe lab, MenuJeu mj){
        this.lab = lab;
        this.mj=mj;
        this.setSize(new Dimension(767, 955));
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(99, 99, 102));

        JPanel jp = new JPanel();
        jp.setSize(new Dimension(767, 200));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 70));
        jp.setBackground(new Color(99, 99, 102));

        JLabel titre = new JLabel(" VOUS AVEZ GAGNE !!!");
        titre.setFont(new Font("Sans-Serif", Font.BOLD, 50));
        titre.setForeground(new Color(0, 0, 0));
        jp.add(titre);
        this.add(jp);


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 100));
        jp2.setBackground(new Color(99, 99, 102));

        JPanel jp3 = new JPanel();
        jp3.setLayout(new GridLayout(1, 2));

        JButton jb = new JButton("MENU");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Menu();
            }
        });

        jp3.add(jb);

        JButton jb2 = new JButton("REJOUER");
        jb2.setPreferredSize(new Dimension(200, 50));
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rejouer();
            }
        });

        jp3.add(jb2);

        jp2.add(jp3);
        this.add(jp2);

        JPanel jp4 = new JPanel();
        jp4.setSize(new Dimension(767, 200));
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 70));
        jp4.setBackground(new Color(99, 99, 102));

        JLabel info = new JLabel(" AVEC " +this.lab.getMouvements() +" MOUVEMENTS !");
        info.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        info.setForeground(new Color(0, 0, 0));
        jp4.add(info);
        this.add(jp4);

        this.setVisible(true);
    }



    public void Menu(){
        this.mj.retour();
    }

    public void rejouer(){
        this.mj.relancer();
    }


}
