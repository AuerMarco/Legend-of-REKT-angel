package zeldiablo;

import java.util.concurrent.ThreadLocalRandom;
import java.io.Serializable;

/**
 * This class has two functions
 * 1) calculating the chance a player has to land a critical hit (according to the players dexterity)
 * 2) It will determine wether or not a certain chance was succesfull
 * For example if you have 25% crit chance it will determine if you actually landed a crit with that 25%
 * It also determines wether or not a mob drops ("gives" you) a weapon on dying
 * 
 * @author Auer Marco
 */
public class Chance implements Serializable {

    double min;
    double max;
    boolean success;
    double chance;
    double random;
    double level;
    double dex;
    String charaClass;
    
    /**
     * Constructor that is used to determine a players crit chance
     * @param level Level of the player
     * @param dex Amount of dexterity a player has
     * @param charaClass Class of the player (also plays a role in how much crit you can get / have)
     */
    public Chance(int level, double dex, String charaClass) {
        min = 0;
        max = 0;
        success = false;
        this.level = level;
        this.dex = dex;
        this.charaClass = charaClass;
        chance = calculateCritChance();
    }

    /**
     * Constructor that is used when the chance is predetermined (mostly for deciding if you get loot from a mob or not)
     * @param chance A fixed chance... for example you can use this class and order it to check for success with 25% 
     */
    public Chance(double chance) {
        min = 1;
        max = 100;
        this.chance = chance;
    }

    /**
     * When you use the getMethod it automatically calls the method that determines if it was a success this time or not 
     * 
     * @return succesful or not (crit / loot)
     */
    public boolean getSuccess() {
        calculateSuccess();
        return success;
    }
    
    /**
     * Calculates the crit chance a player has
     * The player-clases have different amount of fixed chance to crit and also a different maximum they can have
     * @return critchance 
     */
    public double calculateCritChance() {
        switch (charaClass) {
            case "Knight":
                min = 5;
                max = 50;
                break;
            case "Berserker":
                min = 20;
                max = 66.6;
                break;
            case "Hunter":
                min = 10;
                max = 50;
                break;
            default:
                break;
        }
        
        chance = min + ((dex - min)/2);
        
        if (chance > max) {
            chance = max;
        }       
        
        return chance;        
    }

    /**
     * Determines wether or  not something was succesful or not by using the chance
     */
    public void calculateSuccess() {
        random = ThreadLocalRandom.current().nextDouble(1, 100);
        if (random >= 0 && random <= chance) {
            success = true;
        } else {
            success = false;
        }
    }

}
