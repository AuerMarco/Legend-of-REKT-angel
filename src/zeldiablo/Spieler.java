package zeldiablo;

import java.awt.Rectangle;

public class Spieler extends Figur {

    public Spieler(Koordinaten position, int breite, int hoehe, int winkel, String klasse, String name, int level) {
        super(position, breite, hoehe, winkel, klasse, name, level);
    }
    
    public Spieler(Koordinaten position, int breite, int hoehe, int winkel, String klasse, String name, int level, int schaden) {
        super(position, breite, hoehe, winkel, klasse, name, level, schaden);
    }

    public boolean istInReichweiteVon(SpielObjekt that) {
        switch (super.getWinkel()) {
            case 1:
                super.setHitbox(new Rectangle(super.getObjektPosition().getX(), super.getObjektPosition().getY() + (1 * getOriginalGeschwindigkeit()),
                        (int) super.getBreite() - 5, (int) super.getHoehe() - 5));
                break;
            case 3:
                super.setHitbox(new Rectangle(super.getObjektPosition().getX() - (1 * getOriginalGeschwindigkeit()), super.getObjektPosition().getY(),
                        (int) super.getBreite() - 5, (int) super.getHoehe() - 5));
                break;
            case 5:
                super.setHitbox(new Rectangle(super.getObjektPosition().getX(), super.getObjektPosition().getY() - (1 * getOriginalGeschwindigkeit()),
                        (int) super.getBreite() - 5, (int) super.getHoehe() - 5));
                break;
            case 7:
                super.setHitbox(new Rectangle(super.getObjektPosition().getX() + (1 * getOriginalGeschwindigkeit()), super.getObjektPosition().getY(),
                        (int) super.getBreite() - 5, (int) super.getHoehe() - 5));
                break;
        }
        return super.getHitbox().intersects(that.getHitbox());
    }

    public void objektInteraktion(Spieler spieler, Gegenstand gegenstand) {
        if (spieler.istInReichweiteVon(gegenstand)) {
            gegenstand.setSprite(1);
            System.out.println("Sie haben epische Beute erhalten! Yay!");
            spieler.getWaffe().setSchaden(50);
        }
    }

    public void objektInteraktion(Spieler spieler, Figur npc) {

        if (spieler.istInReichweiteVon(npc)) {
            switch (npc.getName()) {
                case "Solaire, Champion der Sonne":
                    super.getNPCdialog().dialogSunbro(this, npc);
                    break;
                case "Unbekannter Schurke":
                    super.getNPCdialog().dialogSchurke(this, npc);
                default:
                    break;
            }
        }

    }
}
