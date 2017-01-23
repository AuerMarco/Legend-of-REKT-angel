package zeldiablo;

import java.awt.Rectangle;

public class Player extends Character {

    private Currency money;
    private boolean startWeaponChest;

    public Player(Coordinates position, int breite, int hoehe, int winkel, String klasse, String name, int level) {
        super(position, breite, hoehe, winkel, klasse, name, level);
        money = new Currency();
    }

    public Currency getMoney() {
        return money;
    }
    
    public boolean getStartWeaponChest() {
        return startWeaponChest;
    }
    
    public void setStartWeaponChest(boolean chest) {
        startWeaponChest = chest;
    }

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
            switch (interObject.getID()) {
                case "StartWeapon":
                    if (!player.getStartWeaponChest()) {
                        interObject.setSprite(1);
                        player.getWeapon().setDamage(10);
                        player.setStartWeaponChest(true);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    public void objectInteraction(Player spieler, Character npc) {

        if (spieler.isInReachOf(npc)) {
            switch (npc.getName()) {
                case "Solaire, Champion of the sun":
                    super.getNPCdialog().dialogSunbro(this, npc);
                    break;
                case "Unknown rogue":
                    super.getNPCdialog().dialogRogue(this, npc);
                default:
                    break;
            }
        }

    }
}
