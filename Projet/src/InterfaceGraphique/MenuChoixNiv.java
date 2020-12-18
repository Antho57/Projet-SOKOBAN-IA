package InterfaceGraphique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuChoixNiv extends JPanel {

    private Fenetre f;

    public MenuChoixNiv(Fenetre f){
        this.f = f;
        this.setSize(new Dimension(900, 600));
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(171, 171, 171));


        JPanel jp = new JPanel();
        jp.setSize(new Dimension(900, 200));
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 70));
        jp.setBackground(new Color(171, 171, 171));


        JLabel titre = new JLabel("SOKOBAN & IA");
        titre.setFont(new Font("Sans-Serif", Font.BOLD, 50));
        titre.setForeground(new Color(89, 0, 255));
        jp.add(titre);
        this.add(jp);


        JPanel jp2 = new JPanel();
        jp2.setSize(new Dimension(900, 200));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 100));
        jp2.setBackground(new Color(171, 171, 171));


        JButton jb = new JButton("Jouer");
        jb.setPreferredSize(new Dimension(300, 50));
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jouer();
            }
        });

        jp2.add(jb);

        this.add(jp2);

        
    }

    public void jouer(){

        MenuChoixNiv m = new MenuChoixNiv(this.f);
        this.f.changer(m);

    }
}
