package zeldiablo;

/**
 * This class calculates the damage a player or mob receives, taking many stats into consideration
 * It has no data members and uses only the parameters it gets 
 * 
 * @author Auer Marco
 */
public class DamageCalculation {

    /**
     * Calculates the damage taking into account the players attackpower, the players weapon, the mobs defence and wether or not the player succesfully landed a critical hit
     * 
     * @param player The player character whose stats get used
     * @param mob The enemy whose stats get used and that is also the one receiving the damage afterwards
     * @return The calculated damage
     */
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
    
    /**
     * Calculates the damage taking into account the mobs attackpower, the mobs weapon and the players defence. Mobs can not land critical hits.
     * 
     * @param mob The enemy whose stats get used
     * @param player The player character whose stats get used and that is also the one receiving the damage afterwards
     * @return The calculated damage
     */
    public double damageCalculation(NPC mob, Player player) {
        double weapon = mob.getWeapon().getDamage();
        double attack = mob.getStats().getAttack();
        double defence = player.getStats().getDefence();

        return ((weapon + attack) * 2 - defence);
    }
}
