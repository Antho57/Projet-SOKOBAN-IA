package InterfaceGraphique;

import Jeu.Labyrinthe;
import Jeu.Sujet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Anthony Briot
 * @author Lucas Saker
 * @author Quentin Vrignon
 * @author Benoit Nicol
 */

/*
Class VueGagner qui représente la vue apres avoir gagne
 */
public class VueGagner extends JPanel{

    private Labyrinthe lab; //labyrinthe courant

    private MenuJeu mj; //menu générale du jeu

    /*
    Constructeur de la vueGagner
    @param lab, labyrinthe courant*
    @param mj, menu générale du jeu
     */
    public VueGagner(Labyrinthe lab, MenuJeu mj){
        this.lab = lab;
        this.mj=mj;
        this.setSize(new Dimension(767, 955));
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(new Color(99, 99, 102, 0));

        JPanel jpnull = new JPanel();
        jpnull.setSize(new Dimension(760, 150));
        jpnull.setLayout(null);
        jpnull.setBackground(new Color(78, 48, 150, 0));
        this.add(jpnull);

        JPanel jptitre = new JPanel();
        jptitre.setSize(new Dimension(767, 200));
        jptitre.setLayout(new GridLayout(2,1));
        jptitre.setBackground(new Color(78, 48, 150, 0));

        JPanel jp = new JPanel();
        jp.setSize(new Dimension(767, 200));
        jp.setLayout(null);
        jp.setBackground(new Color(99, 99, 102, 0));

        JLabel titre = new JLabel(" VOUS AVEZ GAGNE !!!");
        titre.setFont(new Font("BubbleBoy2", Font.BOLD, 30));
        titre.setForeground(new Color(89, 181, 201));
        titre.setBounds(175,50, 500,100);
        jp.add(titre);
        jptitre.add(jp);

        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setLayout(null);
        jp2.setBackground(new Color(99, 99, 102, 0));

        JLabel info = new JLabel(" AVEC " +this.lab.getMouvements() +" MOUVEMENtS !");
        info.setFont(new Font("BubbleBoy2", Font.BOLD, 30));
        info.setForeground(new Color(89, 181, 201));
        info.setBounds(160,50, 500,100);
        jp2.add(info);
        jptitre.add(jp2);
        this.add(jptitre);


        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 200));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 40));
        jp3.setBackground(new Color(78, 48, 150, 0));

        buttonCurved jb = new buttonCurved("MENU");
        jb.setBackground(new Color(0, 0, 0, 0));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Menu();
            }
        });

        jp3.add(jb);
        this.add(jp3);

        JPanel jp4 = new JPanel();
        jp4.setSize(new Dimension(767, 200));
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 40));
        jp4.setBackground(new Color(78, 48, 150, 0));


        buttonCurved jb2 = new buttonCurved("REJOUER");
        jb2.setBackground(new Color(0, 0, 0, 0));
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rejouer();
            }
        });

        jp4.add(jb2);

        this.add(jp4);

        this.setVisible(true);
        this.repaint();
    }

    /*
    Méthode qui permet de revenir au menu principale
     */
    public void Menu(){
        this.mj.retour();
    }

    /*
    Méthode qui permet de relancer le même niveau
     */
    public void rejouer(){
        this.mj.relancer();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = null;
        try {
            img = ImageIO.read(new File("Ressources/Images/Fond_Sans_Titre.jpg")).getScaledInstance(767, 965, Image.SCALE_REPLICATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);
    }


}
