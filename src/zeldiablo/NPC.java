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
    
    public void checkPlayerInReach() {
        
    }
    
    public void attack() {
         if (attackCD == 0) {
            attackCD = 20;
            getAttackHitbox().melee(this);
        }
    }
}
