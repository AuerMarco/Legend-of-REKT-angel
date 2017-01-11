/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

public class Canvas extends JPanel {

    private Player player;

    private final Dimension size;
    public final String imageDirectory;
    private final String[] bgPictureList;
    private ImageIcon backgroundPicture;
    private ImageIcon dialogBox;

    private boolean gameOver;
    private int demoCounter, hpTimer;

    private Timer t;
    private NPC npc1, npc2;
    private NPC mob1;
    private InteractionObjects chest1, chest2, chest3;
    private int arrowCounter;
    private boolean arrowLock;
    public boolean wKey, aKey, sKey, dKey;

    boolean angle1 = false, angle3 = false, angle5 = false, angle7 = false;

    public Canvas() {
        setFocusable(true);
        size = new Dimension(1180, 780);
        setPreferredSize(size);
        imageDirectory = "images/";
        bgPictureList = new String[]{"dialogbox.png", "bg_cobble.jpg", "bg_grass.jpg", "bg_matrix.jpg"};
        gameOver = false;
        demoCounter = 0;
        wKey = false;
        aKey = false;
        sKey = false;
        dKey = false;
        arrowLock = false;

        initGame();
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public NPC getMob1() {
        return mob1;
    }

    public void setMob1(NPC gegner) {
        mob1 = gegner;
    }

    private void initGame() {
        setBackground(3);
        createGameObjects();

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
                        player.setMoving(false);
                        player.setSpriteCounter(0);
                        wKey = false;
                        break;
                    case VK_A:
                        player.setMoving(false);
                        player.setSpriteCounter(0);
                        aKey = false;
                        break;
                    case VK_S:
                        player.setMoving(false);
                        player.setSpriteCounter(0);
                        sKey = false;
                        break;
                    case VK_D:
                        player.setMoving(false);
                        player.setSpriteCounter(0);
                        dKey = false;
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (sKey && aKey) {
                    player.setAngle(2);
                    player.moveDownLeft();
                } else if (wKey && aKey) {
                    player.setAngle(4);
                    player.moveUpLeft();
                } else if (wKey && dKey) {
                    player.setAngle(6);
                    player.moveUpRight();
                } else if (sKey && dKey) {
                    player.setAngle(8);
                    player.moveDownRight();
                } else if (e.getKeyCode() == VK_W) {
                    player.setAngle(5);
                    player.moveUp();
                    wKey = true;
                } else if (e.getKeyCode() == VK_A) {
                    player.setAngle(3);
                    player.moveLeft();
                    aKey = true;
                } else if (e.getKeyCode() == VK_S) {
                    player.setAngle(1);
                    player.moveDown();
                    sKey = true;
                } else if (e.getKeyCode() == VK_D) {
                    player.setAngle(7);
                    player.moveRight();
                    dKey = true;
                }

                switch (e.getKeyCode()) {
                    case VK_J:
                        player.objectInteraction(player, chest1);
                        player.objectInteraction(player, npc1);
                        player.objectInteraction(player, npc2);
                        break;
                    case VK_K:
                        if (player.getAttackCD() == 0) {
                            player.setAttackCD(20);
                            if (player.getCharacterClass() == "Knight" || player.getCharacterClass() == "Berserker") {
                                player.getAttackHitbox().setWeaponFrames(0);
                                player.getAttackHitbox().melee(player);
                            } else if (player.getCharacterClass() == "Hunter") {
                                angle1 = false;
                                angle3 = false;
                                angle5 = false;
                                angle7 = false;
//                                arrowLock = false;
                                player.setArrowActive(true);
                                arrowCounter = 0;
                                switch (player.getAngle()) {
                                    case 1:
                                        angle1 = true;
                                        break;
                                    case 3:
                                        angle3 = true;
                                        break;
                                    case 5:
                                        angle5 = true;
                                        break;
                                    case 7:
                                        angle7 = true;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        break;
                    case VK_ENTER:
//                        System.out.println("---");
//                        DamageCalculation damage = new DamageCalculation();
//                        double dmg = damage.damageCalculation(player, mob1, false);
//                        double hits = mob1.getStats().getHP() / dmg;
//                        System.out.println("Player to mob DMG: " + dmg + ". Hits: " + hits);
//                        
//                        dmg = damage.damageCalculation(mob1, player, false);
//                        hits = player.getStats().getHP() / dmg;
//                        System.out.println("Mob to player DMG: " + dmg + ". Hits: " + hits);

//                        player.getStats().setHP(player.getStats().getHP() - 10);
                        player.increaseXP(10);
//                        System.out.println(mob1.getAttackHitbox().getObjectPosition().getX());
//                        System.out.println(player.getXP());

//                        int x = 0;
//                        for (int i = 0; i < 100; i++) {
//                            Chance chance = new Chance(player.getStats().getCritChance());
//                            boolean success = chance.getSuccess();
//                            System.out.println(success);
//                            if (success) {
//                                x++;
//                            }      
//                        }
//                        System.out.println("Erfolge: " + x);
                        break;
                    case VK_C:
                        if (!player.getStats().getVisible()) {
                            player.getStats().setVisible(true);
                        } else if (player.getStats().getVisible()) {
                            player.getStats().setVisible(false);
                        }
                        break;
                    case VK_L:
                        player.levelUp();
                        break;
                    case VK_M:
                        mob1.levelUp();
                        break;
                    case VK_B:
                        player.levelUp();
                        mob1.levelUp();
                        break;
                    case VK_R:
                        int n = 0;
                        if (player.getLevel() == 1) {
                            n = 1;
                        } else {
                            n = player.getLevel() - 1;
                        }
                        setMob1(new NPC(new Coordinates(460, 200), 39, 36, 1, "Mob", "Orc", n, 10));
                        break;
                }
            }
        });

    }

    private void createGameObjects() {                                          // hier werden die Spielobjekte erzeugt        
        player = new Player(new Coordinates(460, 700), 35, 80, 1, "Knight", "debug", 1, 10);          //Parameter: Coordinates, Breite, Höhe, Winkel, Klasse, Name bzw. ID, Level, WaffenDMG
        npc1 = new NPC(new Coordinates(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion of the sun", 1, 10);
        npc2 = new NPC(new Coordinates(350, 400), 48, 100, 4, "Rogue", "Unknown rogue", 1, 10);
        chest1 = new InteractionObjects(new Coordinates(600, 400), 37, 35, "Chest1", "Chest 1");
//        chest2 = new InteractionObjects(new Coordinates(650, 400), 37, 35, "Kiste1", "Kiste 2");
//        chest3 = new InteractionObjects(new Coordinates(700, 400), 37, 35, "Kiste1", "Kiste 3");
        mob1 = new NPC(new Coordinates(460, 100), 80, 70, 1, "Mob", "Orc", 1, 10);
    }

    public void setBackground(int imageNumber) {
        String imagePath = imageDirectory + bgPictureList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        backgroundPicture = new ImageIcon(imageURL);

        imagePath = imageDirectory + bgPictureList[0];
        imageURL = getClass().getResource(imagePath);
        dialogBox = new ImageIcon(imageURL);
    }

    private void startGame() {
        t.start();
    }

    public void pauseGame() {
        t.stop();
    }

    public void continueGame() {
        if (!getGameOver()) {
            t.start();
        }
    }

    public void restartGame() {
        demoCounter = 0;
        setGameOver(false);
        createGameObjects();
        startGame();
    }

    private void youShallNotPass() {
        player.youShallNotPass(player, chest1);
        player.youShallNotPass(player, npc1);
        player.youShallNotPass(player, npc2);
//        player.youShallNotPass(player, chest2);
//        player.youShallNotPass(player, chest3);
    }

    public void hitDetect() {
        if (mob1.getInviFrames() <= 0) {
            player.getAttackHitbox().hitDetect(player, mob1);
        }
//        player.getAttackHitbox().hitDetect(player, npc2);
        if (player.getInviFrames() <= 0) {
            mob1.getAttackHitbox().hitDetect(mob1, player);
        }

    }

    public void weaponDirection() {
        switch (player.getAngle()) {
            case 1:
                player.getAttackHitbox().setSprite(0);
                break;
            case 3:
                player.getAttackHitbox().setSprite(1);
                break;
            case 5:
                player.getAttackHitbox().setSprite(2);
                break;
            case 7:
                player.getAttackHitbox().setSprite(3);
                break;
            default:
                break;
        }
    }

    public void attackCD() {
        if (player.getAttackCD() > 0) {
            player.setAttackCD(player.getAttackCD() - 1);
        }
        if (mob1.getAttackCD() > 0) {
            mob1.setAttackCD(mob1.getAttackCD() - 1);
        }

        if (mob1.getInviFrames() > 0) {
            mob1.setInviFrames(mob1.getInviFrames() - 1);
        }
        if (player.getInviFrames() > 0) {
            player.setInviFrames(player.getInviFrames() - 1);
        }
    }

    public void checkHP() {
        if (player.getStats().getHP() <= 0) {
            gameOver = true;
            t.stop();
        }
        if (mob1.getStats().getHP() <= 0) {
            mob1.setAlive(false);
            player.increaseXP(mob1.getXP());
        }
    }

    public void deadMobs() {
        NPC toterMob = new NPC(new Coordinates(-1000, -1000), 0, 0, 1, "placeholder", "Mob placeholder", 0, 10);
        if (!mob1.getAlive()) {
            mob1 = toterMob;
        }
    }

    public void arrow() {
        if (player.getArrowActive()) {
            if (angle1) {
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX(), (player.getObjectPosition().getY() + (int) player.getHeight()) + (arrowCounter * 5)), 13, 50, "Arrow1"));
//                if (!arrowLock) {
                weaponDirection();
//                    arrowLock = true;
//                }

            }
            if (angle3) {
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX() - (arrowCounter * 5), (player.getObjectPosition().getY() + (int) player.getHeight() / 2)), 50, 13, "Arrow1"));
//                if (!arrowLock) {
                weaponDirection();
//                    arrowLock = true;
//                }
            }
            if (angle5) {
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX(), (player.getObjectPosition().getY() + (int) player.getHeight()) - (arrowCounter * 5)), 13, 50, "Arrow1"));
//                if (!arrowLock) {
                weaponDirection();
