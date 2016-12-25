/**
 * @author Auer Marco
 */
package zeldiablo;

import java.util.concurrent.ThreadLocalRandom;

public class Chance {

    double min;
    double max;
    boolean success;
    double chance;
    double random;
    double level;
    double dex;
    String charaClass;
    
    public Chance(int level, double dex, String charaClass) {
        min = 0;
        max = 0;
        success = false;
        this.level = level;
        this.dex = dex;
        this.charaClass = charaClass;
        chance = calculateCritChance();
    }

    public Chance(double chance) {
        min = 1;
        max = 100;
        this.chance = chance;
    }

    public boolean getSuccess() {
        calculateSuccess();
        return success;
    }
    
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

    public void calculateSuccess() {
        random = ThreadLocalRandom.current().nextDouble(1, 100);
        if (random >= 0 && random <= chance) {
            success = true;
        } else {
            success = false;
        }
    }

}
