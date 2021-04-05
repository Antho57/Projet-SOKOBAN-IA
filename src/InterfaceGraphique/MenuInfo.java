package InterfaceGraphique;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
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

/**
 * Class qui represente la page du menu principale
 */

public class MenuInfo extends JPanel {

    private Fenetre f; //fenêtre de l'application

    /*
    Constructeur du menuPrincipale
    @param f, fenêtre de l'application
     */
    public MenuInfo(Fenetre f){

        this.f = f;
        this.setSize(new Dimension(767, 955));
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(78, 48, 150, 0));

        JPanel jp = new JPanel();
        jp.setSize(new Dimension(767, 150));
        jp.setLayout(null);
        jp.setBackground(new Color(78, 48, 150, 0));


        JLabel titre = new JLabel("A propos !");
        titre.setFont(new Font("BUBBLEBOY2", Font.BOLD, 40));
        titre.setForeground(new Color(89, 181, 201));
        titre.setBounds(260,220, 500,100);

        jp.add(titre);

        this.add(jp);

        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setBackground(new Color(78, 48, 150, 0));

        JTextArea titreProjet = new JTextArea("Le jeu :\n"+
                "- Sokoban est un casse-tête dans lequel le but est de placer toutes les caisses sur des emplacements.\n"+
                "- Le joueur controle un personnage qui peut bouger dans quatre directions : haut, bas, gauche, droite.\n"+
                "- Une caisse ne peut être poussée que s’il n'y a rien derrière elle, si elle se trouve contre un mur ou une autre caisse cette caisse est donc bloquée.\n"+
                "- Le joueur doit donc pousser les caisses pour les placer sur leurs emplacements, si toutes les caisses sont bien palcées, la partie est gagnée.\n\n" +
                "L'IA :\n" +
                "- Pour notre IA, nous avons utilisé l'algorithme de recherche de chemin A* (exemple ci-dessous).\n"+
                "- Cet algorithme calcule la distance la plus courte possible entre l'emplacement courant et l'objectif à atteindre sans se préoccuper des obstacles.\n" +
                "- Grace à ce calcule nous allons pouvoir trier les déplacements possibles, cela va donc permettre de guider l’IA dans ses choix.\n\n");
        titreProjet.setLineWrap(true);
        titreProjet.setWrapStyleWord(true);
        titreProjet.setEditable(false);
        titreProjet.setOpaque(false);
        titreProjet.setSize(new Dimension(720, 310));

        titreProjet.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        titreProjet.setForeground(Color.lightGray);

        jp2.add(titreProjet);

        this.add(jp2);

        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(400, 150));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 15) );
        jp3.setBackground(new Color(78, 48, 150, 0));

        JLabel gif = new JLabel(new ImageIcon("Ressources/Gif/A"));


        ImageIcon icon = new ImageIcon("Ressources/Gif/Sokoban_ani.gif");
        Image scaleImage = icon.getImage().getScaledInstance(205, 250,Image.SCALE_DEFAULT);
        ImageIcon imageResize = new ImageIcon(scaleImage);
        JLabel gif2 = new JLabel(imageResize);

        buttonCurved jb = new buttonCurved("J'ai compris");
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                goChoisirNiv();
            }
        });

        jp3.add(gif);
        jp3.add(jb);
        jp3.add(gif2);

        this.add(jp3);

    }

    /*
    Méthode qui permet d'afficher le menu pour afficher les infos sur le projet
     */
    public void goChoisirNiv(){

        MenuChoixNiv m = new MenuChoixNiv(this.f);
        this.f.changer(m);

    }

    /*
     * Methode qui permet de generer l'image de fond
     * @param g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = null;
        Image imgGif = null;
        try {
            img = ImageIO.read(new File("Ressources/Images/Fond_Sans_Titre.jpg")).getScaledInstance(767, 965, Image.SCALE_REPLICATE);
            //imgGif = ImageIO.read(new File("Ressources/Gif/A")).getScaledInstance(350, 275, Image.SCALE_REPLICATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);
        //g.drawImage(imgGif, 15, 625, null);
    }

}
