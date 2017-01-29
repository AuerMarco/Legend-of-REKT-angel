package zeldiablo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * This class is responsible for two things: 1) Detecting if an enemy or player
 * was hit / damaged 2) Draw the attack-animation of the player
 *
 * @author Auer Marco
 */
public class AttackAnimation extends GameObjects implements Serializable {

    private int weaponFrames;
    private int inviFrames;
    private boolean weaponReady;

    public final String imageDirectory;
    private ImageIcon sprite;
    private final String[] spriteList;
    private final String animation;

    /**
     *
     *
     * @param position where it will be
     * @param width how wide it will be
     * @param height how high it will be
     * @param animation - this will either be Sword1 for the Knight and
     * Berserker class or Arrow1 for the Hunter class; so it displays the
     * correct sprite
     */
    public AttackAnimation(Coordinates position, int width, int height, String animation) {
        super(position, width, height);
        weaponFrames = 0;
        inviFrames = 0;
        imageDirectory = "images/attacks/";
        spriteList = new String[]{animation + "_animation1.png", animation + "_animation2.png", animation + "_animation3.png", animation + "_animation4.png"};
        this.animation = animation;
        setSprite(0);
        weaponReady = true;
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

    public int getWeaponFrames() {
        return weaponFrames;
    }

    public void setWeaponFrames(int frames) {
        weaponFrames = frames;
    }

    public boolean getWeaponReady() {
        return weaponReady;
    }

    public void setWeaponReady(boolean weaponReady) {
        this.weaponReady = weaponReady;
    }

    /**
     * This method will spawn the sword animation (that damages the enemy on
     * contact) accordig to the angle the player is facing
     *
     * @param player The player character - the figure you control
     */
    public void melee(Player player) {
        switch (player.getAngle()) {
            case 1:
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX() - 30, (player.getObjectPosition().getY() + (int) player.getHeight())), 100, 55, animation));
                break;
            case 2:
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX() - 30, (player.getObjectPosition().getY() + (int) player.getHeight())), 100, 55, animation));
                break;
            case 3:
                player.setAttackHitbox(new AttackAnimation(new Coordinates((player.getObjectPosition().getX() - (int) player.getWidth() - 10), player.getObjectPosition().getY()), 55, 100, animation));
                break;
            case 5:
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX() - 30, (player.getObjectPosition().getY() - ((int) player.getHeight() / 2))), 100, 55, animation));
                break;
            case 7:
                player.setAttackHitbox(new AttackAnimation(new Coordinates((player.getObjectPosition().getX() + (int) player.getWidth() - 10), player.getObjectPosition().getY()), 55, 100, animation));
                break;
            default:
                break;
        }
    }

