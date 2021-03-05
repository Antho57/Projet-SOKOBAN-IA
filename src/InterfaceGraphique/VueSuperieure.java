package InterfaceGraphique;

import Jeu.Labyrinthe;
import Jeu.Sujet;

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
Class qui représente la vue suprérieure dans le jeu
 */
public class VueSuperieure extends JPanel implements Observateur{

    public Labyrinthe lab; //le labyrinthe du jeu ene cour
    private MenuJeu menu; //le menu générale du jeu
    private int mouvements; //le nombre de mouvements effectué par le joueur

    /*
    Constructeur de la vueSuperieure
    @param modele, le labyrinthe courant
    @param menu, le menu générale du jeu
    @param niveau, le numéro du niveau en cour
    @param difficulté, le numéro qui correspond à la difficulté du niveau
     */
    public VueSuperieure(Labyrinthe modele, MenuJeu menu, int niveau, int difficulte){

        this.lab = modele;
        this.menu = menu;
        this.mouvements=0;
        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,3));
        this.setBackground(new Color(26, 45, 85));


        JLabel dep = new JLabel("Deplacement: " + this.mouvements);
        dep.setFont(new Font("BUBBLEBOY2",Font.BOLD, 20));
        dep.setForeground(new Color(89, 181, 201));
        this.add(dep);

        JLabel titre = new JLabel(titre(niveau, difficulte));
        titre.setFont(new Font("BUBBLEBOY2",Font.BOLD, 20));
        titre.setForeground(new Color(89, 181, 201));
        this.add(titre);

        JPanel jp = new JPanel();
        jp.setSize(new Dimension(50, 100));
        jp.setLayout(new GridLayout(1,2));
        jp.setBackground(new Color(26, 45, 85));

        JButton jb1 = new JButton("Menu");
        jb1.setFont(new Font("BUBBLEBOY2",Font.BOLD, 10));
        jb1.setPreferredSize(new Dimension(40, 40));
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.retour();
            }
        });

        JButton jb2 = new JButton("Aide");
        jb2.setFont(new Font("BUBBLEBOY2",Font.BOLD, 10));
        jb2.setPreferredSize(new Dimension(40, 40));

        JButton jb3 = new JButton("Reset");
        jb3.setFont(new Font("BUBBLEBOY2",Font.BOLD, 10));
        jb3.setPreferredSize(new Dimension(70, 40));
        jb3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menu.relancer();
            }
        });

        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb1);
        this.add(jp);
    }


    /*
    Méthode qui renvoi le titre qui représente le niveau et la difficulté du niveau
    @param niv, le niveau courant
    @param dif, la difficulté du niveau
     */
    public String titre(int niv, int dif){
        String s = "<html><p style='text-align: CENTER'>Niveau " +niv;
        switch(dif){
            case(0):
                s +=" &emsp Facile</p></html>";
                break;
            case(1):
                s +=" &emsp Normal</p></html>";
                break;
            case(2):
                s +=" &emsp Difficile</p></html>";
                break;
        }
        return s;
    }


    /*
    Méthode qui permet d'actualiser la vue apres un changement
    @param s, le sujet courant (labyrinthe)
     */
    @Override
    public void actualiser(Sujet s) {
        this.lab = (Labyrinthe) s;
        this.mouvements=lab.getMouvements();
        JLabel[] j = new JLabel[2];
        int i= 0;
        for (Component c : this.getComponents()){
            if (c instanceof JLabel){
                j[i]= (JLabel) c;
                i++;
            }
        }
        if (this.mouvements <= 1) {
        	j[0].setText("Deplacement: " + this.mouvements);
		} else {
			j[0].setText("Deplacements: " + this.mouvements);
		}
        this.repaint();
        this.revalidate();
    }
}
