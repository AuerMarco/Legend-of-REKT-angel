/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Rectangle;

public abstract class SpielObjekt {

    private Koordinaten objektPosition;
    private int breite;
    private int hoehe;
    private int blickWinkel;
    private int geschwindigkeit;
    private final int originalGeschwindigkeit;
    private Rectangle hitbox;
    private Gegenstand kiste1;

    public SpielObjekt(Koordinaten objektPosition, int breite, int hoehe) {
        this.objektPosition = objektPosition;
        this.breite = breite;
        this.hoehe = hoehe;
        blickWinkel = 1;
        geschwindigkeit = 3;
        originalGeschwindigkeit = geschwindigkeit;
        hitbox = new Rectangle(objektPosition.getX(), objektPosition.getY(), breite - 5, hoehe - 5);
    }

    public Koordinaten getObjektPosition() {
        return objektPosition;
    }

    public void setObjectPosition(Koordinaten objektPosition) {
        this.objektPosition = objektPosition;
    }

    public double getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public double getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getWinkel() {
        return blickWinkel;
    }

    public void setWinkel(int winkel) {
        this.blickWinkel = winkel;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }
    
    public int getOriginalGeschwindigkeit() {
        return originalGeschwindigkeit;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean istLinksVon(SpielObjekt that) {
        return this.getObjektPosition().getX() + this.getBreite() < that.getObjektPosition().getX();
    }

    public boolean istRechtsVon(SpielObjekt that) {
        return this.getObjektPosition().getX() + this.getBreite() > that.getObjektPosition().getX();
    }

    public boolean istOberhalbVon(SpielObjekt that) {
        return this.getObjektPosition().getY() + this.getHoehe() < that.getObjektPosition().getY();
    }

    public boolean istUnterhalbVon(SpielObjekt that) {
        return this.getObjektPosition().getY() + this.getHoehe() > that.getObjektPosition().getY();
    }

    public boolean beruehrt(SpielObjekt that) {
        hitbox = new Rectangle(objektPosition.getX(), objektPosition.getY() + (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);  //
        return this.hitbox.intersects(that.hitbox);

//        switch (blickWinkel) {
//            case 1:
//                hitbox = new Rectangle(objektPosition.getX(), objektPosition.getY() + (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);
//                break;
//            case 2:
//                hitbox = new Rectangle(objektPosition.getX() - (1 * getGeschwindigkeit()), objektPosition.getY(), breite - 5, hoehe - 5);
//                break;
//            case 3:
//                hitbox = new Rectangle(objektPosition.getX(), objektPosition.getY() - (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);
//                break;
//            case 4:
//                hitbox = new Rectangle(objektPosition.getX() + (1 * getGeschwindigkeit()), objektPosition.getY() + (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);
//                break;
//        }
    }

    public void youShallNotPass(Spieler spieler, Gegenstand gegenstand) {
        if (spieler.beruehrt(gegenstand)) {
            switch (spieler.getWinkel()) {
                case 1:
                    spieler.bewegungOben();
                    break;
                case 3:
                    spieler.bewegungRechts();
                    break;
                case 5:
                    spieler.bewegungUnten();
                    break;
                case 7:
                    spieler.bewegungLinks();
                    break;
                default:
                    break;
            }
        }
    }
    
    public void youShallNotPass(Spieler spieler, Figur npc) {
        if (spieler.beruehrt(npc)) {
            switch (spieler.getWinkel()) {
                case 1:
                    spieler.bewegungOben();
                    break;
                case 2:
                    spieler.bewegungObenRechts();
                case 3:
                    spieler.bewegungRechts();
                    break;
                case 4:
                    spieler.bewegungUntenRechts();
                    break;
                case 5:
                    spieler.bewegungUnten();
                    break;
                case 6:
                    spieler.bewegungUntenLinks();
                    break;
                case 7:
                    spieler.bewegungLinks();
                    break;
                case 8: 
                    spieler.bewegungObenLinks();
                    break;
                default:
                    break;
            }
        }
    }
    
    public void youShallNotPass(Spieler spieler, AngriffsAnimation angriff) {
        if (spieler.beruehrt(angriff)) {
            switch (spieler.getWinkel()) {
                case 1:
                    spieler.bewegungOben();
                    break;
                case 3:
                    spieler.bewegungRechts();
                    break;
                case 5:
                    spieler.bewegungUnten();
                    break;
                case 7:
                    spieler.bewegungLinks();
                    break;
                default:
                    break;
            }
        }
    }

    //    public boolean istInReichweiteVon(SpielObjekte that) {
//        switch (blickWinkel) {
//            case 1:
//                hitbox = new Rectangle(objektPosition.getX(), objektPosition.getY() + (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);
//                break;
//            case 2:
//                hitbox = new Rectangle(objektPosition.getX() - (1 * getGeschwindigkeit()), objektPosition.getY(), breite - 5, hoehe - 5);
//                break;
//            case 3:
//                hitbox = new Rectangle(objektPosition.getX(), objektPosition.getY() - (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);
//                break;
//            case 4:
//                hitbox = new Rectangle(objektPosition.getX() + (1 * getGeschwindigkeit()), objektPosition.getY() + (1 * getGeschwindigkeit()), breite - 5, hoehe - 5);
//                break;
//        }
//        return this.hitbox.intersects(that.hitbox);
//    }
//
//    public void objektInteraktion(Spieler spieler, Gegenstand gegenstand) {
//        if (spieler.istInReichweiteVon(gegenstand)) {
//            gegenstand.setSprite(1);
//            System.out.println("Sie haben epische Beute erhalten! Yay!");
//        }
//    
    protected abstract void zeichneObjekte(java.awt.Graphics g);
}