//                    arrowLock = true;
//                }
            }
            if (angle7) {
                player.setAttackHitbox(new AttackAnimation(new Coordinates(player.getObjectPosition().getX() + (arrowCounter * 5), (player.getObjectPosition().getY() + (int) player.getHeight() / 2)), 50, 13, "Arrow1"));
//                if (!arrowLock) {
                weaponDirection();
//                    arrowLock = true;
//                }
            }
        }
        if (player.getArrowActive()) {
            arrowCounter++;
            if (arrowCounter >= 250) {
                player.setAttackHitbox(new AttackAnimation(new Coordinates(-1000, -1000), 0, 0, "Sword1"));
                angle1 = false;
                angle3 = false;
                angle5 = false;
                angle7 = false;
            }
        }

    }

    private void hpRegeneration() {
        hpTimer++;
        if (hpTimer == 100) {
            if (player.getStats().getHP() < player.getStats().getMaxHP()) {
                player.getStats().setHP(player.getStats().getHP() + player.getStats().getMaxHP() * 0.01);
                if (player.getStats().getHP() > player.getStats().getMaxHP()) {
                    player.getStats().setHP(player.getStats().getMaxHP());
                }
            }
            hpTimer = 0;
        }
    }

    private void checkXP() {
        if (player.getXP() >= player.getXPneeded()) {
            double xpDelta = 0;
            if (player.getXP() > player.getXPneeded()) {
                xpDelta = player.getXP() - player.getXPneeded();
            }
            player.levelUp();
            player.setXP(player.getXP() + xpDelta);
        }
    }

    private void mobAttack() {
//        demoCounter++;
//        if (demoCounter >= 50) {
        mob1.aggroRange(player);
//            mob1.attack();
        demoCounter = 0;
//        }

//        if (mob1.getAttackCD() == 0) {
//            mob1.setAttackCD(20);
//            mob1.getAttackHitbox().melee(mob1);
//        }
//        mob1.getAttackHitbox().setWeaponFrames(0);
    }

    private void hitboxUpdate() {
        player.hitboxUpdate();
        mob1.hitboxUpdate();
    }

    private void doOnTick() {

        hitboxUpdate();
        youShallNotPass();
        checkXP();
        checkHP();
        deadMobs();
        weaponDirection();
        attackCD();
        hpRegeneration();
        mobAttack();
        hitDetect();

        arrow();

        if (player.getNPCdialog().getDialogVisible()) {
            player.setSpeed(0);
        } else {
            player.setSpeed(player.getOriginalSpeed());
        }

        player.walkingAnimation();
        player.getAttackHitbox().weaponFramesFunction(player);
        mob1.getAttackHitbox().weaponFramesFunction(mob1);

        repaint();
    }

    //Ab hier beginnen die Zeichnungsmethoden:
    public void characterStats(Graphics g) {
        if (player.getStats().getVisible()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(Color.BLACK);
            g.drawString(player.getStats().getStatSummary1(), 50, 550);
            g.drawString(player.getStats().getStatSummary2() + ". Weapon damage: " + (int) player.getWeapon().getDamage(), 50, 575);

            g.setColor(Color.RED);
            g.drawString(mob1.getStats().getStatSummary1(), 50, 650);
            g.drawString(mob1.getStats().getStatSummary2() + ". Weapon damage: " + (int) mob1.getWeapon().getDamage(), 50, 675);
        }
    }

    public void drawInteractionObjects(Graphics g) {
        chest1.drawObjects(g);
//        chest2.drawObjects(g);
//        chest3.drawObjects(g);
    }

    public void drawMobs(Graphics g) {
        mob1.drawObjects(g);
    }

    public void drawNPCs(Graphics g) {
        npc1.drawObjects(g);
        npc2.drawObjects(g);
    }

    public void drawAttacks(Graphics g) {
        player.getAttackHitbox().drawObjects(g);
        mob1.getAttackHitbox().drawObjects(g);
    }

    public void drawDialog(Graphics g) {
        if (player.getNPCdialog().getDialogVisible()) {
            dialogBox.paintIcon(null, g, 0, (780 - 140));

            if (player.getNPCdialog().getNPCsprite() != null) {
                player.getNPCdialog().getNPCsprite().paintIcon(null, g, 88, 666);
            }

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(new Color(255, 220, 70));
            g.drawString(player.getNPCdialog().getNPCname(), 230, 695);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(Color.WHITE);
            g.drawString(player.getNPCdialog().getDialogLine1(), 230, 725);
            g.drawString(player.getNPCdialog().getDialogLine2(), 230, 750);
        }
    }

    public void drawHealthbar(Graphics g) {
        //This part draws the players HP bar (how much health the player has)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        RoundRectangle2D hpBar = new RoundRectangle2D.Double(30, 30, 250, 30, 3, 3);
        g2d.fill(hpBar);

        double currentHPpercent = (player.getStats().getHP() / player.getStats().getMaxHP());

        g2d.setColor(Color.GREEN);
        hpBar = new RoundRectangle2D.Double(30, 30, 250 * currentHPpercent, 30, 3, 3);
        g2d.fill(hpBar);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString(player.getName(), 40, 22);
        g.drawString("" + (int) player.getStats().getHP() + " / " + (int) player.getStats().getMaxHP() + "  " + (int) (currentHPpercent * 100) + "%", 40, 52);

        //This part is the mob HP bar
        g2d.setColor(Color.RED);
        hpBar = new RoundRectangle2D.Double(400, 30, 250, 30, 3, 3);
        g2d.fill(hpBar);

        currentHPpercent = (mob1.getStats().getHP() / mob1.getStats().getMaxHP());

        g2d.setColor(Color.GREEN);
        hpBar = new RoundRectangle2D.Double(400, 30, 250 * currentHPpercent, 30, 3, 3);
        g2d.fill(hpBar);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString(mob1.getName(), 410, 22);
        g.drawString("" + (int) mob1.getStats().getHP() + " / " + (int) mob1.getStats().getMaxHP() + "  " + (int) (currentHPpercent * 100) + "%", 410, 52);
    }

    public void drawXPbar(Graphics g) {
        //This part draws the players XP bar (how much experience he has and needs to level up)
//        if (player.getStats().getVisible()) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        RoundRectangle2D hpBar = new RoundRectangle2D.Double(30, 66, 250, 30, 3, 3);
        g2d.fill(hpBar);

        double currentXPpercent = ((player.getXP() / player.getXPneeded()));

        g2d.setColor(new Color(135, 0, 135));
        hpBar = new RoundRectangle2D.Double(30, 66, 250 * currentXPpercent, 30, 3, 3);
        g2d.fill(hpBar);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("" + (int) player.getXP() + " / " + (int) player.getXPneeded() + "  " + (int) (currentXPpercent * 100) + "%", 40, 88);
//        }
    }

    public void drawXPstatus(Graphics g) {
        if (player.getStats().getVisible()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(Color.BLACK);
            double mobsNeeded = player.getXPneeded() / mob1.getXP();
            g.drawString("XP: " + (int) player.getXP() + ". XP needed: " + (int) player.getXPneeded() + ". Mobs needed: " + mobsNeeded, 50, 600);

            g.setColor(Color.RED);
            g.drawString("XP: " + (int) mob1.getXP(), 50, 700);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundPicture.paintIcon(null, g, 0, 0);           //hier wird das BG Bild gezeichnet        

//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
//        g.setColor(Color.BLACK);
//        g.drawString("Frames: " + demoCounter, 22, size.height - 5);        //Parameter: Text der dasteht | x-Position | y-Position
        drawInteractionObjects(g);
        drawMobs(g);
        drawNPCs(g);

        player.drawObjects(g);
        drawHealthbar(g);
        drawXPbar(g);
        drawAttacks(g);

        characterStats(g);
        drawXPstatus(g);
        drawDialog(g);

        if (getGameOver()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            RoundRectangle2D gameOverBG = new RoundRectangle2D.Double(size.width / 4 - 25,
                    size.height / 3 + 50, 675, 100, 3, 3);
            g2d.fill(gameOverBG);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
            g.setColor(Color.RED);
            g.drawString("You died!", size.width / 4 + 50, size.height / 2);
        }
    }
}
