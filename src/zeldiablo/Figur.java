/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class Figur extends SpielObjekt {

    private boolean inBewegung;
    public final String bilderVerzeichnis;
    private ImageIcon sprite;
    private final String[] spriteList;
    private int spriteCounter;
    private Dialog npcdialog;
    private String klasse;
    private String name;
    private AngriffsAnimation angriffHitbox;
    private boolean pfeilAktiv;
    int inviFrames;
    private boolean amLeben;

    private int level;
    private int xp;
    private int xpBenoetigt;
    private Werte werte;
    private Waffe waffe;

    public Figur(Koordinaten position, int breite, int hoehe, int winkel, String klasse, String name, int level, int schaden) {
        super(position, breite, hoehe);
        setWinkel(winkel);
        inBewegung = false;
        bilderVerzeichnis = "bilder/sprites/";
        this.klasse = klasse;
        this.name = name;
        spriteList = new String[]{klasse + "_unten_idle.png", klasse + "_unten_move1.png", klasse + "_unten_move2.png",
            klasse + "_untenLinks_idle.png", klasse + "_untenLinks_move1.png", klasse + "_untenLinks_move2.png",
            klasse + "_links_idle.png", klasse + "_links_move1.png", klasse + "_links_move2.png",
            klasse + "_obenLinks_idle.png", klasse + "_obenLinks_move1.png", klasse + "_obenLinks_move2.png",
            klasse + "_oben_idle.png", klasse + "_oben_move1.png", klasse + "_oben_move2.png",
            klasse + "_obenRechts_idle.png", klasse + "_obenRechts_move1.png", klasse + "_obenRechts_move2.png",
            klasse + "_rechts_idle.png", klasse + "_rechts_move1.png", klasse + "_rechts_move2.png",
            klasse + "_untenRechts_idle.png", klasse + "_untenRechts_move1.png", klasse + "_untenRechts_move2.png"};
        spriteCounter = 0;
        setSprite(0);
        npcdialog = new Dialog();
        angriffHitbox = new AngriffsAnimation(new Koordinaten(-1000, -1000), 0, 0, "Schwert1");
        amLeben = true;

        this.level = level;
        xp = 0;
        xpBenoetigt = 100;
        werte = new Werte(klasse, level);
        waffe = new Waffe(schaden);
        pfeilAktiv = false;        
        inviFrames = 0;
    }

    public Figur(Koordinaten position, int breite, int hoehe, int winkel, String klasse, String name, int level) {
        super(position, breite, hoehe);
        setWinkel(winkel);
        inBewegung = false;
        bilderVerzeichnis = "bilder/sprites/";
        this.klasse = klasse;
        this.name = name;
        spriteList = new String[]{klasse + "_unten_idle.png", klasse + "_unten_move1.png", klasse + "_unten_move2.png",
            klasse + "_untenLinks_idle.png", klasse + "_untenLinks_move1.png", klasse + "_untenLinks_move2.png",
            klasse + "_links_idle.png", klasse + "_links_move1.png", klasse + "_links_move2.png",
            klasse + "_obenLinks_idle.png", klasse + "_obenLinks_move1.png", klasse + "_obenLinks_move2.png",
            klasse + "_oben_idle.png", klasse + "_oben_move1.png", klasse + "_oben_move2.png",
            klasse + "_obenRechts_idle.png", klasse + "_obenRechts_move1.png", klasse + "_obenRechts_move2.png",
            klasse + "_rechts_idle.png", klasse + "_rechts_move1.png", klasse + "_rechts_move2.png",
            klasse + "_untenRechts_idle.png", klasse + "_untenRechts_move1.png", klasse + "_untenRechts_move2.png"};
        spriteCounter = 0;
        setSprite(0);
        npcdialog = new Dialog();
        angriffHitbox = new AngriffsAnimation(new Koordinaten(-1000, -1000), 0, 0, "Schwert1");
        amLeben = true;

        this.level = level;
        xp = 0;
        xpBenoetigt = 100;
        werte = new Werte(klasse, level);
        waffe = new Waffe();
        pfeilAktiv = false;
        inviFrames = 0;
    }

    public boolean getBewegungsStatus() {
        return inBewegung;
    }

    public void setBewegungsStatus(boolean inBewegung) {
        this.inBewegung = inBewegung;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public ImageIcon getSprite() {
        return sprite;
    }

    public void setSprite(int imageNumber) {
        String imagePath = bilderVerzeichnis + spriteList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        sprite = new ImageIcon(imageURL);
    }

    public Dialog getNPCdialog() {
        return npcdialog;
    }

    public void setNPCdialog(String dialog) {
        npcdialog.setDialogZeile1(dialog);
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
        werte.wertZuweisung(this.klasse, level);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AngriffsAnimation getAngriffHitbox() {
        return angriffHitbox;
    }

    public void setAngriffHitbox(AngriffsAnimation angriff) {
        angriffHitbox = angriff;
    }

    public boolean getAmLeben() {
        return amLeben;
    }

    public void setAmLeben(boolean leben) {
        amLeben = leben;
    }

    public Werte getWerte() {
        return werte;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        werte.wertZuweisung(klasse, level);
        xpBenoetigtAnpassen();
    }

    public void levelUp() {
        level++;
        werte.wertZuweisung(klasse, level);
        xpBenoetigtAnpassen();
    }

    public int getXP() {
        return xp;
    }

    public void setXP(int xp) {
        this.xp = xp;
    }

    public int getXPbenoetigt() {
        return xpBenoetigt;
    }

    public void xpBenoetigtAnpassen() {
        for (int x = level; x > 1; x--) {
            xpBenoetigt = xpBenoetigt + ((xpBenoetigt * 10) / 100);
        }
    }

    public Waffe getWaffe() {
        return waffe;
    }

    public void setWaffe(Waffe waffe) {
        this.waffe = waffe;
    }

    public boolean getPfeilAktiv() {
        return pfeilAktiv;
    }

    public void setPfeilAktiv(boolean aktiv) {
        pfeilAktiv = aktiv;
    }
    
    public int getInviFrames() {
        return inviFrames;
    }
    
    public void setInviFrames(int frames) {
        inviFrames = frames;
    }

    public void bewegungUnten() {               //Winkel: 1
        setObjectPosition(new Koordinaten(getObjektPosition().getX(), getObjektPosition().getY() + (1 * super.getGeschwindigkeit())));
        setBewegungsStatus(true);
    }

    public void bewegungUntenLinks() {           //Winkel: 2
        setObjectPosition(new Koordinaten(getObjektPosition().getX() - (1 * super.getGeschwindigkeit()), getObjektPosition().getY() + (1 * super.getGeschwindigkeit())));
        setBewegungsStatus(true);
    }

    public void bewegungLinks() {               //Winkel: 3
        setObjectPosition(new Koordinaten(getObjektPosition().getX() - (1 * super.getGeschwindigkeit()), getObjektPosition().getY()));
        setBewegungsStatus(true);
    }

    public void bewegungObenLinks() {           //Winkel: 4
        setObjectPosition(new Koordinaten(getObjektPosition().getX() - (1 * super.getGeschwindigkeit()), getObjektPosition().getY() - (1 * super.getGeschwindigkeit())));
        setBewegungsStatus(true);
    }

    public void bewegungOben() {                //Winkel: 5
        setObjectPosition(new Koordinaten(getObjektPosition().getX(), getObjektPosition().getY() - (1 * super.getGeschwindigkeit())));
        setBewegungsStatus(true);
    }

    public void bewegungObenRechts() {           //Winkel: 6
        setObjectPosition(new Koordinaten(getObjektPosition().getX() + (1 * super.getGeschwindigkeit()), getObjektPosition().getY() - (1 * super.getGeschwindigkeit())));
        setBewegungsStatus(true);
    }

    public void bewegungRechts() {              //Winkel: 7
        setObjectPosition(new Koordinaten(getObjektPosition().getX() + (1 * super.getGeschwindigkeit()), getObjektPosition().getY()));
        setBewegungsStatus(true);
    }

    public void bewegungUntenRechts() {           //Winkel: 8
        setObjectPosition(new Koordinaten(getObjektPosition().getX() + (1 * super.getGeschwindigkeit()), getObjektPosition().getY() + (1 * super.getGeschwindigkeit())));
        setBewegungsStatus(true);
    }

    public void laufAnimation() {
        if (inBewegung) {
            spriteCounter++;
        }

        if (!inBewegung) {
            switch (getWinkel()) {
                case 1:
                    setSprite(0);
                    break;
                case 2:
                    setSprite(3);
                    break;
                case 3:
                    setSprite(6);
                    break;
                case 4:
                    setSprite(9);
                    break;
                case 5:
                    setSprite(12);
                    break;
                case 6:
                    setSprite(15);
                    break;
                case 7:
                    setSprite(18);
                    break;
                case 8:
                    setSprite(21);
                    break;
                default:
                    break;
            }
        }

        switch (getWinkel()) {
            case 1:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(1);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(2);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 2:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(4);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(5);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 3:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(7);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(8);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 4:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(10);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(11);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 5:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(13);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(14);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 6:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(16);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(17);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 7:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(19);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(20);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 8:
                if (spriteCounter <= 10 && inBewegung) {
                    setSprite(22);
                } else if (spriteCounter <= 20 && inBewegung) {
                    setSprite(23);
                } else {
                    spriteCounter = 0;
                }
                break;
            default:
                break;
        }
    }
    
    //Zeichnung für das originale, 1. blaue Viereck :)
    @Override
    public void zeichneObjekte(java.awt.Graphics g) {

        Graphics2D g2d = (Graphics2D) g;   
        g2d.setColor(Color.BLUE);        
        
              
        RoundRectangle2D spieler = new RoundRectangle2D.Double(getObjektPosition().getX(), 
                                                               getObjektPosition().getY(), 
                                                               getBreite(), getHoehe(), 3, 3); 
//        
////        AffineTransform transform = new AffineTransform();          
////        transform.rotate(getWinkel(), spieler.getCenterX(), spieler.getCenterY());
////        Shape transformedMissileShape = transform.createTransformedShape(spieler);
////        
//        g2d.fill(spieler);  
        sprite.paintIcon(null, g, getObjektPosition().getX(), getObjektPosition().getY());
    }
}
