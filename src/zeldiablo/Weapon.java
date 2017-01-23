/**
 * @author Wedenig Manuel
 */
package zeldiablo;

import java.util.Random;

public class Weapon {

    private String name;
    private int damage;
    private int x;
    private int min;
    private int max;
    private int n;
    private Random r;

    public Weapon(int level) {
        name = "examplename";
        damage = 0;        
        n = 0;
        x = 0;
        r = new Random();
        randomDamage(level);
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setDamage(int value) {
        damage = value;
    }

    public void randomDamage(int level) {

        while (x < level) {
            n = n + 10;
            x++;
        }

        min = n / 2;
        max = n +1;
        damage = r.nextInt(max - min) + min;

    }

}
