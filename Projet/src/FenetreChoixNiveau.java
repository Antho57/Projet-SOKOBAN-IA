import javax.swing.*;
import java.awt.*;

public class FenetreChoixNiveau extends JFrame {

    public FenetreChoixNiveau(){

        this.setSize(new Dimension(900, 600));
        this.setTitle("SOKOBAN & IA");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MenuChoixNiv mcn = new MenuChoixNiv(this);
        this.add(mcn);

        this.setVisible(true);
    }


}
