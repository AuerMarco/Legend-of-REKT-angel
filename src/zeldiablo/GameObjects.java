package zeldiablo;

import java.awt.Rectangle;

/**
 * This class is the superclass of all the entities on the canvas.
 * It sets certain things every entity has like position and size.
 * 
 * @author Auer Marco
 */
public abstract class GameObjects {

    private Coordinates objectPosition;
    private int width;
    private int height;
    private int angle;
    private int speed;
    private final int originalSpeed;
    private Rectangle hitbox;

    /**
     * speed is the distance (pixel) a character-object will move
     * hitbox is basically just a rectangle. Collision (like hit detection) is determined by checking if 2 hitboxes are touching eachother
     * 
     * @param objectPosition where the entity will be
     * @param width Width of the entity
     * @param height Height of the entity
     */
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
    
    /**
     * updates the hitbox of an entity on each frame
     */
    public void hitboxUpdate() {
        hitbox = new Rectangle(objectPosition.getX(), objectPosition.getY() + (1 * getSpeed()), width - 5, height - 5);
    }

    /**
     * Determines if a collision is happening by using the intersect method of the Rectangle class. Very comfortable method to use in Java
     * @param that that is the entity whose hitbox gets checked if it touches the current hitbox of the current instance (this ) 
     * @return boolean wether a collision is happening or not
     */
    public boolean touches(GameObjects that) {
//        hitbox = new Rectangle(objectPosition.getX(), objectPosition.getY() + (1 * getSpeed()), width - 5, height - 5);  //
        return this.hitbox.intersects(that.hitbox);
    }

    /**
     * This method prevents the player from running into the object by moving him in the opposite direction that he is facing
     * 
     * @param player The player character - the figure you control
     * @param InterObject An object (like a chest) that can be interacted with
     */
    public void youShallNotPass(Player player, InteractionObjects InterObject) {
        if (player.touches(InterObject)) {
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
    
    /**
     * This method prevents the player from running into the NPC by moving him in the opposite direction that he is facing
     * 
     * @param player The player character - the figure you control
     * @param npc An NPC that can be interacted with
     */
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

    /**
     * Abstract method that forces implementation in all the classes that inherit from GameObjects
     * 
     * @param g graphics
     */
    protected abstract void drawObjects(java.awt.Graphics g);
}
