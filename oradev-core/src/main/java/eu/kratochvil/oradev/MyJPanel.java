package eu.kratochvil.oradev;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MyJPanel extends JPanel {
    private static Random generator = new Random();
    private ImageIcon picture; // image to be displayed
    private static final String[] images = {"yellowflowers.png", "purpleflowers.png",
            "redflowers.png", "redflowers2.png", "lavenderflowers.png"};

    // load image
    public MyJPanel() {
        int randomNumber = generator.nextInt(5);
        picture = new ImageIcon(images[randomNumber]); // set icon
    } // end MyJPanel constructor

    // display imageIcon on panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        picture.paintIcon(this, g, 0, 0); // display icon
    } // end method paintComponent

    // return image dimensions
    public Dimension getPreferredSize() {
        return new Dimension(picture.getIconWidth(),
                picture.getIconHeight());
    } // end method getPreferredSize
}