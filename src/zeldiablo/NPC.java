package zeldiablo;

/**
 * This class is made to identify which gameObjects are non player characters
 * (NPCs) This can either be a friendly NPC (that talks to you) or a mob that
 * you fight
 *
 * @author Auer Marco
 */
public class NPC extends Character {

    private boolean aggro;
    private String id;

    /**
     *
     * @param position Where to spawn it
     * @param width Width of the entity
     * @param height Height of the entity
     * @param angle The angle the NPC is facing
     * @param characterClass The class of the NPC... plays no role for friendly
     * ones. For mobs the class will be "Mob"
     * @param name The ID / Name
     * @param level Start level
     */
    public NPC(Coordinates position, int width, int height, int angle, String characterClass, String name, int level) {
        super(position, width, height, angle, characterClass, name, level);
        aggro = true;
    }
    public NPC(Coordinates position, int width, int height, int angle, String characterClass, String name, int level, String wepname, int wepdmg, int wepstr, int wepdex, int wepstam, int wepdef) {
        super(position, width, height, angle, characterClass, name, level, wepname, wepdmg, wepstr, wepdex, wepstam, wepdef);
        aggro = true;
    }
    
    public NPC(Coordinates position, int width, int height, int angle, String characterClass, String name, int level, String id) {
        super(position, width, height, angle, characterClass, name, level);
        aggro = true;
        this.id = id;
    }

    public boolean getAggro() {
        return aggro;
    }

    public void setAggro(boolean aggro) {
        this.aggro = aggro;
    }
    
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }

    //Originally the mobs were supposed to get aggresive when a player is in a certain ranges
//    public void aggroRange(Player player) {
//        moveToPlayer(player);
//    }
    
    /**
     * Makes the mob move towards the player, if in state "aggro" (aggressive)
     *
     * @param player The player character - the figure you control
     */
    public void moveToPlayer(Player player) {
        int playerX = player.getObjectPosition().getX();
        int playerY = player.getObjectPosition().getY();
        int mobX = this.getObjectPosition().getX();
        int mobY = this.getObjectPosition().getY();

        if (inviFrames == 0) {
            if (mobX > playerX + 10) {           // +  player.getWidth()
                setAngle(3);
                setSprite(6);
                moveLeft();
            } else if (mobX < playerX - 10) {          // - player.getWidth()
                setAngle(7);
                setSprite(18);
                moveRight();
            } else if (mobY < playerY - 10) {          // - player.getHeight()
                setAngle(1);
                setSprite(0);
                moveDown();
            } else if (mobY > playerY + 10) {          // + player.getHeight()
                setAngle(5);
                setSprite(12);
                moveUp();
            }
        }

    }

    //Method that was originally meant to check if the player is in reach and trigger the aggro state. 
    //It was never finishd
//    public void checkPlayerInReach(Player player) {
//        int playerX = player.getObjectPosition().getX();
//        int playerY = player.getObjectPosition().getY();
//        int mobX = this.getObjectPosition().getX();
//        int mobY = this.getObjectPosition().getY();
//        
//        if (mobX + 10 <= playerX) {
//            attack();
//        }
//    }
//
//    Original attack method of the npc, before the feature got removed. 
//    All of these are kept in case I decide to go back to making the mob attack
//    public void attack() {
//        if (attackCD == 0) {
//            attackCD = 20;
//            getAttackHitbox().melee(this);
//        }
//    }
}
