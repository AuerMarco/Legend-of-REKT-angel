package zeldiablo;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class contains the character-entity that is the controllable player
 * character It has all the methods unique to the player (like talking /
 * interacting with objects)
 *
 * @author Auer Marco
 */
public class Player extends Character {

    private Currency money;
    private boolean startWeaponChest;
    private ArrayList<Weapon> inventar;

    /**
     * Creates a currency and inventory the player can use
     *
     * @param position Where to spawn it
     * @param width Width of the entity
     * @param height Height of the entity
     * @param angle The angle the player is facing
     * @param characterClass The class of the player (Knight, Berserker, Hunter)
     * @param name the players name
     * @param level start level
     */
    public Player(Coordinates position, int width, int height, int angle, String characterClass, String name, int level) {
        super(position, width, height, angle, characterClass, name, level);
        money = new Currency();
        inventar = new ArrayList<>();
    }

    public Player(Coordinates position, int width, int height, int angle, String characterClass, String name, int level, String wepname, int wepdmg, int wepstr, int wepdex, int wepstam, int wepdef) {
        super(position, width, height, angle, characterClass, name, level, wepname, wepdmg, wepstr, wepdex, wepstam, wepdef);
        money = new Currency();
        inventar = new ArrayList<>();
    }

    public Currency getMoney() {
        return money;
    }

    public ArrayList<Weapon> getInventar() {
        return inventar;
    }

    public boolean getStartWeaponChest() {
        return startWeaponChest;
    }

    /**
     * If a player has looted a certain chest, it is noted here so each unique
     * chest can only be looted once even after switching the area you are in
     * and thus reloading the chests
     *
     * @param chest boolean true
     */
    public void setStartWeaponChest(boolean chest) {
        startWeaponChest = chest;
    }

    /**
     * Checks if the players hitbox (+/- 5 pixel) has a collision with an NPC or
     * object
     *
     * @param that the NPC / object that gets checked
     * @return boolean true / false
     */
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

    /**
     * Determines which action is performed if a player is in reach of an object
     * using the ID (Name)
     *
     * @param player The player character - the figure you control
     * @param interObject The object whose ID gets checked to determine the
     * actio performed (if it is in reach)
     */
    public void objectInteraction(Player player, InteractionObjects interObject) {
        if (player.isInReachOf(interObject)) {
            switch (interObject.getID()) {
                case "StartWeapon":
                    if (!player.getStartWeaponChest()) {
                        interObject.setSprite(1);
                        player.getInventar().add(new Weapon("Wooden Training Sword", 10, 0, 0, 0, 0));
                        player.setStartWeaponChest(true);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * Determines which action is performed if a player is in reach of an NPC
     * using the ID (Name)
     *
     * @param player The player character - the figure you control
     * @param npc the NPC whose ID gets checked
     */
    public void objectInteraction(Player player, Character npc) {

        if (player.isInReachOf(npc)) {
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
