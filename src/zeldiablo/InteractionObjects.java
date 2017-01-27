package zeldiablo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * This class is made to identify which GameObjects are meant to be interacted with. 
 * It contains the entities like chests
 * 
 * @author Auer Marco
 */
public class InteractionObjects extends GameObjects {

    public final String imageDirectory;
    private ImageIcon sprite;
    private final String[] spriteList;
    private String id;

    /**
     * The constructor has a spritelist just like in a few of the other classes
     * 
     * @param position Where to spawn it
     * @param width Width of the entity
     * @param height Height of the entity
     * @param thing Which object it is. example "Chest1" 
     * @param id The "name" of the chest. The ID determines which loot you get from which chest
     */
    public InteractionObjects(Coordinates position, int width, int height, String thing, String id) {
        super(position, width, height);
        imageDirectory = "images/objects/";
        spriteList = new String[]{thing + "_deactivated.png", thing + "_activated.png"};
        setSprite(0);
        this.id = id;
    }

    /**
     * First it sets the path consisting of the directory and the String from
     * the spriteList. For example: "images/attacks/sword1_animation1.png"
     * Second it creates an imageURL with the newly defined imagePath Last it
     * will create an ImageIcon object, the sprite, according to the newly
     * created imageURL
     *
     * @param imageNumber decides what index of the spriteList will be accessed
     */
    public void setSprite(int imageNumber) {
        String imagePath = imageDirectory + spriteList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        sprite = new ImageIcon(imageURL);
    }
    
    public String getID() {
        return id;
    }
    
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Draws the objects
     * @param g graphic
     */
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
