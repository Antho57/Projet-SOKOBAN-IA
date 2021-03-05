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
        titre.setForeground(Color.white);
        titre.setBounds(260,200, 500,100);
        
        jp.add(titre);
        
        this.add(jp);

        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setBackground(new Color(78, 48, 150, 0));
        
        JTextArea titreProjet = new JTextArea("Le projet :\n Gardien d'entrep�t (divis� en cases carr�es), "
        		+ "le joueur doit ranger des caisses sur des cases cibles. Il peut se d�placer dans les quatre directions, "
        		+ "et pousser (mais pas tirer) une seule caisse � la fois. Une fois toutes les caisses rang�es (c'est parfois un vrai casse-t�te), "
        		+ "le niveau est r�ussi et le joueur passe au niveau suivant, plus difficile en g�n�ral. "
        		+ "L'id�al est de r�ussir avec le moins de coups possibles (d�placements et pouss�es).\n\n"
        		+ "L'IA :\n A* est un algorithme de r�solution que l�on peut repr�senter sous forme d�arbre d��tat. "
        		+ "Chaque �tat poss�de une valeur heuristique (compos� du co�t r�el pour arriver au n�ud courant + le co�t "
        		+ "pr�visionnel admissible pour arriver � l'�tat final) correspondant au co�t de la distance entre l��tat initial et l��tat final. "
        		+ "A chaque it�ration, on se positionne sur l��tat ayant la plus petite valeur heuristique parmi tous les n�uds de l�arbre non visit�s et "
        		+ "fils d�un noeud d�j� visit� jusqu�� arriver � l'�tat final par le chemin le plus court possible.");
        titreProjet.setLineWrap(true);
        titreProjet.setWrapStyleWord(true);
        titreProjet.setEditable(false);
        titreProjet.setOpaque(false);
        titreProjet.setSize(new Dimension(740, 310));
        
        titreProjet.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        titreProjet.setForeground(Color.lightGray);
        
        jp2.add(titreProjet);

        this.add(jp2);

        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 150));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 567, 130));
        jp3.setBackground(new Color(78, 48, 150, 0));
        
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
