/**
 * @author Auer Marco
 */
package zeldiablo;

public class Weapon {
    
    private double damage;
    
    public Weapon() {
        damage = 10;
    }
    
    public Weapon(int damage) {
        this.damage = damage;
    }
    
    public double getDamage() {
        return damage;
    }
    
    public void setDamage(double damage) {
        this.damage = damage;
    }
    
}
