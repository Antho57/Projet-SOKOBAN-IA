package InterfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class buttonCurved extends JButton {

    private Color startColor = new Color(150, 149, 149);
    private Color endColor = new Color(150, 149, 149);
    private Color rollOverColor = new Color(89, 181, 201);
    private Color pressedColor = new Color(89, 181, 201);
    private GradientPaint GP;

    /**
     * Constructor takes String argument
     * @param text
     */
    public buttonCurved(String text) {
        super();
        super.setPreferredSize(new Dimension(150, 50));
        setText(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(new Font("Thoma", Font.BOLD, 12));
        setForeground(Color.WHITE);
        setFocusable(false);

    }

    /**
     *
     * @param startColor
     * @param endColor
     * @param rollOverColor
     * @param pressedColor
     */
    public buttonCurved(Color startColor, Color endColor, Color rollOverColor, Color pressedColor) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
        this.rollOverColor = rollOverColor;
        this.pressedColor = pressedColor;
        setForeground(Color.WHITE);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

    }

    /**
     *
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int h = getHeight();
        int w = getWidth();
        ButtonModel model = getModel();

        if (!model.isEnabled()) {
            setForeground(Color.GRAY);
            GP = new GradientPaint(0, 0, new Color(192,192,192), 0, h, new Color(192,192,192),
                    true);
        }else{
            setForeground(Color.WHITE);
            if (model.isRollover()) {
                GP = new GradientPaint(0, 0, rollOverColor, 0, h, rollOverColor,
                        true);
                this.getParent().getParent().repaint();

            } else {
                GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
                this.getParent().getParent().repaint();
            }
        }
        g2d.setPaint(GP);
        GradientPaint p1;
        GradientPaint p2;

        if (model.isPressed()) {
            GP = new GradientPaint(0, 0, pressedColor, 0, h, pressedColor, true);
            g2d.setPaint(GP);
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
                    new Color(100, 100, 100));
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                    new Color(255, 255, 255, 100));
        } else {
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
                    new Color(0, 0, 0));
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
                    h - 3, new Color(0, 0, 0, 50));
            GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
        }

        //Création de la forme du button
        Shape clip = g2d.getClip();
        Rectangle2D.Float r2d = new Rectangle2D.Float(0, 0, 149, 49);
        g2d.clip(r2d);
        g2d.fillRoundRect(0, 0, 150, 50, 50, 50);
        g2d.setClip(clip);
        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, 149, 49, 50, 50);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, 147, 47, 50, 50);
        g2d.dispose();

        super.paintComponent(g);
    }

    /**
     *  This method sets the Actual Background Color of the Button
     */
    public void setStartColor(Color color) {
        startColor = color;
    }

    /**
     *  This method sets the Pressed Color of the Button
     */
    public void setEndColor(Color pressedColor) {
        endColor = pressedColor;
    }

    /**
     * @return  Starting Color of the Button
     */
    public Color getStartColor() {
        return startColor;
    }

    /**
     * @return  Ending Color of the Button
     */
    public Color getEndColor() {
        return endColor;
    }
}