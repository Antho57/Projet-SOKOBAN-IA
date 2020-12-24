package InterfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCredits extends JPanel{

    private Fenetre f;

    public MenuCredits(Fenetre f){

        this.f = f;
        this.setSize(new Dimension(900, 600));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(78, 48, 150));


        JPanel jp = new JPanel();
        jp.setSize(new Dimension(900, 200));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 30));
        jp.setBackground(new Color(78, 48, 150));


        JLabel titre = new JLabel("<html> <p style='text-align: center; text-decoration: underline'> CREDITS</p> <br> " +
                "<p style='text-align: center; text-decoration: underline'>CREATEURS </p><br> " +
                "<p style='text-align: center'>BRIOT ANTHONY <br> VRIGNON QUENTIN <br> NICOL BENOIT <br> SAKER LUCAS </p><br>" +
                "<p style='text-align: center; text-decoration: underline'>TUTEUR </p><br>" +
                "<p style='text-align: center'>VINCENT THOMAS </html>");
        titre.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        titre.setForeground(new Color(0, 0, 0));
        jp.add(titre);
        this.add(jp, BorderLayout.CENTER);


        JPanel jp3 = new JPanel();
        jp3.setSize(new Dimension(900, 50));
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 5));
        jp3.setBackground(new Color(78, 48, 150));

        JButton jb2= new JButton("Retour");
        jb2.setPreferredSize(new Dimension(75, 50));
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retour();
            }
        });

        jp3.add(jb2);

        this.add(jp3, BorderLayout.SOUTH);

    }

    public void retour(){

        MenuPrincipale m = new MenuPrincipale(this.f);
        this.f.changer(m);

    }


}
