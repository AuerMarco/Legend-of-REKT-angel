/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class Gegenstand extends SpielObjekt {

    public final String bilderVerzeichnis;
    private ImageIcon sprite;
    private final String[] spriteList;
    private final String id;

    public Gegenstand(Koordinaten position, int breite, int hoehe, String gegenstand, String id) {
        super(position, breite, hoehe);
        bilderVerzeichnis = "bilder/gegenstand/";
        spriteList = new String[]{gegenstand + "_inaktiv.png", gegenstand + "_aktiv.png"};
        setSprite(0);
        this.id = id;
    }

    public void setSprite(int imageNumber) {
        String imagePath = bilderVerzeichnis + spriteList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        sprite = new ImageIcon(imageURL);
    }

    @Override
    public void zeichneObjekte(java.awt.Graphics g) {

//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.BLUE);
//        RoundRectangle2D spieler = new RoundRectangle2D.Double(getObjektPosition().getX(),
//                getObjektPosition().getY(),
//                getBreite(), getHoehe(), 3, 3);
//        g2d.fill(spieler);
        
        sprite.paintIcon(null, g, getObjektPosition().getX(), getObjektPosition().getY());
    }

}
