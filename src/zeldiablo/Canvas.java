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
import java.io.Serializable;

/**
 * This is pretty much the main class of my game - it acts the canvas - the
 * stage where everything is drawn on and happening
 *
 * @author Auer Marco
 */
public class Canvas extends JPanel implements Serializable {

    private Player player;

    private final Dimension size;
    public final String imageDirectory;
    private final String[] bgPictureList, spriteList;
    private ImageIcon backgroundPicture, dialogBox, levelUp, inventory, stats;

    private boolean gameOver;
    private int demoCounter, hpTimer;

    private Timer t;
    private NPC npc1, npc2;
    private NPC mob1;
    private InteractionObjects chest1, chest2, chest3;
    private int arrowCounter;
    private boolean arrowLock;
    public boolean wKey, aKey, sKey, dKey;

    private boolean angle1 = false, angle3 = false, angle5 = false, angle7 = false;

    private boolean inventoryVisible;
    private int inventoryHighlight, inventoryCounter;
    private int invPosi1 = inventoryCounter, invPosi2 = 1, invPosi3 = 2, invPosi4 = 3, invPosi5 = 4;

    /**
     * This constructor sets all the basics of the canvas, like size etc Then it
     * uses an image array, similiar to what you saw / read in the
     * AttackAnimation class
     */
    public Canvas() {
        setFocusable(true);
        size = new Dimension(1180, 780);
        setPreferredSize(size);
        imageDirectory = "images/";
        bgPictureList = new String[]{"bg_cobble.jpg", "bg_grass.jpg", "bg_matrix.jpg"};
        spriteList = new String[]{"dialogbox.png", "LevelUp.png", "inventory.png", "stats.png"};
        gameOver = false;
        demoCounter = 0;
        wKey = false;
        aKey = false;
        sKey = false;
        dKey = false;
        arrowLock = false;
        inventoryHighlight = 1;
        inventoryCounter = 0;

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

    /**
     * This method will change the currently present mob1 to a new, as every
     * areal (part of the world) has different mobs
     *
     * @param mob Enemy that the current mob1 will get changed to
     */
    public void setMob1(NPC mob) {
        mob1 = mob;
    }

    /**
     * This mob starts the game up by setting the background (or rather, calling
     * the method that does it) and then calling the method that creates the
     * game objects like NPCs and objects like chests It also contains the very,
     * very important KeyListener that allows to control and play the game!
     */
    private void initGame() {
        setBackground(2);
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
                        if (inventoryVisible) {
                            if (player.getInventar().size() != 0) {
                                equipWeapon();
                            }
                        }
                        if (!inventoryVisible) {
                            player.objectInteraction(player, chest1);
                            player.objectInteraction(player, npc1);
                            player.objectInteraction(player, npc2);
                        }
                        if (player.getNPCdialog().getDialogVisible()) {
                            player.getNPCdialog().dialogLogic();
                        }
                        break;
                    case VK_K:
                        if (player.getAttackCD() <= 0) {
                            player.setAttackCD(20);
                            if (player.getCharacterClass() != "Hunter") {      //player.getCharacterClass() == "Knight" || player.getCharacterClass() == "Berserker"
                                player.getAttackHitbox().setWeaponFrames(0);
                                player.getAttackHitbox().melee(player);
                            } else {
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
//                        System.out.println(mob1.getAngle());
//                        System.out.println("---");
//                        DamageCalculation damage = new DamageCalculation();
//                        double dmg = damage.damageCalculation(player, mob1, false);
//                        double hits = mob1.getStats().getHP() / dmg;
//                        System.out.println("Player to mob DMG: " + dmg + ". Hits: " + hits);
//                        
//                        dmg = damage.damageCalculation(mob1, player, false);
//                        hits = player.getStats().getHP() / dmg;
//                        System.out.println("Mob to player DMG: " + dmg + ". Hits: " + hits);

//                        int x = 0;
//                        while (x < player.getLevel()) {
//                            player.getCurrency().changeValue(10);
//                            x++;
//                        }
//                        player.getStats().setHP(player.getStats().getHP() - 10);
//                        player.increaseXP(10);
                        System.out.println("Equiped weapon: Damage: " + player.getWeapon().getDamage() + " Str:" + (int) player.getWeapon().getStats().getAttack() + " Dex:" + (int) player.getWeapon().getStats().getDexterity() + " Stam:" + (int) player.getWeapon().getStats().getStamina() + " Def:" + (int) player.getWeapon().getStats().getDefence());
                        int posi = 1;
                        for (Weapon weapon : player.getInventar()) {
                            System.out.println("Position: " + posi + ", " + weapon.getDamage());
                            posi++;
                        }
                        break;
                    case VK_C:
                        if (!player.getNPCdialog().getDialogVisible()) {
                            if (!player.getStats().getVisible()) {
                                player.getStats().setVisible(true);
                            } else if (player.getStats().getVisible()) {
                                player.getStats().setVisible(false);
                            }
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
                        setMob1(new NPC(new Coordinates(460, 200), 80, 70, 1, "Mob", "Orc", n));
                        break;
                    case VK_T:
                        if (!mob1.getAggro()) {
                            mob1.setAggro(true);
                        } else if (mob1.getAggro()) {
                            mob1.setAggro(false);
                        }
                        break;
                    case VK_I:
                        inventoryHighlight = 1;
                        inventoryCounter = 0;
                        if (!player.getNPCdialog().getDialogVisible()) {
                            if (inventoryVisible) {
                                inventoryVisible = false;
                            } else {
                                inventoryVisible = true;
                            }
                        }
                        break;
                    case VK_UP:
//                        System.out.println(inventoryHighlight);
//                        if (inventoryHighlight > 1 && inventoryCounter < 4) {    // && inventoryCounter != player.getInventar().size()-1      // inventoryHighlight <= 5
//                            inventoryHighlight--;
//                        }
//                        System.out.println(inventoryCounter);
                        if (inventoryCounter > 0) {
                            inventoryCounter--;
                        }
                        break;
                    case VK_DOWN:
//                        System.out.println(inventoryHighlight);                        
//                        if (inventoryHighlight >= 1 && inventoryHighlight < 5 && inventoryHighlight < player.getInventar().size()) {
//                            inventoryHighlight++;
//                        }
//                        System.out.println(inventoryCounter);
                        if (inventoryCounter < player.getInventar().size() - 1) {
                            inventoryCounter++;
                        }
                        break;
                    case VK_E:
                        player.getInventar().add(new Weapon(player.getLevel()));
                        player.getInventar().add(new Weapon(player.getLevel()));
                        player.getInventar().add(new Weapon(player.getLevel()));
                        player.getInventar().add(new Weapon(player.getLevel()));
                        player.getInventar().add(new Weapon(player.getLevel()));
                        break;
                    case VK_ESCAPE:
                        if (inventoryVisible || player.getStats().getVisible() || player.getLootVisible()) {
                            inventoryVisible = false;
                            player.getStats().setVisible(false);
                            player.setLootVisible(false);
                        }
                        break;
                    case VK_F5:
                        SaveFile file = new SaveFile();
                        file.createSaveFile(player);
                        break;
                    case VK_F9:
                        file = new SaveFile();
                        Player playerLoader = file.loadSaveFile("c:\\temp\\player.ser");
                        file.loadPlayer(player, playerLoader);
                        break;                        
                }
            }
        });
    }

