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
        this.setBackground(new Color(78, 48, 150));


        JPanel jp = new JPanel();
        jp.setSize(new Dimension(767, 150));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 70));
        jp.setBackground(new Color(78, 48, 150));


        JLabel titre = new JLabel("A propos !");
        titre.setFont(new Font("Sans-Serif", Font.ITALIC, 40));
        titre.setForeground(Color.white);
        
        jp.add(titre);
        
        this.add(jp);


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setBackground(new Color(78, 48, 150));
        
        
        
        JTextArea titreProjet = new JTextArea("Le projet :\n\n Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
        		+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n\n"
        		+ "L'IA :\n\n Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
        		+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        titreProjet.setLineWrap(true);
        titreProjet.setWrapStyleWord(true);
        titreProjet.setEditable(false);
        titreProjet.setOpaque(false);
        titreProjet.setSize(new Dimension(740, 310));
        
        titreProjet.setFont(new Font("Sans-Serif", Font.PLAIN, 17));
        titreProjet.setForeground(Color.black);
        
        jp2.add(titreProjet);

        this.add(jp2);

        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 150));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 567, 130));
        jp3.setBackground(new Color(78, 48, 150));
        
        JButton jb = new JButton("J'ai compris");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                goChoisirNiv();
            }
        });
        
        jp3.add(jb);

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
		try {
			img = ImageIO.read(new File("Ressources/Images/Fond_Sans_Titre.jpg")).getScaledInstance(767, 965, Image.SCALE_REPLICATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);
	}

}
