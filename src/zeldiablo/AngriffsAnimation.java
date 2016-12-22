/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class AngriffsAnimation extends SpielObjekt {

    private int waffenFrames;
    private int invincibilityFrames;
    private boolean waffenSchwungBereit;

    public final String bilderVerzeichnis;
    private ImageIcon sprite;
    private final String[] spriteList;
    private final String animation;
//    private int pfeilDistanz;
//    private int pfeilCounter;

    public AngriffsAnimation(Koordinaten objektPosition, int breite, int hoehe, String animation) {
        super(objektPosition, breite, hoehe);
        waffenFrames = 0;
        invincibilityFrames = 0;
        bilderVerzeichnis = "bilder/angriffe/";
        spriteList = new String[]{animation + "_animation1.png", animation + "_animation2.png", animation + "_animation3.png", animation + "_animation4.png"};
        this.animation = animation;
        setSprite(0);
        waffenSchwungBereit = true;
    }

    public void setSprite(int imageNumber) {
        String imagePath = bilderVerzeichnis + spriteList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        sprite = new ImageIcon(imageURL);
    }

    public int getWaffenFrames() {
        return waffenFrames;
    }

    public void setWaffenFrames(int frames) {
        waffenFrames = frames;
    }

    public boolean getWaffenSchwung() {
        return waffenSchwungBereit;
    }

    public void setWaffenSchwung(boolean waffenSchwung) {
        this.waffenSchwungBereit = waffenSchwung;
    }

    public void nahkampf(Spieler spieler) {
        switch (spieler.getWinkel()) {
            case 1:
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() - 30, (spieler.getObjektPosition().getY() + (int) spieler.getHoehe())), 100, 55, animation));
                break;
            case 2:
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() - 30, (spieler.getObjektPosition().getY() + (int) spieler.getHoehe())), 100, 55, animation));
                break;
            case 3:
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten((spieler.getObjektPosition().getX() - (int) spieler.getBreite() - 10), spieler.getObjektPosition().getY()), 55, 100, animation));
                break;
            case 5:
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() - 30, (spieler.getObjektPosition().getY() - ((int) spieler.getHoehe() / 2))), 100, 55, animation));
                break;
            case 7:
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten((spieler.getObjektPosition().getX() + (int) spieler.getBreite() - 10), spieler.getObjektPosition().getY()), 55, 100, animation));
                break;
            default:
                break;
        }
    }

    public void hitDetektor(Spieler spieler, NPC gegner) {
        if (spieler.getAngriffHitbox().getHitbox().intersects(gegner.getHitbox())) {
            Schadensberechnung schadensberechnung = new Schadensberechnung();
            gegner.getWerte().setHP(gegner.getWerte().getHP() - schadensberechnung.berechnung(spieler, gegner));
            
//            if (spieler.getKlasse() == "Jäger") {
//                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(-1000, -1000), 0, 0, "Schwert1"));
//            }

            System.out.println(schadensberechnung.berechnung(spieler, gegner));
            gegner.setInviFrames(50);
        }

    }

    public void waffenFramesFunktion(Spieler spieler) {
        waffenFrames++;
        if (waffenFrames > 10) {
            spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(-1000, -1000), 0, 0, "Schwert1"));
        }
    }

    @Override
    protected void zeichneObjekte(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        RoundRectangle2D spieler = new RoundRectangle2D.Double(getObjektPosition().getX(), getObjektPosition().getY(),
                getBreite(), getHoehe(), 3, 3);

//        AffineTransform transform = new AffineTransform();        
//        transform.rotate(1, spieler.getCenterX(), spieler.getCenterY());
//        Shape trans = transform.createTransformedShape(spieler);
        g2d.fill(spieler);

        sprite.paintIcon(null, g, getObjektPosition().getX(), getObjektPosition().getY());
    }

}
