package InterfaceGraphique;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

public class MenuPrincipale extends JPanel {

    private Fenetre f; //fenêtre de l'application

    /*
    Constructeur du menuPrincipale
    @param f, fenêtre de l'application
     */
    public MenuPrincipale(Fenetre f) {

        this.f = f;
        this.setSize(new Dimension(767, 955));
        this.setLayout(new GridLayout(5, 1));
        this.setBackground(new Color(78,48,150, 0));


        JPanel jp = new JPanel();
        jp.setSize(new Dimension(767, 200));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 100));
        jp.setBackground(new Color(78, 48, 150, 0));


        JLabel titre = new JLabel("");
        titre.setFont(new Font("BubbleBoy2", Font.BOLD, 50));
        titre.setForeground(new Color(195, 195, 195));
        jp.add(titre);
        this.add(jp);

        JPanel jp1 = new JPanel();
        jp1.setSize(new Dimension(767, 200));
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 100));
        jp1.setBackground(new Color(78, 48, 150, 0));
        this.add(jp1);


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        jp2.setBackground(new Color(78, 48, 150, 0));


        buttonCurved jb = new buttonCurved("Jouer");
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jouer();
            }
        });

        jp2.add(jb);

        this.add(jp2);

        JPanel jp4 = new JPanel();
        jp4.setSize(new Dimension(767, 200));
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        jp4.setBackground(new Color(78, 48, 150, 0));

        buttonCurved jb3 = new buttonCurved("Quitter");
        jb3.setBackground(new Color(195, 195, 195));
        jb3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jp4.add(jb3);

        this.add(jp4);

        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 200));
        jp3.setLayout(new FlowLayout(FlowLayout.LEFT, 567, 50));
        jp3.setBackground(new Color(78, 48, 150, 0));

        buttonCurved jb2= new buttonCurved("Credits");
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                credits();
            }
        });

        jp3.add(jb2);


        this.add(jp3);
        this.repaint();

    }

    /*
    Méthode qui permet de jouer (affiche le menu de choix de niveau
     */
    public void jouer(){

        MenuInfo m = new MenuInfo(this.f);
        this.f.changer(m);

    }

    /*
    Méthode qui permet d'afficher les crédits
     */
    public void credits(){
        MenuCredits m = new MenuCredits(this.f);
        this.f.changer(m);
    }

    /*
     * Methode qui permet de generer l'image de fond
     * @param g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = null;
        try {
            img = ImageIO.read(new File("Ressources/Images/Fond2.jpg")).getScaledInstance(767, 965, Image.SCALE_REPLICATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);
    }


}
