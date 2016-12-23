/**
 * @author Auer Marco
 */
package zeldiablo;

public class DamageCalculation {
    
    public double damageCalculation(Player player, NPC mob) {
        double weapon = player.getWeapon().getDamage();
        double attack = player.getStats().getAttack();
        double defence = mob.getStats().getDefence();
        return ((weapon + attack) * 2 - defence);
    }
}
