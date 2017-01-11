/**
 * @author Auer Marco
 */
package zeldiablo;

public class NPC extends Character {

    public NPC(Coordinates position, int breite, int hoehe, int winkel, String klasse, String name, int level, int schaden) {
        super(position, breite, hoehe, winkel, klasse, name, level, schaden);
    }

//    public NPC(Coordinates position, int breite, int hoehe, int winkel, String klasse, String name, int level) {
//        super(position, breite, hoehe, winkel, klasse, name, level);
//    }  
    public void aggroRange(Player player) {
        int playerX = player.getObjectPosition().getX();
        int playerY = player.getObjectPosition().getY();
        int mobX = this.getObjectPosition().getX();
        int mobY = this.getObjectPosition().getY();

        //mobX - playerX  --> Spieler ist links vom Mob
        if (mobX - playerX >= 40) {
            setSprite(6);
            moveLeft();
//            checkPlayerInReach(player);
        }
        else if (mobX - playerX <= 10) {
            setSprite(18);
            moveRight();
        }
        
        if (mobY < playerY - player.getHeight()) {
            setSprite(0);
            moveDown();
//            checkPlayerInReach(player);
        }
        else if (mobY > playerY + player.getHeight()) {      // + player.getHeight()*1.8
            setSprite(12);
            moveUp();
        }

    }

    public void checkPlayerInReach(Player player) {
        int playerX = player.getObjectPosition().getX();
        int playerY = player.getObjectPosition().getY();
        int mobX = this.getObjectPosition().getX();
        int mobY = this.getObjectPosition().getY();
        
        if (mobX + 10 <= playerX) {
            attack();
        }
    }

    public void attack() {
        if (attackCD == 0) {
            attackCD = 20;
            getAttackHitbox().melee(this);
        }
    }
}
