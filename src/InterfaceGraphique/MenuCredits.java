package InterfaceGraphique;

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
Class qui représente le MenuCredits
 */
public class MenuCredits extends JPanel{

    private Fenetre f; //fenêtre de l'application

    /*
    Contructeur du menuCredits
    @param f, fenêtre de l'application
     */
    public MenuCredits(Fenetre f){

        this.f = f;
        this.setSize(new Dimension(767, 955));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(78, 48, 150, 0));


        JPanel jp = new JPanel();
        jp.setSize(new Dimension(767, 200));
        jp.setLayout(null);
        jp.setBackground(new Color(78, 48, 150, 0));


        JLabel titre = new JLabel("<html> <p style='text-align: center; text-decoration: underline'> Credits</p> <br> " +
                "<p style='text-align: center; text-decoration: underline'>Createurs </p><br> " +
                "<p style='text-align: center'>Briot Anthony <br><br> Vrignon Quentin <br><br> Nicol Benoit <br><br> Saker Lucas </p><br><br>" +
                "<p style='text-align: center; text-decoration: underline'>tuteur </p><br>" +
                "<p style='text-align: center'>Vicent thomas </p><br><br>" +
                "<p style='text-align: center'> Gif A* : Subh83</p><br>" +
                "<p style='text-align: center'>Gif Sokoban : Borgar Þorsteinsson </p></html>");
        titre.setFont(new Font("BUBBLEBOY2" , Font.PLAIN, 23));
        titre.setForeground(new Color(89, 181, 201));
        titre.setBounds(150, 80 ,800, 800);
        jp.add(titre);
        this.add(jp, BorderLayout.CENTER);


        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 50));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 50));
        jp3.setBackground(new Color(78, 48, 150, 0));

        buttonCurved jb2= new buttonCurved("Retour");
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retour();
            }
        });

        jp3.add(jb2);

        this.add(jp3, BorderLayout.SOUTH);
        this.repaint();

    }

    /*
    Méthode qui permet de revenir au menu principale
     */
    public void retour(){

        MenuPrincipale m = new MenuPrincipale(this.f);
        this.f.changer(m);

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
