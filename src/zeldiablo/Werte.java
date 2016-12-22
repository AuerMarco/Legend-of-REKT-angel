/**
 * @author Auer Marco
 */
package zeldiablo;

public class Werte {

    private double angriffskraft;
    private double geschicklichkeit;
    private double ausdauer;
    private double verteidigung;
    private double hp;
    private double maxHP;
    private int level;
    private String werteZusammenfassung;
    private boolean sichtbar;

    public Werte(String klasse, int level) {
        angriffskraft = 0;
        geschicklichkeit = 0;
        ausdauer = 0;
        verteidigung = 0;
        this.level = level;
        wertZuweisung(klasse, level);
        hp = ausdauer * 15;
        maxHP = hp;
        werteZusammenfassung = "";
    }

    public double getAngriffskraft() {
        return angriffskraft;
    }

    public void setAngriffskraft(double angriffskraft) {
        this.angriffskraft = angriffskraft;
    }

    public double getGeschicklichkeit() {
        return geschicklichkeit;
    }

    public void setGeschicklichkeit(double geschicklichkeit) {
        this.geschicklichkeit = geschicklichkeit;
    }

    public double getAusdauer() {
        return ausdauer;
    }

    public void setAusdauer(double ausdauer) {
        this.ausdauer = ausdauer;
    }

    public double getVerteidigung() {
        return verteidigung;
    }

    public void setVerteidigung(double verteidigung) {
        this.verteidigung = verteidigung;
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

    public String getWerteZusammenfassung1() {
        return werteZusammenfassung = "Angriffskraft: " + (int) angriffskraft + ". Geschicklichkeit: " + (int) geschicklichkeit + ". Ausdauer: " + (int) ausdauer + ". Verteidigung: " + (int) verteidigung;
    }

    public String getWerteZusammenfassung2() {
        return werteZusammenfassung = "HP: " + (int) hp + ". Max HP: " + (int) maxHP + ". Level: " + level;
    }

    public boolean getSichtbarkeit() {
        return sichtbar;
    }

    public void setSichtbarkeit(boolean sichtbarkeit) {
        this.sichtbar = sichtbarkeit;
    }

    public void wertZuweisung(String klasse, int level) {
        switch (klasse) {
            case "Krieger":
                angriffskraft = 10;
                geschicklichkeit = 5;
                ausdauer = 15;
                verteidigung = 15;
                break;
            case "Barbar":
                angriffskraft = 15;
                geschicklichkeit = 20;
                ausdauer = 10;
                verteidigung = 10;
                break;
            case "Jäger":
                angriffskraft = 10;
                geschicklichkeit = 10;
                ausdauer = 10;
                verteidigung = 5;
                break;
            case "REKTangel":
                ausdauer = 1;
            case "Mob":
                angriffskraft = 15;
                ausdauer = 5;
                verteidigung = 5;
                break;
            case "Boss1":
                angriffskraft = 20;
                ausdauer = 30;
                verteidigung = 10;
                break;
            case "debug":
                angriffskraft = 666;
                ausdauer = 666;
                verteidigung = 666;
                break;
            default:
                break;
        }

        this.level = level;

        for (int x = level; x > 1; x--) {
            angriffskraft = angriffskraft + ((angriffskraft * 20) / 100);
            ausdauer = ausdauer + ((ausdauer * 20) / 100);
            verteidigung = verteidigung + ((verteidigung * 20) / 100);
            geschicklichkeit = geschicklichkeit + ((geschicklichkeit * 5) / 100);
        }

        this.hp = this.ausdauer * 15;
        this.maxHP = this.hp;
    }

}
