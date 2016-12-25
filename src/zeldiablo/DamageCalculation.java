/**
 * @author Auer Marco
 */
package zeldiablo;

public class DamageCalculation {

    public double damageCalculation(Player player, NPC mob) {
        double weapon = player.getWeapon().getDamage();
        double attack = player.getStats().getAttack();
        double defence = mob.getStats().getDefence();
        double critMulti = 1;

//        Chance chance = new Chance(player.getLevel(), player.getStats().getDexterity(), player.getCharacterClass());
        Chance chance = new Chance(player.getStats().getCritChance());
        boolean crit = chance.getSuccess();

        if (crit) {
            critMulti = 2;
            System.out.println("CRIT!");
        } else {
//            System.out.println("No crit");
        }

        return ((weapon + attack) * 2 - defence) * critMulti;
    }

    public double damageCalculation(Player player, NPC mob, boolean crit) {
        double weapon = player.getWeapon().getDamage();
        double attack = player.getStats().getAttack();
        double defence = mob.getStats().getDefence();
        double critMulti = 1;

        if (crit) {
            critMulti = 2;
            System.out.println("CRIT!");
        } else {
//            System.out.println("No crit");
        }

        return ((weapon + attack) * 2 - defence) * critMulti;
    }
}
