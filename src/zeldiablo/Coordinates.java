package zeldiablo;

import java.io.Serializable;

/**
 * Really simple class that contains the coordinates for all gameObjects
 * 
 * @author Auer Marco
 */
public class Coordinates implements Serializable {

    private int posX;
    private int posY;

    public Coordinates(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getX() {
        return posX;
    }
    public void setX(int x) {
        posX = x;
    }

    public int getY() {
        return posY;
    }
    public void setY(int y) {
        posY = y;
    }

}
