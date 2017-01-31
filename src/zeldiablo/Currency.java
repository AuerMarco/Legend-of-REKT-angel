/**
 * @author Wedenig Manuel
 */
package zeldiablo;

import java.io.Serializable;

/**
 * This class adds a currency to the game
 *
 * @author Wedenig Manuel
 */
public class Currency implements Serializable {

    //Currency is called silverserpents
    private int silverserpents;

    /**
     * This constructor simply sets the start value of the silverserpents
     * (that's the name of our currency) to 0
     */
    public Currency() {
        silverserpents = 0;
    }

    /**
     * Simple method that returns the amount of silverserpents the player
     * currently has
     *
     * @return the current amount of money
     */
    public int getSilverserpents() {
        //to check out the current amount of silverserpents
        return silverserpents;
    }

    /**
     * Sets. the silverserpents to a certain value
     *
     * @param value new value of the currency
     */
    public void setValue(int value) {
        silverserpents = value;
    }

    /**
     * Increases. the silverserpents by a certain amount
     *
     * @param value
     */
    public void changeValue(int value) {
        silverserpents = silverserpents + value;
    }

    /**
     * This method calculates the amount of money a mob gives the player. It
     * starts with 25 and adds 5 for every Level the mob has. for example 30 for
     * Level 1 and 35 for Level 2
     *
     * @param level Level the mob has
     */
    public void mobdrop(int level) {
        int dropvalue;

        if (level >= 1) {
            dropvalue = 25 + (level * 5);
            silverserpents = silverserpents + dropvalue;
        } else {
            dropvalue = 0;
        }

    }

}
