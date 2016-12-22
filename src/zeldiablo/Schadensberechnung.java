/**
 * @author Auer Marco
 */
package zeldiablo;

public class Schadensberechnung {
    
    public double berechnung(Spieler spieler, NPC gegner) {
        double waffe = spieler.getWaffe().getSchaden();
        double angriffskraft = spieler.getWerte().getAngriffskraft();
        double verteidigung = gegner.getWerte().getVerteidigung();
        return ((waffe + angriffskraft) * 2 - verteidigung);
    }
}
