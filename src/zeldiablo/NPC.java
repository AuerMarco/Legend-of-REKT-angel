/**
 * @author Auer Marco
 */
package zeldiablo;

public class NPC extends Character {

    public NPC(Coordinates position, int breite, int hoehe, int winkel, String klasse, String name, int level, int schaden) {
        super(position, breite, hoehe, winkel, klasse, name, level, schaden);
    }

//    public void aggroRange(Player player) {
//        moveToPlayer(player);
//    }
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
            }
            else if (mobY < playerY - 10) {          // - player.getHeight()
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
//    public void attack() {
//        if (attackCD == 0) {
//            attackCD = 20;
//            getAttackHitbox().melee(this);
//        }
//    }
}
