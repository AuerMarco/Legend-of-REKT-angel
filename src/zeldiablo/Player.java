package zeldiablo;

import java.awt.Rectangle;

public class Player extends Character {

    public Player(Coordinates position, int breite, int hoehe, int winkel, String klasse, String name, int level, int weapon) {
        super(position, breite, hoehe, winkel, klasse, name, level, weapon);
    }
    
//    public Player(Coordinates position, int breite, int hoehe, int winkel, String klasse, String name, int level, int schaden) {
//        super(position, breite, hoehe, winkel, klasse, name, level, schaden);
//    }

    public boolean isInReachOf(GameObjects that) {
        switch (super.getAngle()) {
            case 1:
                super.setHitbox(new Rectangle(super.getObjectPosition().getX(), super.getObjectPosition().getY() + (1 * getOriginalSpeed()),
                        (int) super.getWidth() - 5, (int) super.getHeight() - 5));
                break;
            case 3:
                super.setHitbox(new Rectangle(super.getObjectPosition().getX() - (1 * getOriginalSpeed()), super.getObjectPosition().getY(),
                        (int) super.getWidth() - 5, (int) super.getHeight() - 5));
                break;
            case 5:
                super.setHitbox(new Rectangle(super.getObjectPosition().getX(), super.getObjectPosition().getY() - (1 * getOriginalSpeed()),
                        (int) super.getWidth() - 5, (int) super.getHeight() - 5));
                break;
            case 7:
                super.setHitbox(new Rectangle(super.getObjectPosition().getX() + (1 * getOriginalSpeed()), super.getObjectPosition().getY(),
                        (int) super.getWidth() - 5, (int) super.getHeight() - 5));
                break;
        }
        return super.getHitbox().intersects(that.getHitbox());
    }

    public void objectInteraction(Player player, InteractionObjects interObject) {
        if (player.isInReachOf(interObject)) {
            interObject.setSprite(1);
            System.out.println("You got epic loot! Yay!");
            player.getWeapon().setDamage(50);
        }
    }

    public void objectInteraction(Player spieler, Character npc) {

        if (spieler.isInReachOf(npc)) {
            switch (npc.getName()) {
                case "Solaire, Champion of the sun":
                    super.getNPCdialog().dialogSunbro(this, npc);
                    break;
                case "Unknown rogue":
                    super.getNPCdialog().dialogSchurke(this, npc);
                default:
                    break;
            }
        }

    }
}
