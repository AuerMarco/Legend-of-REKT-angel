/**
 * @author Auer Marco
 */
package zeldiablo;

public class Waffe {
    
    private double schaden;
    
    public Waffe() {
        schaden = 10;
    }
    
    public Waffe(int schaden) {
        this.schaden = schaden;
    }
    
    public double getSchaden() {
        return schaden;
    }
    
    public void setSchaden(double schaden) {
        this.schaden = schaden;
    }
    
}
