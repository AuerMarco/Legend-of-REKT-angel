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

public class Character extends GameObjects {

    private boolean moving;
    public final String imageDirectory;
    private ImageIcon sprite;
    private final String[] spriteList;
    private int spriteCounter;
    private Dialog npcDialog;
    private String characterClass;
    private String name;
    private AttackAnimation attackHitbox;
    private boolean arrowActive;
    int inviFrames, attackCD;
    private boolean alive;

    private int level;
    private int levelUpAnimationFrames;
    private boolean levelUpAnimationVisible;
    private double xp;
    private double xpNeeded;
    
    private Stats stats;
    private Weapon weapon;
    

    public Character(Coordinates position, int width, int height, int angle, String characterClass, String name, int level, int damage) {
        super(position, width, height);
        setAngle(angle);
        moving = false;
        imageDirectory = "images/sprites/";
        this.characterClass = characterClass;
        this.name = name;
        spriteList = new String[]{characterClass + "_down_idle.png", characterClass + "_down_move1.png", characterClass + "_down_move2.png",
            characterClass + "_downLeft_idle.png", characterClass + "_downLeft_move1.png", characterClass + "_downLeft_move2.png",
            characterClass + "_left_idle.png", characterClass + "_left_move1.png", characterClass + "_left_move2.png",
            characterClass + "_upLeft_idle.png", characterClass + "_upLeft_move1.png", characterClass + "_upLeft_move2.png",
            characterClass + "_up_idle.png", characterClass + "_up_move1.png", characterClass + "_up_move2.png",
            characterClass + "_upRight_idle.png", characterClass + "_upRight_move1.png", characterClass + "_upRight_move2.png",
            characterClass + "_right_idle.png", characterClass + "_right_move1.png", characterClass + "_right_move2.png",
            characterClass + "_downRight_idle.png", characterClass + "_downRight_move1.png", characterClass + "_downRight_move2.png"};
        spriteCounter = 0;
        setSprite(0);
        npcDialog = new Dialog();
        attackHitbox = new AttackAnimation(new Coordinates(-1000, -1000), 0, 0, "Sword1");
        alive = true;

        this.level = level;
        adjustXP();
        xpNeeded = 100;
        stats = new Stats(characterClass, level, name);
        weapon = new Weapon(damage);
        arrowActive = false;
        inviFrames = 0;
        attackCD = 0;        
        levelUpAnimationFrames = 0;
        levelUpAnimationVisible = false;
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
        String imagePath = imageDirectory + spriteList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        sprite = new ImageIcon(imageURL);
    }

    public Dialog getNPCdialog() {
        return npcDialog;
    }

