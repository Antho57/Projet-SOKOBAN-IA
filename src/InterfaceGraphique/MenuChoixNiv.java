package InterfaceGraphique;
import Jeu.Labyrinthe;
import Jeu.Personnage;

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
Class qui représente le MenuChoixNiv
 */
public class MenuChoixNiv extends JPanel {

	private Fenetre f; //fenêtre de l'application
	private int niveau; //niveau courant
	private int difficulte; //difficulté du niveau courant

	/*
    Constructeur du MenuChoixNiv
    @param f, fenêtre de l'apllication
	 */
	public MenuChoixNiv(Fenetre f){
		this.f = f;
		this.setSize(new Dimension(760, 955));
		this.setLayout(new GridLayout(3, 1));
		this.setBackground(new Color(78, 48, 150, 0));


		JPanel jp = new JPanel();
		jp.setSize(new Dimension(760, 150));
		jp.setLayout(null);
		jp.setBackground(new Color(78, 48, 150, 0));
		this.add(jp);

		JLabel titre = new JLabel("Choix du niveau");
		titre.setFont(new Font("BUBBLEBOY2", Font.BOLD, 45));
		titre.setForeground(new Color(89, 181, 201));
		titre.setBounds(145,20, 500,100);


		JPanel jp2 = new JPanel();
		jp2.setSize(new Dimension(760, 200));
		jp2.setLayout(new GridLayout(2,1));
		jp2.setBackground(new Color(78, 48, 150, 0));

		JPanel jpaux = new JPanel();
		jpaux.setLayout(null);
		jpaux.setBackground(new Color(78, 48, 150, 0));
		jpaux.add(titre);
		jp2.add(jpaux);

		JPanel jpaux2 = new JPanel();
		jpaux2.setLayout(null);
		jpaux2.setBackground(new Color(78, 48, 150, 0));
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(1, 2));

		String[] niveauxF = {"Niveau 1", "Niveau 2", "Niveau 3", "Niveau 4", "Niveau 5"};
		String[] niveauxN = {"Niveau 6", "Niveau 7", "Niveau 8", "Niveau 9", "Niveau 10"};
		String[] niveauxD = {"Niveau 11", "Niveau 12", "Niveau 13", "Niveau 14", "Niveau 15"};
		String[] difficulte = {"Facile", "Normal", "Difficile"};

		JComboBox jcb1 = new JComboBox(niveauxF);
		jcb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				choixNiveau(jcb.getSelectedIndex());
			}
		});
		jp3.setBounds(170,50, 400, 50);

		JComboBox jcb2= new JComboBox(difficulte);
		jcb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				choixDifficulte(jcb.getSelectedIndex());
				switch (jcb.getSelectedIndex()) {
					case 0:
						jcb1.setModel(new DefaultComboBoxModel<String>(niveauxF));
						repaint();
						break;
					case 1:
						jcb1.setModel(new DefaultComboBoxModel<String>(niveauxN));
						repaint();
						break;
					case 2:
						jcb1.setModel(new DefaultComboBoxModel<String>(niveauxD));
						repaint();
						break;

					default:
						break;
				}
			}
		});



		jcb1.setPreferredSize(new Dimension(300, 50));

		jp3.add(jcb1);
		jp3.add(jcb2);
		jpaux2.add(jp3);
		jp2.add(jpaux2);
		this.add(jp2);

		JPanel jp4 = new JPanel();
		jp4.setSize(new Dimension(760, 200));
		jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 40));
		jp4.setBackground(new Color(78, 48, 150, 0));


		buttonCurved jb = new buttonCurved("Jouer");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jouer();
			}
		});

		buttonCurved jb2 = new buttonCurved("Retour");
		jb2.setBackground(new Color(195, 195, 195));
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//retour en arriere vers le menu principale
				retourMenuPrincipale();
			}
		});

		jp4.add(jb);
		jp4.add(jb2);

		this.add(jp4);
		this.repaint();

	}

	/*
    Méthode qui permet de lancer le jeu avec le niveau choisi
	 */
	public void jouer(){

		int[] choix= {this.niveau+1, this.difficulte};
		MenuJeu m = new MenuJeu(this.f, choix, new Labyrinthe(this.niveau+1));
		this.f.changer(m);

	}

	/*
    Méthode qui permet de changer le niveau choisi
    @param n, le niveau choisi
	 */
	public void choixNiveau (int n){
		this.niveau=n;
	}

	/*
    Méthode qui permet de changer la difficulté choisi
    @param d, la difficulté choisi
	 */
	public void choixDifficulte (int d){
		this.difficulte=d;
	}

	/*
	 * Methode qui permet de revenir au menu principale
	 */
	public void retourMenuPrincipale(){
		MenuPrincipale m = new MenuPrincipale(this.f);
		this.f.changer(m);
	}

	/*
	 * Methode qui permet de se diriger vers le  menu des infos du projet
	 */
	public void goInfo(){
		MenuInfo m = new MenuInfo(this.f);
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