//    This was the method that was used, back when it was still planned to have the mob (enemy) attack instead of just damaging the player by running into him
//    public void melee(NPC mob) {
//        switch (mob.getAngle()) {
//            case 1:
//                mob.setAttackHitbox(new AttackAnimation(new Coordinates(mob.getObjectPosition().getX() - 30, (mob.getObjectPosition().getY() + (int) mob.getHeight())), 100, 55, animation));
//                break;
//            case 2:
//                mob.setAttackHitbox(new AttackAnimation(new Coordinates(mob.getObjectPosition().getX() - 30, (mob.getObjectPosition().getY() + (int) mob.getHeight())), 100, 55, animation));
//                break;
//            case 3:
//                mob.setAttackHitbox(new AttackAnimation(new Coordinates((mob.getObjectPosition().getX() - (int) mob.getWidth() - 10), mob.getObjectPosition().getY()), 55, 100, animation));
//                break;
//            case 5:
//                mob.setAttackHitbox(new AttackAnimation(new Coordinates(mob.getObjectPosition().getX() - 30, (mob.getObjectPosition().getY() - ((int) mob.getHeight() / 2))), 100, 55, animation));
//                break;
//            case 7:
//                mob.setAttackHitbox(new AttackAnimation(new Coordinates((mob.getObjectPosition().getX() + (int) mob.getWidth() - 10), mob.getObjectPosition().getY()), 55, 100, animation));
//                break;
//            default:
//                break;
//        }
//    }
    /**
     * Checks if the players attack (sword / arrow) is touching the enemy. If
     * yes, the mob loses health according to the calcualted damage
     *
     * @param player The player character - the figure you control
     * @param mob The enemy that the hit detection gets checked on
     */
    public void hitDetect(Player player, NPC mob) {
        if (player.getAttackHitbox().getHitbox().intersects(mob.getHitbox())) {

            DamageCalculation damageCalc = new DamageCalculation();
            double damage = damageCalc.damageCalculation(player, mob);
            if (damage < 1) {
                damage = 0;
            }
            mob.getStats().setHP(mob.getStats().getHP() - damage);

            pushBack(player, mob);

            System.out.println("Player to mob: " + damage);
            mob.setInviFrames(50);
        }

    }

    /**
     * Checks if the enemy touches the player. If yes, the player loses health
     * according to the calculated damage
     *
     * @param mob The enemy that the hit detection gets checked on
     * @param player The player character - the figure you control
     */
    public void hitDetect(NPC mob, Player player) {
        if (mob.getHitbox().intersects(player.getHitbox())) {           //getAttackHitbox().

            DamageCalculation damageCalc = new DamageCalculation();
            double damage = damageCalc.damageCalculation(mob, player);
            if (damage < 1) {
                damage = 0;
            }
            player.getStats().setHP(player.getStats().getHP() - damage);

            pushBack(mob, player);

            System.out.println("Mob to player: " + damage);
            player.setInviFrames(50);
        }
    }

    /**
     * This function makes the weapon animation disappear (set to a placeholder)
     * after 10 frames (200 ms) have passed
     *
     * @param player The player character - the figure you control
     */
    public void weaponFramesFunction(Player player) {
        weaponFrames++;
        if (weaponFrames > 10) {
            player.setAttackHitbox(new AttackAnimation(new Coordinates(-1000, -1000), 0, 0, "Sword1"));
        }
    }

    //Another old function that isn't used anymore, but kept just in case I will go back to making the mob attack
//    public void weaponFramesFunction(NPC mob) {
//        weaponFrames++;
//        if (weaponFrames > 10) {
//            mob.setAttackHitbox(new AttackAnimation(new Coordinates(-1000, -1000), 0, 0, "Sword1"));
//        }
//    }
    /**
     * When you hit the enemy, he gets knocked back
     *
     * @param player The player character - the figure you control
     * @param mob The enemy that gets pushed back (if a hit is detected)
     */
    public void pushBack(Player player, NPC mob) {
        int n = 0;
        switch (player.getCharacterClass()) {
            case "Knight":
                n = 5;
                break;
            case "Berserker":
                n = 10;
                break;
            case "Hunter":
                n = 5;
                break;
            default:
                break;
        }
        switch (mob.getAngle()) {
            case 1:
                for (int i = 0; i < n; i++) {
                    mob.moveUp();
                }
                break;
            case 3:
                for (int i = 0; i < n; i++) {
                    mob.moveRight();
                }
                break;
            case 5:
                for (int i = 0; i < n; i++) {
                    mob.moveDown();
                }
                break;
            case 7:
                for (int i = 0; i < n; i++) {
                    mob.moveLeft();
                }
                break;
            default:
                break;
        }
    }

    /**
     * When you get touched by an enemy you get pushed back (on top of taking
     * damage)
     *
     * @param mob The mob that pushes the player
     * @param player The player character - the figure you control, that gets
     * pushed here
     */
    public void pushBack(NPC mob, Player player) {
        switch (mob.getAngle()) {
            case 1:
                for (int i = 0; i < 10; i++) {
                    player.moveDown();

                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    player.moveLeft();
                }
                break;
            case 5:
                for (int i = 0; i < 10; i++) {
                    player.moveUp();
                }
                break;
            case 7:
                for (int i = 0; i < 10; i++) {
                    player.moveRight();
                }
                break;
            default:
                break;
        }
    }

    /**
     * This method draws the sprites (or paintIcons to be precise) and all the other things to the Canvas
     *
     * @param g the Graphics
     */
    @Override
    protected void drawObjects(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        RoundRectangle2D hitbox = new RoundRectangle2D.Double(getObjectPosition().getX(), getObjectPosition().getY(),
                getWidth(), getHeight(), 3, 3);

//        AffineTransform transform = new AffineTransform();        
//        transform.rotate(1, hitbox.getCenterX(), hitbox.getCenterY());
//        Shape trans = transform.createTransformedShape(hitbox);
//        g2d.fill(hitbox);
        sprite.paintIcon(null, g, getObjectPosition().getX(), getObjectPosition().getY());
    }

}
