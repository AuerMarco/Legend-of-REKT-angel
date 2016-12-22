/**
 * @author Auer Marco
 */
package zeldiablo;

public class NPC extends Figur {
    
    public NPC(Koordinaten position, int breite, int hoehe, int winkel, String klasse, String name, int level, int schaden) {
        super(position, breite, hoehe, winkel, klasse, name, level, schaden);
    }  
    
    public NPC(Koordinaten position, int breite, int hoehe, int winkel, String klasse, String name, int level) {
        super(position, breite, hoehe, winkel, klasse, name, level);
    }  
    
}
