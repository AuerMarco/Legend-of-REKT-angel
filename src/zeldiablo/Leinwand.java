/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.geom.RoundRectangle2D;

public class Leinwand extends JPanel {

    private Spieler spieler;

    private final Dimension groesse;
    public final String bilderVerzeichnis;
    private final String[] hintergrundBilderListe;
    private ImageIcon hintergrundBild;
    private ImageIcon dialogbox;

    private boolean gameOver;
    private int demoCounter;

    private Timer t;
    private NPC npc1, npc2;
    private NPC gegner1;
    private Gegenstand kiste1, kiste2, kiste3;
    private int angriffCD, pfeilCounter;
    private boolean pfeilSperre;
    public boolean wTaste, aTaste, sTaste, dTaste;

    boolean winkel1 = false, winkel3 = false, winkel5 = false, winkel7 = false;

    public Leinwand() {
        setFocusable(true);
        groesse = new Dimension(1180, 780);
        setPreferredSize(groesse);
        bilderVerzeichnis = "bilder/";
        hintergrundBilderListe = new String[]{"dialogbox.png", "bg_cobble.jpg", "bg_grass.jpg", "bg_matrix.jpg"};
        gameOver = false;
        demoCounter = 0;
        angriffCD = 0;
        wTaste = false;
        aTaste = false;
        sTaste = false;
        dTaste = false;
        pfeilSperre = false;

        initGame();
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    public NPC getGegner1() {
        return gegner1;
    }

    public void setGegner1(NPC gegner) {
        gegner1 = gegner;
    }

    private void initGame() {
        setHintergrund(3);
        spielObjekteErstellen();

        t = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doOnTick();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_W:
                        spieler.setBewegungsStatus(false);
                        spieler.setSpriteCounter(0);
                        wTaste = false;
                        break;
                    case VK_A:
                        spieler.setBewegungsStatus(false);
                        spieler.setSpriteCounter(0);
                        aTaste = false;
                        break;
                    case VK_S:
                        spieler.setBewegungsStatus(false);
                        spieler.setSpriteCounter(0);
                        sTaste = false;
                        break;
                    case VK_D:
                        spieler.setBewegungsStatus(false);
                        spieler.setSpriteCounter(0);
                        dTaste = false;
                        break;
//                    case VK_SHIFT:
//                        spieler.setGeschwindigkeit(spieler.getOriginalGeschwindigkeit());
//                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (sTaste && aTaste) {
                    spieler.setWinkel(2);
                    spieler.bewegungUntenLinks();
                } else if (wTaste && aTaste) {
                    spieler.setWinkel(4);
                    spieler.bewegungObenLinks();
                } else if (wTaste && dTaste) {
                    spieler.setWinkel(6);
                    spieler.bewegungObenRechts();
                } else if (sTaste && dTaste) {
                    spieler.setWinkel(8);
                    spieler.bewegungUntenRechts();
                } else if (e.getKeyCode() == VK_W) {
                    spieler.setWinkel(5);
                    spieler.bewegungOben();
                    wTaste = true;
                } else if (e.getKeyCode() == VK_A) {
                    spieler.setWinkel(3);
                    spieler.bewegungLinks();
                    aTaste = true;
                } else if (e.getKeyCode() == VK_S) {
                    spieler.setWinkel(1);
                    spieler.bewegungUnten();
                    sTaste = true;
                } else if (e.getKeyCode() == VK_D) {
                    spieler.setWinkel(7);
                    spieler.bewegungRechts();
                    dTaste = true;
                }

                switch (e.getKeyCode()) {
                    case VK_J:
                        spieler.objektInteraktion(spieler, kiste1);
                        spieler.objektInteraktion(spieler, npc1);
                        spieler.objektInteraktion(spieler, npc2);
                        break;
                    case VK_K:
                        if (angriffCD == 0) {
                            angriffCD = 15;
                            if (spieler.getKlasse() == "Krieger" || spieler.getKlasse() == "Barbar") {
                                spieler.getAngriffHitbox().setWaffenFrames(0);
                                spieler.getAngriffHitbox().nahkampf(spieler);
                            } else if (spieler.getKlasse() == "Jäger") {
                                winkel1 = false;
                                winkel3 = false;
                                winkel5 = false;
                                winkel7 = false;
//                                pfeilSperre = false;
                                spieler.setPfeilAktiv(true);
                                pfeilCounter = 0;                                
                                switch (spieler.getWinkel()) {
                                    case 1:
                                        winkel1 = true;
                                        break;
                                    case 3:
                                        winkel3 = true;
                                        break;
                                    case 5:
                                        winkel5 = true;
                                        break;
                                    case 7:
                                        winkel7 = true;
                                        break;
                                    default:
                                        break;
                                }
//                                winkel1 = false;
//                                winkel3 = false;
//                                winkel5 = false;
//                                winkel7 = false;
                            }
                        }
                        break;
//                    case VK_SHIFT:
//                        spieler.setGeschwindigkeit(spieler.getOriginalGeschwindigkeit() * 2);
//                        break;
                    case VK_ENTER:
                        spieler.getWerte().setHP(spieler.getWerte().getHP() - 10);
                        break;
                    case VK_C:
                        if (!spieler.getWerte().getSichtbarkeit()) {
                            spieler.getWerte().setSichtbarkeit(true);
                        } else if (spieler.getWerte().getSichtbarkeit()) {
                            spieler.getWerte().setSichtbarkeit(false);
                        }
                        break;
                    case VK_L:
                        spieler.levelUp();
                        break;
                }
            }
        });

    }

    private void spielObjekteErstellen() {                                          // hier werden die Spielobjekte erzeugt        
        spieler = new Spieler(new Koordinaten(460, 400), 200, 80, 1, "REKTangel", "Kyle", 1, 10);          //Parameter: Koordinaten, Breite, Höhe, Winkel, Klasse, Name bzw. ID, Level
        npc1 = new NPC(new Koordinaten(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion der Sonne", 1);
        npc2 = new NPC(new Koordinaten(350, 400), 48, 100, 4, "Schurke", "Unbekannter Schurke", 1);
        kiste1 = new Gegenstand(new Koordinaten(600, 400), 37, 35, "Kiste1", "Kiste 1");
//        kiste2 = new Gegenstand(new Koordinaten(650, 400), 37, 35, "Kiste1", "Kiste 2");
//        kiste3 = new Gegenstand(new Koordinaten(700, 400), 37, 35, "Kiste1", "Kiste 3");
        gegner1 = new NPC(new Koordinaten(400, 200), 200, 159, 1, "Boss1", "Asylum Demon", 1);

    }

    public void setHintergrund(int imageNumber) {
        String imagePath = bilderVerzeichnis + hintergrundBilderListe[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        hintergrundBild = new ImageIcon(imageURL);

        imagePath = bilderVerzeichnis + hintergrundBilderListe[0];
        imageURL = getClass().getResource(imagePath);
        dialogbox = new ImageIcon(imageURL);
    }

    private void spielStarten() {
        t.start();
    }

    public void spielPausieren() {
        t.stop();
    }

    public void spielFortsetzen() {
        if (!getGameOver()) {
            t.start();
        }
    }

    public void spielNeustarten() {
        demoCounter = 0;
        setGameOver(false);
        spielObjekteErstellen();
        spielStarten();
    }

    private void youShallNotPass() {
        //        if (spieler.beruehrt(kiste1) && spieler.getBewegungsStatus()&& spieler.getBewegungsStatus()) {     // && spieler.getBewegungsStatus()
//            spieler.setGeschwindigkeit(0);
//        } 
//        else {
//            spieler.setGeschwindigkeit(3);
//        }

        spieler.youShallNotPass(spieler, kiste1);
        spieler.youShallNotPass(spieler, npc1);
        spieler.youShallNotPass(spieler, npc2);
//        spieler.youShallNotPass(spieler, npc1);
//        spieler.youShallNotPass(spieler, kiste2);
//        spieler.youShallNotPass(spieler, kiste3);
    }

    public void hitDetektor() {
        spieler.getAngriffHitbox().hitDetektor(spieler, gegner1);
//        spieler.getAngriffHitbox().hitDetektor(spieler, npc2);
    }

    public void waffenRichtung() {
        switch (spieler.getWinkel()) {
            case 1:
                spieler.getAngriffHitbox().setSprite(0);
                break;
            case 3:
                spieler.getAngriffHitbox().setSprite(1);
                break;
            case 5:
                spieler.getAngriffHitbox().setSprite(2);
                break;
            case 7:
                spieler.getAngriffHitbox().setSprite(3);
                break;
            default:
                break;
        }
    }

    public void waffenCD() {
        if (angriffCD > 0) {
            angriffCD--;
        }
        if (gegner1.getInviFrames() > 0) {
            gegner1.setInviFrames(gegner1.getInviFrames() - 1);
        }
    }

    public void checkHP() {
        if (spieler.getWerte().getHP() <= 0) {
            gameOver = true;
            t.stop();
        }
        if (gegner1.getWerte().getHP() <= 0) {
            gegner1.setAmLeben(false);
        }
    }

    public void toteMobs() {
        NPC toterMob = new NPC(new Koordinaten(-1000, -1000), 0, 0, 1, "toterMob", "Toter Mob", 0);
        if (!gegner1.getAmLeben()) {
            gegner1 = toterMob;
        }
//        if (!npc2.getAmLeben()) {
//            npc2 = toterMob;
//        }
    }

//    public void pfeil() {
//        if (spieler.getPfeilAktiv()) {
//            if (spieler.getWinkel() == 1 && !winkel3 && !winkel5 && !winkel7) {
//                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX(), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe()) + (pfeilCounter * 5)), 13, 50, "Pfeil1"));
//                waffenRichtung();
//                winkel1 = true;
//            }
//            if (spieler.getWinkel() == 3 && !winkel1 && !winkel5 && !winkel7) {
//                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() - (pfeilCounter * 5), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe() / 2)), 50, 13, "Pfeil1"));
//                waffenRichtung();
//                winkel3 = true;
//            }
//            if (spieler.getWinkel() == 5 && !winkel1 && !winkel3 && !winkel7) {
//                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX(), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe()) - (pfeilCounter * 5)), 13, 50, "Pfeil1"));
//                waffenRichtung();
//                winkel5 = true;
//            }
//            if (spieler.getWinkel() == 7 && !winkel1 && !winkel3 && !winkel5) {
//                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() + (pfeilCounter * 5), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe() / 2)), 50, 13, "Pfeil1"));
//                waffenRichtung();
//                winkel7 = true;
//            }
//        }
//        pfeilCounter++;
//        if (pfeilCounter >= 250) {
//            spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(-1000, -1000), 0, 0, "Schwert1"));
//            winkel1 = false;
//            winkel3 = false;
//            winkel5 = false;
//            winkel7 = false;
//        }
//    }
    public void pfeil() {
        if (spieler.getPfeilAktiv()) {
            if (winkel1) {
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX(), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe()) + (pfeilCounter * 5)), 13, 50, "Pfeil1"));
//                if (!pfeilSperre) {
                    waffenRichtung();
//                    pfeilSperre = true;
//                }

            }
            if (winkel3) {
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() - (pfeilCounter * 5), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe() / 2)), 50, 13, "Pfeil1"));
//                if (!pfeilSperre) {
                    waffenRichtung();
