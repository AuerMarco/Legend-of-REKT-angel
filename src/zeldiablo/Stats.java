package zeldiablo;

/**
 * This class contains the stats (like attackpower) of characters and weapons
 *
 * @author Auer Marco
 */
public class Stats {

    private double attack;
    private double dexterity;
    private double stamina;
    private double defence;
    private double hp;
    private double maxHP;
    private double critChance;
    private int level;
    private String statSummary;
    private boolean visible;

    /**
     * This constructor is used to assign stats to characters (players & mobs)
     * It might be noted that dexterity is the stat that determines the crit
     * chance. The other stats should be self explainatory
     *
     * @param characterClass Class like Knight or also Mob for example
     * @param level Level the character is at the moment
     * @param name The (players) name. Also has impacts on the amount of stats
     * you get! Check the adjustment-method for more information.
     */
//    public Stats(String characterClass, int level, String name) {
//        attack = 0;
//        dexterity = 0;
//        stamina = 0;
//        defence = 0;
//        this.level = level;
//        statAdjustment(characterClass, level, name);
//        hp = stamina * 15;
//        maxHP = hp;
//        statSummary = "";
//    }
    public Stats(Character character) {
        attack = 0;
        dexterity = 0;
        stamina = 0;
        defence = 0;
        this.level = character.getLevel();
        statAdjustment(character);
        hp = stamina * 15;
        maxHP = hp;
        statSummary = "";
    }

    /**
     * This constructor is used for the weapons, as the weapon-class has it's
     * own methods to determine the amount each stat should be
     */
    public Stats() {
        attack = 0;
        dexterity = 0;
        stamina = 0;
        defence = 0;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getStamina() {
        return stamina;
    }

    public void setStamina(double stamina) {
        this.stamina = stamina;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public double getHP() {
        return hp;
    }

    public void setHP(double hp) {
        this.hp = hp;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getCritChance() {
        return critChance;
    }

    /**
     * @return a string that sums up 4 of the stats. Used to display them in one
     * line on the screen when pressing C
     */
    public String getStatSummary1() {
        return statSummary = "Attack: " + (int) attack + ". Dexterity: " + (int) dexterity + ". Stamina: " + (int) stamina + ". Defence: " + (int) defence;
    }

    /**
     * @return a string that sums up 4 other stats. Used to display them in the
     * second line on the screen when pressing C
     */
    public String getStatSummary2() {
        return statSummary = "HP: " + (int) hp + ". Max HP: " + (int) maxHP + ". Level: " + level + ". Crit: " + critChance;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * This method first determines the base stats the character receives
     * according to the class (e.g. Knight, Mob) Then it increases the stats
     * (except dexterity) by a certain % for each level. Next it increases the
     * dexterity by fixed amounts for each level / class Next it also multiplies
     * the stats if the character has certain names. e.g. "debug" increases them
     * to amounts that guarantee one hits The name "Osl" doubles all the stats
     * Then it creates a chance object and calculates the characters crit
     * chance. The last step is determining the characters health points by
     * multiplying the Stamina times 15.
     *
     * @param character The character whose stats get updated
     */
    public void statAdjustment(Character character) {
        String characterClass = character.getCharacterClass();
        String name = character.getName();
        switch (characterClass) {
            case "Knight":
                attack = 10;
                dexterity = 5;
                stamina = 15;
                defence = 15;
                break;
            case "Berserker":
                attack = 15;
                dexterity = 20;
                stamina = 10;
                defence = 10;
                break;
            case "Hunter":
                attack = 10;
                dexterity = 10;
                stamina = 10;
                defence = 5;
                break;
            case "REKTangel":
                stamina = 1;
            case "Mob":
                attack = 15;
                stamina = 5;
                defence = 5;
                break;
            case "Boss1":
                attack = 20;
                stamina = 30;
                defence = 10;
                break;
            default:
                break;
        }

        this.level = level;

        for (int x = level; x > 1; x--) {
            if (x <= 20) {
                attack = attack * 1.20;
                stamina = stamina * 1.20;
                defence = defence * 1.20;
            } else if (x <= 25) {
                attack = attack * 1.15;
                stamina = stamina * 1.15;
                defence = defence * 1.15;
            } else if (x <= 30) {
                attack = attack * 1.10;
                stamina = stamina * 1.10;
                defence = defence * 1.10;
            } else if (x <= 40) {
                attack = attack * 1.05;
                stamina = stamina * 1.05;
                defence = defence * 1.05;
            } else if (x <= 50) {
                attack = attack * 1.025;
                stamina = stamina * 1.025;
                defence = defence * 1.025;
            } else if (x > 50) {
                attack = attack * 1.01;
                stamina = stamina * 1.01;
                defence = defence * 1.01;
            }

            switch (characterClass) {
                case "Knight":
                    dexterity += 1;
//                    attack += 1;                    
//                    stamina += 3;
//                    defence += 2;
                    break;
                case "Berserker":
                    dexterity += 1.5;
//                    attack += 3;                    
//                    stamina += 1;
//                    defence += 1;
                    break;
                case "Hunter":
                    dexterity += 1.25;
//                    attack += 1;                    
//                    stamina += 1;
//                    defence += 1;
                    break;
//                case "Mob":
//                    attack += 2;
//                    stamina += 1;
//                    defence += 1;
//                    break;
//                case "Boss1":
//                    attack += 3;
//                    stamina += 3;
//                    defence += 2;
//                    break;
                default:
                    break;
            }

        }

        attack += character.getWeapon().getStats().getAttack();
        dexterity += character.getWeapon().getStats().getDexterity();
        stamina += character.getWeapon().getStats().getStamina();
        defence += character.getWeapon().getStats().getDefence();

        switch (name) {
            case "Osl":
                attack *= 2;
                stamina *= 2;
                defence *= 2;
                break;
            case "debug":
                attack = 666 * level;
                dexterity = 666 * level;
                stamina = 666 * level;
                defence = 666 * level;
                break;
            default:
                break;
        }

        Chance chanceCalc = new Chance(level, dexterity, characterClass);
        critChance = chanceCalc.calculateCritChance();

        this.hp = this.stamina * 15;

        this.maxHP = this.hp;
    }

}
