package InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class VueInferieure extends JPanel {

    public VueInferieure(){

        this.setSize(new Dimension(900, 100));
        this.setLayout(new GridLayout(1,4));
        this.setBackground(new Color(134, 134, 134));


        JButton jb1 = new JButton("IA");
        jb1.setPreferredSize(new Dimension(40, 40));
        this.add(jb1);

        JLabel titre = new JLabel("LIGNE");
        titre.setFont(new Font("Sans-Serif",Font.BOLD, 20));
        titre.setForeground(new Color(195, 195, 195));
        this.add(titre);

        JButton jb2 = new JButton("AUTO");
        jb1.setPreferredSize(new Dimension(40, 40));
        this.add(jb2);
    }


    public void actualiser(){

    }

}