//                    pfeilSperre = true;
//                }
            }
            if (winkel5) {
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX(), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe()) - (pfeilCounter * 5)), 13, 50, "Pfeil1"));
//                if (!pfeilSperre) {
                    waffenRichtung();
//                    pfeilSperre = true;
//                }
            }
            if (winkel7) {
                spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(spieler.getObjektPosition().getX() + (pfeilCounter * 5), (spieler.getObjektPosition().getY() + (int) spieler.getHoehe() / 2)), 50, 13, "Pfeil1"));
//                if (!pfeilSperre) {
                    waffenRichtung();
//                    pfeilSperre = true;
//                }
            }
        }
        pfeilCounter++;
        if (pfeilCounter >= 250) {
            spieler.setAngriffHitbox(new AngriffsAnimation(new Koordinaten(-1000, -1000), 0, 0, "Schwert1"));
            winkel1 = false;
            winkel3 = false;
            winkel5 = false;
            winkel7 = false;
        }
    }

    private void doOnTick() {

        youShallNotPass();
        checkHP();
        toteMobs();
        waffenRichtung();
        waffenCD();

        if (gegner1.getInviFrames() <= 0) {
            hitDetektor();
        }

        pfeil();

        if (spieler.getNPCdialog().getDialogSichtbarkeit()) {
            spieler.setGeschwindigkeit(0);
        } else {
            spieler.setGeschwindigkeit(spieler.getOriginalGeschwindigkeit());
        }

        spieler.laufAnimation();
        spieler.getAngriffHitbox().waffenFramesFunktion(spieler);

        repaint();
    }

    //Ab hier beginnen die Zeichnungsmethoden:
    public void charakterStats(Graphics g) {
        if (spieler.getWerte().getSichtbarkeit()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(Color.BLACK);
            g.drawString(spieler.getWerte().getWerteZusammenfassung1(), 50, 550);
            g.drawString(spieler.getWerte().getWerteZusammenfassung2() + ". Waffenschaden: " + (int) spieler.getWaffe().getSchaden(), 50, 575);

            g.setColor(Color.RED);
            g.drawString(gegner1.getWerte().getWerteZusammenfassung1(), 50, 625);
            g.drawString(gegner1.getWerte().getWerteZusammenfassung2() + ". Waffenschaden: " + (int) gegner1.getWaffe().getSchaden(), 50, 650);
        }
    }

    public void zeichneGegenstaende(Graphics g) {
        kiste1.zeichneObjekte(g);
//        kiste2.zeichneObjekte(g);
//        kiste3.zeichneObjekte(g);
    }

    public void zeichneGegner(Graphics g) {
        gegner1.zeichneObjekte(g);
    }

    public void zeichneNPCs(Graphics g) {
        npc1.zeichneObjekte(g);
        npc2.zeichneObjekte(g);
    }

    public void zeichneAngriffe(Graphics g) {
        spieler.getAngriffHitbox().zeichneObjekte(g);
    }

    public void zeichneDialog(Graphics g) {
        if (spieler.getNPCdialog().getDialogSichtbarkeit()) {
            dialogbox.paintIcon(null, g, 0, (780 - 140));

            if (spieler.getNPCdialog().getNPCsprite() != null) {
                spieler.getNPCdialog().getNPCsprite().paintIcon(null, g, 88, 666);
            }

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(new Color(255, 220, 70));
            g.drawString(spieler.getNPCdialog().getNPCname(), 230, 695);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(Color.WHITE);
            g.drawString(spieler.getNPCdialog().getDialogZeile1(), 230, 725);
            g.drawString(spieler.getNPCdialog().getDialogZeile2(), 230, 750);
        }
    }

    public void zeichneHealthbar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        RoundRectangle2D hpleiste = new RoundRectangle2D.Double(30, 30, 250, 30, 3, 3);
        g2d.fill(hpleiste);

        int aktuelleHPprozent = (int) (spieler.getWerte().getHP() / spieler.getWerte().getMaxHP() * 250);

        g2d.setColor(Color.GREEN);
        hpleiste = new RoundRectangle2D.Double(30, 30, aktuelleHPprozent, 30, 3, 3);
        g2d.fill(hpleiste);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString(spieler.getName(), 40, 22);
        g.drawString("" + (int) spieler.getWerte().getHP() + " / " + (int) spieler.getWerte().getMaxHP() + "  " + (int) (aktuelleHPprozent / 2.5) + "%", 40, 52);

        g2d.setColor(Color.RED);
        hpleiste = new RoundRectangle2D.Double(400, 30, 250, 30, 3, 3);
        g2d.fill(hpleiste);

        aktuelleHPprozent = (int) (gegner1.getWerte().getHP() / gegner1.getWerte().getMaxHP() * 250);

        g2d.setColor(Color.GREEN);
        hpleiste = new RoundRectangle2D.Double(400, 30, aktuelleHPprozent, 30, 3, 3);
        g2d.fill(hpleiste);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString(gegner1.getName(), 410, 22);
        g.drawString("" + (int) gegner1.getWerte().getHP() + " / " + (int) gegner1.getWerte().getMaxHP() + "  " + (int) (aktuelleHPprozent / 2.5) + "%", 410, 52);
    }

    @Override
    public void paintComponent(Graphics g) {
        hintergrundBild.paintIcon(null, g, 0, 0);           //hier wird das BG Bild gezeichnet        

//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
//        g.setColor(Color.BLACK);
//        g.drawString("Frames: " + demoCounter, 22, groesse.height - 5);        //Parameter: Text der dasteht | x-Position | y-Position
        zeichneGegenstaende(g);
        zeichneGegner(g);
        zeichneNPCs(g);

        spieler.zeichneObjekte(g);
        zeichneHealthbar(g);
        zeichneAngriffe(g);

        charakterStats(g);
        zeichneDialog(g);

        if (getGameOver()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            RoundRectangle2D gameOverBG = new RoundRectangle2D.Double(groesse.width / 4 - 25,
                    groesse.height / 3 + 50, 675, 100, 3, 3);
            g2d.fill(gameOverBG);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
            g.setColor(Color.RED);
            g.drawString("You died!", groesse.width / 4 + 50, groesse.height / 2);
        }
    }
}