    public void setNPCdialog(String dialog) {
        npcDialog.setDialogLine1(dialog);
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
        stats.statAdjustment(this.characterClass, level, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttackAnimation getAttackHitbox() {
        return attackHitbox;
    }

    public void setAttackHitbox(AttackAnimation attack) {
        attackHitbox = attack;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Stats getStats() {
        return stats;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        showLevelUpAnimation();
        this.level = level;
        stats.statAdjustment(characterClass, level, name);
        adjustXP();
        adjustXPneeded();
    }

    public void levelUp() {
        showLevelUpAnimation();
        level++;
        stats.statAdjustment(characterClass, level, name);
        adjustXP();
        adjustXPneeded();
    }
    
    public void showLevelUpAnimation() {
        levelUpAnimationFrames = 0;
        levelUpAnimationVisible = true;
    }

    public double getXP() {
        return xp;
    }

    public void setXP(double xp) {
        this.xp = xp;
    }

    public void increaseXP(double xp) {
        this.xp = this.xp + xp;
    }

    public void adjustXP() {
        if (characterClass == "Mob") {
//            if (level == 1) {
//                xp = 10;
//            } else {
//                xp += 1;
//            }   
            xp = 10;
            for (int x = level; x > 1; x--) {
                xp += 1;
            }
//            for (int x = level; x > 1; x--) {
//                xp *= 1.10;
//            }
//            xp = xpNeeded / 10;
        } else {
            xp = 0;
        }
    }

    public double getXPneeded() {
        return xpNeeded;
    }

    public void adjustXPneeded() {
        xpNeeded = 100;
        for (int x = level; x > 1; x--) {
            xpNeeded += 25;
        }
        
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean getArrowActive() {
        return arrowActive;
    }

    public void setArrowActive(boolean active) {
        arrowActive = active;
    }

    public int getInviFrames() {
        return inviFrames;
    }

    public void setInviFrames(int frames) {
        inviFrames = frames;
    }
    
    public int getAttackCD() {
        return attackCD;
    }
    
    public void setAttackCD(int cd) {
        attackCD = cd;
    }
    
    public boolean getLevelUpAnimationVisible() {
        return levelUpAnimationVisible;
    }

    public void moveDown() {               //Winkel: 1
        setObjectPosition(new Coordinates(getObjectPosition().getX(), getObjectPosition().getY() + (1 * super.getSpeed())));
        setMoving(true);
    }

    public void moveDownLeft() {           //Winkel: 2
        setObjectPosition(new Coordinates(getObjectPosition().getX() - (1 * super.getSpeed()), getObjectPosition().getY() + (1 * super.getSpeed())));
        setMoving(true);
    }

    public void moveLeft() {               //Winkel: 3
        setObjectPosition(new Coordinates(getObjectPosition().getX() - (1 * super.getSpeed()), getObjectPosition().getY()));
        setMoving(true);
    }

    public void moveUpLeft() {           //Winkel: 4
        setObjectPosition(new Coordinates(getObjectPosition().getX() - (1 * super.getSpeed()), getObjectPosition().getY() - (1 * super.getSpeed())));
        setMoving(true);
    }

    public void moveUp() {                //Winkel: 5
        setObjectPosition(new Coordinates(getObjectPosition().getX(), getObjectPosition().getY() - (1 * super.getSpeed())));
        setMoving(true);
    }

    public void moveUpRight() {           //Winkel: 6
        setObjectPosition(new Coordinates(getObjectPosition().getX() + (1 * super.getSpeed()), getObjectPosition().getY() - (1 * super.getSpeed())));
        setMoving(true);
    }

    public void moveRight() {              //Winkel: 7
        setObjectPosition(new Coordinates(getObjectPosition().getX() + (1 * super.getSpeed()), getObjectPosition().getY()));
        setMoving(true);
    }

    public void moveDownRight() {           //Winkel: 8
        setObjectPosition(new Coordinates(getObjectPosition().getX() + (1 * super.getSpeed()), getObjectPosition().getY() + (1 * super.getSpeed())));
        setMoving(true);
    }

    public void walkingAnimation() {
        if (moving) {
            spriteCounter++;
        }

        if (!moving) {
            switch (getAngle()) {
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

        switch (getAngle()) {
            case 1:
                if (spriteCounter <= 10 && moving) {
                    setSprite(1);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(2);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 2:
                if (spriteCounter <= 10 && moving) {
                    setSprite(4);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(5);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 3:
                if (spriteCounter <= 10 && moving) {
                    setSprite(7);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(8);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 4:
                if (spriteCounter <= 10 && moving) {
                    setSprite(10);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(11);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 5:
                if (spriteCounter <= 10 && moving) {
                    setSprite(13);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(14);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 6:
                if (spriteCounter <= 10 && moving) {
                    setSprite(16);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(17);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 7:
                if (spriteCounter <= 10 && moving) {
                    setSprite(19);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(20);
                } else {
                    spriteCounter = 0;
                }
                break;
            case 8:
                if (spriteCounter <= 10 && moving) {
                    setSprite(22);
                } else if (spriteCounter <= 20 && moving) {
                    setSprite(23);
                } else {
                    spriteCounter = 0;
                }
                break;
            default:
                break;
        }
    }

    public void levelUpAnimationFunction(Player player) {
        levelUpAnimationFrames++;
        if (levelUpAnimationFrames > 50) {
            levelUpAnimationVisible = false;
        }
    }
    
    //Zeichnung für das originale, 1. blaue Viereck :)
    @Override
    public void drawObjects(java.awt.Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        RoundRectangle2D spieler = new RoundRectangle2D.Double(getObjectPosition().getX(),
                getObjectPosition().getY(),
                getWidth(), getHeight(), 3, 3);
//        
////        AffineTransform transform = new AffineTransform();          
////        transform.rotate(getAngle(), spieler.getCenterX(), spieler.getCenterY());
////        Shape transformedMissileShape = transform.createTransformedShape(spieler);
////        
        g2d.fill(spieler);  
        sprite.paintIcon(null, g, getObjectPosition().getX(), getObjectPosition().getY());
    }
}
