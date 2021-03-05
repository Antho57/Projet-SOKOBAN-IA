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
        this.setLayout(new GridLayout(3, 1));
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


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 180));
        jp2.setBackground(new Color(78, 48, 150, 0));


        JButton jb = new JButton("Jouer");
        jb.setPreferredSize(new Dimension(300, 50));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jouer();
            }
        });
        
        JButton jb3 = new JButton("Quitter");
        jb3.setPreferredSize(new Dimension(200, 50));
        jb3.setBackground(new Color(195, 195, 195));
        jb3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                System.out.println("Vous avez quitt�.");
            }
        });

        jp2.add(jb);

        jp2.add(jb3);

        this.add(jp2);

        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 200));
        jp3.setLayout(new FlowLayout(FlowLayout.LEFT, 567, 130));
        jp3.setBackground(new Color(78, 48, 150, 0));

        JButton jb2= new JButton("Credits");
        jb2.setPreferredSize(new Dimension(75, 50));
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                credits();
            }
        });
        
//        JButton jb3= new JButton("Quitter");
//        jb3.setPreferredSize(new Dimension(75, 50));
//        jb3.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });

        jp3.add(jb2);
//        jp3.add(jb3);


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
