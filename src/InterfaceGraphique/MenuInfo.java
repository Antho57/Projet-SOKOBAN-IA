package InterfaceGraphique;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        jp.setSize(new Dimension(767, 200));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 70));
        jp.setBackground(new Color(78, 48, 150));


        JLabel titre = new JLabel("A propos !");
        titre.setFont(new Font("Sans-Serif", Font.BOLD, 50));
        titre.setForeground(new Color(195, 195, 195));
        
        jp.add(titre);
        
        this.add(jp);


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(767, 200));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 100));
        jp2.setBackground(new Color(78, 48, 150));
        
        JLabel titreProjet = new JLabel("Le projet :");
        titreProjet.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        titreProjet.setForeground(Color.black);
        
        jp2.add(titreProjet);
        
//        JLabel titreIA = new JLabel("L'IA :");
//        titreProjet.setFont(new Font("Sans-Serif", Font.BOLD, 30));
//        titreProjet.setForeground(Color.black);
//        
//        jp2.add(titreIA);

        this.add(jp2);

        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(767, 200));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 567, 130));
        jp3.setBackground(new Color(78, 48, 150));
        
        JButton jb = new JButton("J'ai compris");
        jb.setPreferredSize(new Dimension(300, 50));
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

}
