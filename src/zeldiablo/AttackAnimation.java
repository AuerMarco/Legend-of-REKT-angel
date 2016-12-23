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

public class AttackAnimation extends GameObjects {

    private int weaponFrames;
    private int inviFrames;
    private boolean weaponReady;

    public final String imageDirectory;
    private ImageIcon sprite;
    private final String[] spriteList;
    private final String animation;

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

    public void hitDetect(Player player, NPC mob) {
        if (player.getAttackHitbox().getHitbox().intersects(mob.getHitbox())) {
            DamageCalculation damage = new DamageCalculation();
            mob.getStats().setHP(mob.getStats().getHP() - damage.damageCalculation(player, mob));
            
            System.out.println(damage.damageCalculation(player, mob));
            mob.setInviFrames(50);
        }

    }

    public void weaponFramesFunction(Player player) {
        weaponFrames++;
        if (weaponFrames > 10) {
            player.setAttackHitbox(new AttackAnimation(new Coordinates(-1000, -1000), 0, 0, "Sword1"));
        }
    }

    @Override
    protected void drawObjects(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        RoundRectangle2D hitbox = new RoundRectangle2D.Double(getObjectPosition().getX(), getObjectPosition().getY(),
                getWidth(), getHeight(), 3, 3);

//        AffineTransform transform = new AffineTransform();        
//        transform.rotate(1, hitbox.getCenterX(), hitbox.getCenterY());
//        Shape trans = transform.createTransformedShape(hitbox);
        g2d.fill(hitbox);

        sprite.paintIcon(null, g, getObjectPosition().getX(), getObjectPosition().getY());
    }

}
