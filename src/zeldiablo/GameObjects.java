/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Rectangle;

public abstract class GameObjects {

    private Coordinates objectPosition;
    private int width;
    private int height;
    private int angle;
    private int speed;
    private final int originalSpeed;
    private Rectangle hitbox;

    public GameObjects(Coordinates objectPosition, int width, int height) {
        this.objectPosition = objectPosition;
        this.width = width;
        this.height = height;
        angle = 1;
        speed = 3;
        originalSpeed = speed;
        hitbox = new Rectangle(objectPosition.getX(), objectPosition.getY(), width - 5, height - 5);
    }

    public Coordinates getObjectPosition() {
        return objectPosition;
    }

    public void setObjectPosition(Coordinates objectPosition) {
        this.objectPosition = objectPosition;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getOriginalSpeed() {
        return originalSpeed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isLeftOf(GameObjects that) {
        return this.getObjectPosition().getX() + this.getWidth() < that.getObjectPosition().getX();
    }

    public boolean isRightOf(GameObjects that) {
        return this.getObjectPosition().getX() + this.getWidth() > that.getObjectPosition().getX();
    }

    public boolean isAbove(GameObjects that) {
        return this.getObjectPosition().getY() + this.getHeight() < that.getObjectPosition().getY();
    }

    public boolean isBelow(GameObjects that) {
        return this.getObjectPosition().getY() + this.getHeight() > that.getObjectPosition().getY();
    }

    public boolean touches(GameObjects that) {
        hitbox = new Rectangle(objectPosition.getX(), objectPosition.getY() + (1 * getSpeed()), width - 5, height - 5);  //
        return this.hitbox.intersects(that.hitbox);
    }

    public void youShallNotPass(Player player, InteractionObjects InterObject) {
        if (player.touches(InterObject)) {
            switch (player.getAngle()) {
                case 1:
                    player.moveUp();
                    break;
                case 3:
                    player.moveRight();
                    break;
                case 5:
                    player.moveDown();
                    break;
                case 7:
                    player.moveLeft();
                    break;
                default:
                    break;
            }
        }
    }
    
    public void youShallNotPass(Player player, Character npc) {
        if (player.touches(npc)) {
            switch (player.getAngle()) {
                case 1:
                    player.moveUp();
                    break;
                case 2:
                    player.moveUpRight();
                case 3:
                    player.moveRight();
                    break;
                case 4:
                    player.moveDownRight();
                    break;
                case 5:
                    player.moveDown();
                    break;
                case 6:
                    player.moveDownLeft();
                    break;
                case 7:
                    player.moveLeft();
                    break;
                case 8: 
                    player.moveUpLeft();
                    break;
                default:
                    break;
            }
        }
    }
    
    public void youShallNotPass(Player player, AttackAnimation attack) {
        if (player.touches(attack)) {
            switch (player.getAngle()) {
                case 1:
                    player.moveUp();
                    break;
                case 3:
                    player.moveRight();
                    break;
                case 5:
                    player.moveDown();
                    break;
                case 7:
                    player.moveLeft();
                    break;
                default:
                    break;
            }
        }
    }

    protected abstract void drawObjects(java.awt.Graphics g);
}
