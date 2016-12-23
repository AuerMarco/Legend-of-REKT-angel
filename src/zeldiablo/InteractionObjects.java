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

public class InteractionObjects extends GameObjects {

    public final String imageDirectory;
    private ImageIcon sprite;
    private final String[] spriteList;
    private final String id;

    public InteractionObjects(Coordinates position, int width, int height, String thing, String id) {
        super(position, width, height);
        imageDirectory = "images/objects/";
        spriteList = new String[]{thing + "_deactivated.png", thing + "_activated.png"};
        setSprite(0);
        this.id = id;
    }

    public void setSprite(int imageNumber) {
        String imagePath = imageDirectory + spriteList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        sprite = new ImageIcon(imageURL);
    }

    @Override
    public void drawObjects(java.awt.Graphics g) {

//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.BLUE);
//        RoundRectangle2D spieler = new RoundRectangle2D.Double(getObjektPosition().getX(),
//                getObjektPosition().getY(),
//                getWidth(), getHeight(), 3, 3);
//        g2d.fill(spieler);
        
        sprite.paintIcon(null, g, getObjectPosition().getX(), getObjectPosition().getY());
    }

}
