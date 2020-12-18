package InterfaceGraphique;
import javax.swing.*;
import java.awt.*;

public class MenuChoixNiv extends JPanel {

    private Fenetre f;

    public MenuChoixNiv(Fenetre f){
        this.f = f;
        this.f = f;
        this.setSize(new Dimension(900, 600));
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(171, 171, 171));
    }
}
