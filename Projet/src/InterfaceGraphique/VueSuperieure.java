package InterfaceGraphique;

import Jeu.ModeleLabyrinthe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueSuperieure extends JPanel {

    public ModeleLabyrinthe modele;
    private MenuJeu menu;
    private int mouvements;

    public VueSuperieure(ModeleLabyrinthe modele, MenuJeu menu, int niveau, int difficulte){

        this.modele = modele;
        this.menu = menu;
        this.mouvements=0;
        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,3));
        this.setBackground(new Color(134, 134, 134));


        JLabel deplacement = new JLabel("Deplacements :  " + this.mouvements);
        deplacement.setFont(new Font("Sans-Serif",Font.BOLD, 20));
        deplacement.setForeground(new Color(195, 195, 195));
        this.add(deplacement);

        JLabel titre = new JLabel(titre(niveau, difficulte));
        titre.setFont(new Font("Sans-Serif",Font.BOLD, 20));
        titre.setForeground(new Color(195, 195, 195));
        this.add(titre);

        JPanel jp = new JPanel();
        jp.setSize(new Dimension(50, 100));
        jp.setLayout(new GridLayout(1,2));
        jp.setBackground(new Color(255, 5, 204));

        JButton jb1 = new JButton("MENU");
        jb1.setPreferredSize(new Dimension(50, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.retour();
            }
        });

        JButton jb2 = new JButton("AIDE");
        jb2.setPreferredSize(new Dimension(50, 40));

        jp.add(jb2);
        jp.add(jb1);
        this.add(jp);
    }


    public void actualiser(){
        this.mouvements=modele.getMouvements();
    }

    public String titre(int niv, int dif){
        String s = "<html><p style='text-align: CENTER'>Niveau n°" +niv;
        switch(dif){
            case(0):
                s +=" &emsp Facile</p></html>";
                break;
            case(1):
                s +=" &emsp Normale</p></html>";
                break;
            case(2):
                s +=" &emsp Difficile</p></html>";
                break;
        }
        return s;
    }


}
