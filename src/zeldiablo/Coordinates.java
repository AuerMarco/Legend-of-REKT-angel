/**
 * @author Auer Marco
 */
package zeldiablo;

public class Coordinates {

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
