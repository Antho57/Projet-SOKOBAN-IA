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
        
        JTextArea titreProjet = new JTextArea("Le projet :\n Gardien d'entrepôt (divisé en cases carrées), "
        		+ "le joueur doit ranger des caisses sur des cases cibles. Il peut se déplacer dans les quatre directions, "
        		+ "et pousser (mais pas tirer) une seule caisse à la fois. Une fois toutes les caisses rangées (c'est parfois un vrai casse-tête), "
        		+ "le niveau est réussi et le joueur passe au niveau suivant, plus difficile en général. "
        		+ "L'idéal est de réussir avec le moins de coups possibles (déplacements et poussées).\n\n"
        		+ "L'IA :\n A* est un algorithme de résolution que l'on peut représenter sous forme d'arbre d'état. "
        		+ "Chaque état possède une valeur heuristique (composé du coût réel pour arriver au noeud courant + le coût "
        		+ "prévisionnel admissible pour arriver à l'état final) correspondant au coût de la distance entre l'état initial et l'état final. "
        		+ "A chaque itération, on se positionne sur l'état ayant la plus petite valeur heuristique parmi tous les noeuds de l'arbre non visités et "
        		+ "fils d'un noeud déjà visité jusqu'à arriver à l'état final par le chemin le plus court possible.");
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