    /**
     * This method creates all the currently present entities of the map. Later
     * they will get replaced by placeholders and concrete mobs with their
     * unique locations will be spawned via an another class
     */
    private void createGameObjects() {                                          // hier werden die Spielobjekte erzeugt        
        player = new Player(new Coordinates(460, 700), 35, 80, 1, "Knight", "Kyle", 1, "Broken Sword", 0, 0, 0, 0, 0);          //Parameter: Coordinates, Breite, Höhe, Winkel, Klasse, Name bzw. ID, Level, und dann 5 Parameter zum erstellen der Waffe
        npc1 = new NPC(new Coordinates(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion of the sun", 1);
        npc2 = new NPC(new Coordinates(350, 400), 48, 100, 4, "Rogue", "Unknown rogue", 1);
        chest1 = new InteractionObjects(new Coordinates(600, 400), 37, 35, "Chest1", "StartWeapon");
        mob1 = new NPC(new Coordinates(460, 300), 80, 70, 1, "Mob", "Orc", 1);
    }

    /**
     * First it sets the path consisting of the directory and the String from
     * the spriteList. For example: "images/bg_matrix.jpg" Second it creates an
     * imageURL with the newly defined imagePath Last it will create an
     * ImageIcon object, the sprite, according to the newly created imageURL
     *
     * It does this for all three ImageIcons that are present at the same time
     *
     * @param imageNumber decides what index of the spriteList will be accessed
     */
    public void setBackground(int imageNumber) {
        String imagePath = imageDirectory + bgPictureList[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        backgroundPicture = new ImageIcon(imageURL);

        imagePath = imageDirectory + spriteList[0];
        imageURL = getClass().getResource(imagePath);
        dialogBox = new ImageIcon(imageURL);

        imagePath = imageDirectory + spriteList[1];
        imageURL = getClass().getResource(imagePath);
        levelUp = new ImageIcon(imageURL);

        imagePath = imageDirectory + spriteList[2];
        imageURL = getClass().getResource(imagePath);
        inventory = new ImageIcon(imageURL);

        imagePath = imageDirectory + spriteList[3];
        imageURL = getClass().getResource(imagePath);
        stats = new ImageIcon(imageURL);
    }

    /**
     * Starts the timer (which is part of the ActionListener).
     */
    private void startGame() {
        t.start();
    }

    /**
     * Stops the timer (which is part of the ActionListener).
     */
    public void pauseGame() {
        t.stop();
    }

    /**
     * Continues (starts) the timer (which is part of the ActionListener) if the
     * player isn't gameOver (dead).
     */
    public void continueGame() {
        if (!getGameOver()) {
            t.start();
        }
    }

    /**
     * Resets all entities to their defaults (defined in the method
     * createGameObjects() discussed earlier)
     */
    public void restartGame() {
        demoCounter = 0;
        setGameOver(false);
        createGameObjects();
        startGame();
    }

    /**
     * Collection method that checks if the player is trying to walk into an
     * object on every frame One method call for each object / npc
     */
    private void youShallNotPass() {
        player.youShallNotPass(player, chest1);
//        player.youShallNotPass(player, chest2);
//        player.youShallNotPass(player, chest3);

        player.youShallNotPass(player, npc1);
        player.youShallNotPass(player, npc2);
    }

    /**
     * Collection method that checks if a player or mob is hit One method call
     * for each npc / player combination
     */
    public void hitDetect() {
        if (mob1.getInviFrames() <= 0) {
            player.getAttackHitbox().hitDetect(player, mob1);
        }

        if (player.getInviFrames() <= 0) {
            mob1.getAttackHitbox().hitDetect(mob1, player);
        }

    }

    /**
     * This method changes the weapon's direction according to the player's
     * angle. It does this by simply changing to the correct weapon sprite
     */
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

    /**
     * This method prevents the player from using the weapon too fast It also
     * makes the player / mob vincible again by reducing the invincibility
     * frames on every frame
     */
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

    /**
     * This method checks if a player or mob has 0 health and performs an
     * according action
     */
    public void checkHP() {
        if (player.getStats().getHP() <= 0) {
            gameOver = true;
            t.stop();
        }

        if (mob1.getStats().getHP() <= 0) {
            Chance chance = new Chance(10);
            if (chance.getSuccess() && mob1.getLevel() != 0) {
                Weapon loot = new Weapon(mob1.getLevel());
                System.out.println("lol");
                player.getInventar().add(loot);
                player.setLoot(loot);
                player.setLootVisible(true);
            }
            mob1.setAlive(false);
            player.increaseXP(mob1.getXP());
            player.getCurrency().mobdrop(mob1.getLevel());
        }
    }

    /**
     * This method sets a mob to a placeholder (that is outside the map) if it
     * gets flagged as dead by the method above
     */
    public void deadMobs() {
        NPC toterMob = new NPC(new Coordinates(-1000, -1000), 0, 0, 1, "placeholder", "Mob placeholder", 0);
        if (!mob1.getAlive()) {
            mob1 = toterMob;
        }
    }

    /**
     * This method will make the Hunter-class shoot an arrow instead of weilding
     * a sword. Once again the direction is decided by taking the player's angle
     */
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

    /**
     * This method will regenerate 1% of the players health every 100 frames
     */
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

    /**
     * This method will check if the player has reached the experience points
     * needed to level up
     */
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

    /**
     * This method will make the mobs run towards the player if they are
     * aggresive Aggression state can currently be changed with the T-key
     */
    private void mobAttack() {
        if (mob1.getAggro()) {
            mob1.moveToPlayer(player);
        }
    }

    /**
     * This will update the players and mobs hitboxs on every frame
     */
    private void hitboxUpdate() {
        player.hitboxUpdate();
        mob1.hitboxUpdate();
    }

    /**
     * This method makes the player equip the weapon, that is currently selected
     * Selected means which index the counter currently is on Then it calls the
     * stats adjustment method to update the player stats
     */
    private void equipWeapon() {
        player.setWeapon(player.getInventar().get(inventoryCounter));
        player.getStats().statAdjustment(player);
    }

    /**
     * This method holds all the methods that have to get called on every frame
     * (also known as tick) This method gets called every 20ms by the
     * ActionListener / timer defined higher up
     */
    private void doOnTick() {

        hitboxUpdate();
        youShallNotPass();
        checkXP();
        player.levelUpAnimationFunction(player);
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
//        mob1.getAttackHitbox().weaponFramesFunction(mob1);

        repaint();
    }

    //The drawing methods begin here
    /**
     * This method draws the current stats of the player you can see by pressing
     * the c-Key
     *
     * @param g graphics
     */
    public void characterStats(Graphics g) {
        if (player.getStats().getVisible()) {
            stats.paintIcon(null, g, 50, 150);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("Player stats", 210, 193);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(new Color(255, 220, 70));
            String[] statNames = new String[]{"Name:", "Class:", "Level:", "Strength:", "Dexterity:", "Stamina:", "Defence:", "Crit Chance:"};
            int x = 75;
            int y = 265;
            for (String statName : statNames) {
                g.drawString(statName, x, y);
                y += 25;
            }
            g.setColor(Color.WHITE);
            String[] statNumbers = new String[]{player.getName(), player.getCharacterClass(), "" + player.getLevel(), "" + (int) player.getStats().getAttack(), "" + (int) player.getStats().getDexterity(), "" + (int) player.getStats().getStamina(), "" + (int) player.getStats().getDefence(), "" + player.getStats().getCritChance() + "%"};
            x = 265;
            y = 265;
            for (String statNumber : statNumbers) {
                g.drawString(statNumber, x, y);
                y += 25;
            }

            g.setColor(new Color(255, 220, 70));
            g.drawString("Equipped weapon:", 75, 490);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            x = 75;
            y = 540;
            g.setColor(Color.WHITE);
            g.drawString("" + player.getWeapon().getName(), x, y);
            g.setColor(Color.WHITE);        //There is a reason for it being set to white twice here. ("placeholder" for a feature later)
            statNames = new String[]{"Damage:", "Strength:", "Dexterity:", "Stamina:", "Defence:"};
            for (String statName : statNames) {
                y += 25;
                g.drawString(statName, x, y);
            }
            x = 200;
            y = 565;
            statNumbers = new String[]{"" + player.getWeapon().getDamage(), "+" + (int) player.getWeapon().getStats().getAttack(), "+" + (int) player.getWeapon().getStats().getDexterity(), "+" + (int) player.getWeapon().getStats().getStamina(), "+" + (int) player.getWeapon().getStats().getDefence()};
            for (String statNumber : statNumbers) {
                g.drawString(statNumber, x, y);
                y += 25;
            }

//            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
//            g.setColor(Color.BLUE);
//            g.drawString(player.getStats().getStatSummary1(), 50, 750);
//            g.drawString(player.getStats().getStatSummary2() + ". Weapon damage: " + (int) player.getWeapon().getDamage(), 50, 775);
//            g.setColor(Color.RED);
//            g.drawString(mob1.getStats().getStatSummary1(), 50, 650);
//            g.drawString(mob1.getStats().getStatSummary2() + ". Weapon damage: " + (int) mob1.getWeapon().getDamage(), 50, 675);
        }
    }

    /**
     * This method draws all the objects like chests etc
     *
     * @param g graphics
     */
    public void drawInteractionObjects(Graphics g) {
        chest1.drawObjects(g);
//        chest2.drawObjects(g);
//        chest3.drawObjects(g);
    }

    /**
     * This method draws all the mobs
     *
     * @param g graphics
     */
    public void drawMobs(Graphics g) {
        mob1.drawObjects(g);
        drawMobHealthbar(g, mob1);
    }

    /**
     * This method draws all the NPCs
     *
     * @param g graphics
     */
    public void drawNPCs(Graphics g) {
        npc1.drawObjects(g);
        npc2.drawObjects(g);
    }

    /**
     * This method draws the attacks (kinda obsolete since atm only the player
     * has an attack animation)
     *
     * @param g graphics
     */
    public void drawAttacks(Graphics g) {
        player.getAttackHitbox().drawObjects(g);
        mob1.getAttackHitbox().drawObjects(g);
    }

    /**
     * This method draws the dialog and dialogbox you see when you interact with
     * a friendly NPC (press J when you are near one)
     *
     * @param g graphics
     */
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

    /**
     * This method draws the healthbar
     *
     * @param g graphics
     */
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
        g.drawString(player.getName(), 40, 22);         // ", " + player.getCharacterClass() +  // + " Level " + player.getLevel()
        g.drawString("" + (int) player.getStats().getHP() + " / " + (int) player.getStats().getMaxHP() + "  " + (int) (currentHPpercent * 100) + "%", 40, 52);

//        //This part is the OLD mob HP bar on top of the screen; left in just for memory's sake
//        g2d.setColor(Color.RED);
//        hpBar = new RoundRectangle2D.Double(400, 30, 250, 30, 3, 3);
//        g2d.fill(hpBar);
//
//        currentHPpercent = (mob1.getStats().getHP() / mob1.getStats().getMaxHP());
//
//        g2d.setColor(Color.GREEN);
//        hpBar = new RoundRectangle2D.Double(400, 30, 250 * currentHPpercent, 30, 3, 3);
//        g2d.fill(hpBar);
//
//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
//        g.setColor(Color.BLACK);
//        g.drawString(mob1.getName() + " Level " + mob1.getLevel(), 410, 22);
//        g.drawString("" + (int) mob1.getStats().getHP() + " / " + (int) mob1.getStats().getMaxHP() + "  " + (int) (currentHPpercent * 100) + "%", 410, 52);
    }

    /**
     * This method draws the mob's healthbars
     *
     * @param g graphics
     */
    public void drawMobHealthbar(Graphics g, NPC mob) {
        //This part is the new mob HP bar (above the individual mobs)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        RoundRectangle2D hpBar = new RoundRectangle2D.Double(mob1.getObjectPosition().getX(), mob1.getObjectPosition().getY() - 15, 75, 7, 3, 3);
        g2d.fill(hpBar);

        double currentHPpercent = (mob1.getStats().getHP() / mob1.getStats().getMaxHP());

        g2d.setColor(Color.GREEN);
        hpBar = new RoundRectangle2D.Double(mob1.getObjectPosition().getX(), mob1.getObjectPosition().getY() - 15, 75 * currentHPpercent, 7, 3, 3);
        g2d.fill(hpBar);

//        g2d.setColor(Color.WHITE);
//        hpBar = new RoundRectangle2D.Double(mob1.getObjectPosition().getX(), mob1.getObjectPosition().getY()-27, 75, 11, 3, 3);
//        g2d.fill(hpBar);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString("Level " + mob1.getLevel(), mob1.getObjectPosition().getX() + 5, mob1.getObjectPosition().getY() - 17);
    }

    /**
     * This method draws the experience bar
     *
     * @param g graphics
     */
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

    /**
     * This method draws the experience information in textform to the character
     * stats (when you press C)
     *
     * @param g graphics
     */
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

    /**
     * This method draws the level up animation
     *
     * @param g graphics
     */
    public void drawLevelUp(Graphics g) {
        if (player.getLevelUpAnimationVisible()) {
            levelUp.paintIcon(null, g, player.getObjectPosition().getX() - 7, player.getObjectPosition().getY() - 20);
        }
    }

    /**
     * This method draws the inventory
     *
     * @param g graphics
     */
    public void drawInventory(Graphics g) {
        int x = 1050;
        if (inventoryVisible) {
            inventory.paintIcon(null, g, 600, 200);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("Inventory", 825, 253);

            //This part is responsible for displaying the money the player owns (silverserpents)
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            for (int i = 1; i < (Integer.toString(player.getCurrency().getSilverserpents())).length(); i++) {
                x -= 18;
            }
            g.drawString("" + player.getCurrency().getSilverserpents(), x, 315);

            //This part is responsible for displaying the weapons in the inventory    
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            x = 650;
            if (inventoryCounter > 0 && player.getInventar().size() != 0) {
                if (player.getInventar().get(inventoryCounter - 1) != null) {
                    g.drawString("" + player.getInventar().get(inventoryCounter - 1).getName(), x, 373);
                    g.drawString("Damage:" + player.getInventar().get(inventoryCounter - 1).getDamage() + " Strength:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getAttack() + " Dexterity:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getDexterity(), x, 398);
                    g.drawString("Stamina:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getStamina() + " Defence:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getDefence(), x, 423);
                }
            }
            if (player.getInventar().size() != 0) {
                if (player.getInventar().get(inventoryCounter) != null) {
                    g.drawString("" + player.getInventar().get(inventoryCounter).getName(), x, 490);
                    g.drawString("Damage:" + player.getInventar().get(inventoryCounter).getDamage() + " Strength:" + (int) player.getInventar().get(inventoryCounter).getStats().getAttack() + " Dexterity:" + (int) player.getInventar().get(inventoryCounter).getStats().getDexterity(), x, 515);
                    g.drawString("Stamina:" + (int) player.getInventar().get(inventoryCounter).getStats().getStamina() + " Defence:" + (int) player.getInventar().get(inventoryCounter).getStats().getDefence(), x, 540);
                }
            }
            if ((inventoryCounter < player.getInventar().size() - 1)) {
                if (player.getInventar().get(inventoryCounter + 1) != null) {
                    g.drawString("" + player.getInventar().get(inventoryCounter + 1).getName(), x, 600);
                    g.drawString("Damage:" + player.getInventar().get(inventoryCounter + 1).getDamage() + " Strength:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getAttack() + " Dexterity:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getDexterity(), x, 625);
                    g.drawString("Stamina:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getStamina() + " Defence:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getDefence(), x, 650);
                }
            }

            //Remains of the "old" 5-slot-inventory, kept just in case I decide to pick that style back up again
//            if ((player.getInventar().size() >= invPosi4)) {
//                if (player.getInventar().get(invPosi4) != null) {
//                    g.setColor(Color.WHITE);
//                    if (inventoryHighlight == 4) {
//                        g.setColor(Color.YELLOW);
//                    }
//                    g.drawString("" + player.getInventar().get(invPosi4).getName(), 650, 555);
//                    g.drawString("Dmg:" + player.getInventar().get(invPosi4).getDamage() + " Atk:" + player.getInventar().get(invPosi4).getDamage() + " Dex:" + player.getInventar().get(invPosi4).getDamage(), 588, 575);
//                    g.drawString("Stam:" + player.getInventar().get(invPosi4).getDamage() + " Def:" + player.getInventar().get(invPosi4).getDamage(), 587, 595);
//                }
//            }
//            if ((player.getInventar().size() >= invPosi5)) {
//                if (player.getInventar().get(invPosi5) != null) {
//                    g.setColor(Color.WHITE);
//                    if (inventoryHighlight == 5) {
//                        g.setColor(Color.YELLOW);
//                    }
//                    g.drawString("" + player.getInventar().get(invPosi5).getName(), 650, 630);
//                    g.drawString("Dmg:" + player.getInventar().get(invPosi5).getDamage() + " Atk:" + player.getInventar().get(invPosi5).getDamage() + " Dex:" + player.getInventar().get(invPosi5).getDamage(), 588, 650);
//                    g.drawString("Stam:" + player.getInventar().get(invPosi5).getDamage() + " Def:" + player.getInventar().get(invPosi5).getDamage(), 587, 670);
//                }
//            }
        }
    }

    private void drawLoot(Graphics g) {
        if (player.getLootVisible()) {
            dialogBox.paintIcon(null, g, 0, (780 - 140));
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(new Color(255, 220, 70));
            g.drawString("Press [ESC] to close", 800, 695);
            g.setColor(Color.WHITE);
            g.drawString("You got loot!", 230, 695);

            g.drawString("Weapon name: " + player.getLoot().getName(), 230, 725);
            g.drawString("Damage:" + player.getLoot().getDamage() + " Strength:" + (int) player.getLoot().getStats().getAttack() + " Dexterity:" + (int) player.getLoot().getStats().getDexterity() + " Stamina:" + (int) player.getLoot().getStats().getStamina() + " Defence:" + (int) player.getLoot().getStats().getDefence(), 230, 750);
        }
    }

    /**
     * This is a collection method that calls all the individual drawing methods
     *
     * @param g graphics
     */
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
//        drawXPstatus(g);
        drawDialog(g);
        drawLevelUp(g);
        drawInventory(g);
        drawLoot(g);

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
