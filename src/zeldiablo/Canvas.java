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
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JTextField;

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
    private int gameoverCounter, hpTimer;

    private Timer t;
    private NPC placeholder;
    private NPC npc1, npc2, npc3, npc4, npc5;
    private NPC[] npcs;
    private NPC mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10;
    private NPC[] mobs;
    private InteractionObject chest1, chest2, chest3;
    private InteractionObject[] chests;
    private int arrowCounter;
    private boolean arrowLock;
    public boolean wKey, aKey, sKey, dKey;

    private boolean angle1 = false, angle3 = false, angle5 = false, angle7 = false;

    private boolean inventoryVisible;
    private int inventoryHighlight, inventoryCounter;
    private int color1, color2, color3;
    private int invPosi1 = inventoryCounter, invPosi2 = 1, invPosi3 = 2, invPosi4 = 3, invPosi5 = 4;

    private boolean startScreen;
    private int startscreenCounter;
    World world;
    boolean spawned;
    InteractionObject tele1, tele2, tele3, tele4;

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
        bgPictureList = new String[]{"bg_matrix.jpg", "bg_town.jpg", "bg_arena.jpg", "bg_area1.jpg", "bg_smith.jpg", "bg_trader.jpg"};
        spriteList = new String[]{"dialogbox.png", "LevelUp.png", "inventory.png", "stats.png"};
        gameOver = false;
        gameoverCounter = 0;
        wKey = false;
        aKey = false;
        sKey = false;
        dKey = false;
        arrowLock = false;
        inventoryHighlight = 1;
        inventoryCounter = 0;
        world = new World();
        spawned = false;

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

    public NPC getNPC1() {
        return npc1;
    }

    /**
     * This method will change the currently present npc1 to a new, as every
     * areal (part of the world) has different NPCs and mobs
     *
     * @param npc NPC that the current npc1 will get changed to
     */
    public void setNPC1(NPC npc) {
        npc1 = npc;
    }

    public NPC getNPC2() {
        return npc2;
    }

    public void setNPC2(NPC npc) {
        npc2 = npc;
    }

    public NPC getNPC3() {
        return npc3;
    }

    public void setNPC3(NPC npc) {
        npc3 = npc;
    }

    public NPC getNPC4() {
        return npc4;
    }

    public void setNPC4(NPC npc) {
        npc4 = npc;
    }

    public NPC getNPC5() {
        return npc5;
    }

    public void setNPC5(NPC npc) {
        npc5 = npc;
    }

    public NPC getMob1() {
        return mob1;
    }

    public void setMob1(NPC mob) {
        mob1 = mob;
    }

    public NPC getMob2() {
        return mob2;
    }

    public void setMob2(NPC mob) {
        mob2 = mob;
    }

    public NPC getMob3() {
        return mob3;
    }

    public void setMob3(NPC mob) {
        mob3 = mob;
    }

    public NPC getMob4() {
        return mob4;
    }

    public void setMob4(NPC mob) {
        mob4 = mob;
    }

    public NPC getMob5() {
        return mob5;
    }

    public void setMob5(NPC mob) {
        mob5 = mob;
    }

    public NPC getMob6() {
        return mob6;
    }

    public void setMob6(NPC mob) {
        mob6 = mob;
    }

    public NPC getMob7() {
        return mob7;
    }

    public void setMob7(NPC mob) {
        mob7 = mob;
    }

    public NPC getMob8() {
        return mob8;
    }

    public void setMob8(NPC mob) {
        mob8 = mob;
    }

    public NPC getMob9() {
        return mob9;
    }

    public void setMob9(NPC mob) {
        mob9 = mob;
    }

    public NPC getMob10() {
        return mob10;
    }

    public void setMob10(NPC mob) {
        mob10 = mob;
    }

    public InteractionObject getChest1() {
        return chest1;
    }

    public void setChest1(InteractionObject chest) {
        chest1 = chest;
    }

    public InteractionObject getChest2() {
        return chest2;
    }

    public void setChest2(InteractionObject chest) {
        chest2 = chest;
    }

    public InteractionObject getChest3() {
        return chest3;
    }

    public void setChest3(InteractionObject chest) {
        chest3 = chest;
    }

    public void setStartscreenFalse() {
        startScreen = false;
    }

    public void setTeleporter1(InteractionObject tele) {
        tele1 = tele;
    }

    public void setTeleporter2(InteractionObject tele) {
        tele2 = tele;
    }

    public void setTeleporter3(InteractionObject tele) {
        tele3 = tele;
    }

    public void setTeleporter4(InteractionObject tele) {
        tele4 = tele;
    }

    /**
     * This method starts the game up by setting the background (or rather,
     * calling the method that does it) and then calling the method that creates
     * the game objects like NPCs and objects like chests It also contains the
     * very, very important KeyListener that allows to control and play the
     * game!
     */
    private void initGame() {
        setBackground(0);
        startScreen = true;
        player = new Player(new Coordinates(-2000, -2000), 200, 80, 1, "REKTangel", "Kyle", 1, "Broken Sword", 0, 0, 0, 0, 0);          //Parameters: coordinates, width, height, angle, class, name / ID, level, and 5 parameters for weapon-creation
        player.getWeapon().setQuality("Poor");
        player.getWeapon().setFlavortext("\"You should get rid off it fast\"");
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
                        if (!startScreen) {
                            if (inventoryVisible) {
                                if (player.getInventar().size() != 0) {
                                    equipWeapon();
                                }
                            }
                            if (!inventoryVisible) {
                                player.objectInteraction(player, chest1);
                                npcs = new NPC[]{npc1, npc2, npc3, npc4, npc5};
                                for (NPC npc : npcs) {
                                    player.objectInteraction(player, npc);
                                }

//                                player.objectInteraction(player, npc2);
                            }
                            if (player.getStartWeaponChest() && player.getNPCdialog().getDialogVisible() && player.getMapID().equalsIgnoreCase("Zone_ChestIntro") && player.getWeapon().getName().equalsIgnoreCase("Wooden Training Sword")) {
                                player.getNPCdialog().dialogLogicSpecial();
                            } else if (player.getNPCdialog().getDialogVisible()) {
                                player.getNPCdialog().dialogLogic();
                            }
                        }
                        if (startScreen) {
                            startMenu();
                        }
                        break;
                    case VK_K:
                        if (!startScreen) {
                            if (player.getAttackCD() <= 0) {
                                player.setAttackCD(20);
                                if (!player.getCharacterClass().equals("Hunter")) {      //player.getCharacterClass() == "Knight" || player.getCharacterClass() == "Berserker"
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
                        }

                        break;
                    case VK_ENTER:
//                        System.out.println("Equiped weapon: Damage: " + player.getWeapon().getDamage() + " Str:" + (int) player.getWeapon().getStats().getAttack() + " Dex:" + (int) player.getWeapon().getStats().getDexterity() + " Stam:" + (int) player.getWeapon().getStats().getStamina() + " Def:" + (int) player.getWeapon().getStats().getDefence());
//                        int posi = 1;
//                        for (Weapon weapon : player.getInventar()) {
//                            System.out.println("Position: " + posi + ", " + weapon.getDamage());
//                            posi++;
//                        }
//                        changeScreen();
                        System.out.println(""+player.getObjectPosition().getX()+", "+player.getObjectPosition().getY());
                        break;
                    case VK_C:
                        if (!startScreen) {
                            if (!player.getNPCdialog().getDialogVisible()) {
                                if (!player.getStats().getVisible()) {
                                    player.getStats().setVisible(true);
                                } else if (player.getStats().getVisible()) {
                                    player.getStats().setVisible(false);
                                }
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
                        if (!startScreen) {
                            int n = 0;
                            if (player.getLevel() == 1) {
                                n = 1;
                            } else {
                                n = player.getLevel() - 1;
                            }
                            setMob1(new NPC(new Coordinates(460, 200), 80, 70, 1, "Mob", "Orc", n));
                        }
                        break;
                    case VK_T:
                        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
                        for (NPC mob : mobs) {
                            if (!mob.getAggro()) {
                                mob.setAggro(true);
                            } else if (mob.getAggro()) {
                                mob.setAggro(false);
                            }
                        }
                        break;
                    case VK_I:
                        if (!startScreen) {
                            inventoryHighlight = 1;
                            inventoryCounter = 0;
                            if (!player.getNPCdialog().getDialogVisible()) {
                                if (inventoryVisible) {
                                    inventoryVisible = false;
                                } else {
                                    inventoryVisible = true;
                                }
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
                        if (startscreenCounter >= 1) {
                            startscreenCounter--;
                        }
//                        System.out.println(startscreenCounter);
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
                        if (startscreenCounter < 1) {
                            startscreenCounter++;
                        }
//                        System.out.println(startscreenCounter);
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
                        if (player.getMapID().equalsIgnoreCase("Zone_Start")) {
                            changeScreen();
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
                    case VK_1:
                        if (player.getMapID().equalsIgnoreCase("Zone_Start")) {
                            player.setCharacterClass("Knight");
                            changeScreen();
                        }
                        break;
                    case VK_2:
                        if (player.getMapID().equalsIgnoreCase("Zone_Start")) {
                            player.setCharacterClass("Berserker");
                            changeScreen();
                        }
                        break;
                    case VK_3:
                        if (player.getMapID().equalsIgnoreCase("Zone_Start")) {
                            player.setCharacterClass("Hunter");
                            changeScreen();
                        }
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
    public void createGameObjects() {                                          //Game Objects (placeholders) are created here       

        placeholder = new NPC(new Coordinates(-1000, -1000), 0, 0, 1, "placeholder", "Mob placeholder", 0);
        //The array doesn't work here for some reason
        npc1 = placeholder;
        npc2 = placeholder;
        npc3 = placeholder;
        npc4 = placeholder;
        npc5 = placeholder;
        mob1 = placeholder;
        mob2 = placeholder;
        mob3 = placeholder;
        mob4 = placeholder;
        mob5 = placeholder;
        mob6 = placeholder;
        mob7 = placeholder;
        mob8 = placeholder;
        mob9 = placeholder;
        mob10 = placeholder;
        InteractionObject placeholderChest = new InteractionObject(new Coordinates(-1000, -1000), 0, 0, "Chest1", "Null");
        chest1 = placeholderChest;
        chest2 = placeholderChest;
        chest3 = placeholderChest;
        InteractionObject telePlaceholder = new InteractionObject(new Coordinates(-1000, -1000), 0, 0, "nothing", "Null");
        tele1 = telePlaceholder;
        tele2 = telePlaceholder;
        tele3 = telePlaceholder;
        tele4 = telePlaceholder;
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
        gameoverCounter = 0;
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

        npcs = new NPC[]{npc1, npc2, npc3, npc4, npc5};
        for (NPC npc : npcs) {
            player.youShallNotPass(player, npc);
        }
    }

    /**
     * Collection method that checks if a player or mob is hit One method call
     * for each npc / player combination
     */
    public void hitDetect() {
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            if (mob.getInviFrames() <= 0) {
                player.getAttackHitbox().hitDetect(player, mob);
            }
            if (player.getInviFrames() <= 0) {
                mob.getAttackHitbox().hitDetect(mob, player);
            }
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
//        if (mob1.getAttackCD() > 0) {
//            mob1.setAttackCD(mob1.getAttackCD() - 1);
//        }

        if (player.getInviFrames() > 0) {
            player.setInviFrames(player.getInviFrames() - 1);
        }
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            if (mob.getInviFrames() > 0) {
                mob.setInviFrames(mob.getInviFrames() - 1);
            }
        }
    }

    /**
     * This method checks if a player or mob has 0 health and performs an
     * according action
     */
    public void checkHP() {
        if (player.getStats().getHP() <= 0) {
            player.getStats().setHP(player.getStats().getMaxHP());
            gameOver = true;
            world.town(this);
            player.getCurrency().setValue(player.getCurrency().getSilverserpents() - (player.getCurrency().getSilverserpents() * 10 / 100));
//            t.stop();
        }

        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            if (mob.getStats().getHP() <= 0) {
                Chance chance = new Chance(10);
                if (chance.getSuccess() && mob.getLevel() != 0) {
                    Weapon loot = new Weapon(mob.getLevel());
                    player.getInventar().add(loot);
                    player.setLoot(loot);
                    player.setLootVisible(true);
                }
                mob.setAlive(false);
                player.increaseXP(mob.getXP());
                player.getCurrency().mobdrop(mob.getLevel());
            }
        }

        if (player.getStartWeaponChest() && spawned && mob1.getStats().getHP() <= 0 && player.getMapID().equalsIgnoreCase("Zone_ChestIntro")) {
            world.town(this);
            Chance chance = new Chance(10);
            if (chance.getSuccess() && mob1.getLevel() != 0) {
                Weapon loot = new Weapon(mob1.getLevel());
                player.getInventar().add(loot);
                player.setLoot(loot);
                player.setLootVisible(true);
            }
            player.increaseXP(mob1.getXP());
            player.getCurrency().mobdrop(mob1.getLevel());
        }
    }

    /**
     * This method sets a mob to a placeholder (that is outside the map) if it
     * gets flagged as dead by the method above
     */
    public void deadMobs() {
        //The array doesn't work here for some reason
        if (!mob1.getAlive()) {
            mob1 = placeholder;
        }
        if (!mob2.getAlive()) {
            mob2 = placeholder;
        }
        if (!mob3.getAlive()) {
            mob3 = placeholder;
        }
        if (!mob4.getAlive()) {
            mob4 = placeholder;
        }
        if (!mob5.getAlive()) {
            mob5 = placeholder;
        }
        if (!mob6.getAlive()) {
            mob6 = placeholder;
        }
        if (!mob7.getAlive()) {
            mob7 = placeholder;
        }
        if (!mob8.getAlive()) {
            mob8 = placeholder;
        }
        if (!mob9.getAlive()) {
            mob9 = placeholder;
        }
        if (!mob10.getAlive()) {
            mob10 = placeholder;
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
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            if (mob.getAggro()) {
                mob.moveToPlayer(player);
            }
        }
    }

    /**
     * This will update the players and mobs hitboxs on every frame
     */
    private void hitboxUpdate() {
        player.hitboxUpdate();
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            mob.hitboxUpdate();
        }
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

    private void changeScreen() {
        if (player.getMapID().equalsIgnoreCase("Zone_Start") && player.getCharacterClass() != "REKTangel") {
            world.chestIntro(this);
        } else {
            world.theMatrix(this);
        }
    }

    private void startMenu() {
        if (startscreenCounter == 0) {
            System.out.println("Loaded game");
//            world.theMatrix(this);
            SaveFile file = new SaveFile();
            Player playerLoader = file.loadSaveFile("c:\\temp\\player.ser");
//            player = new Player(new Coordinates(450, 500), 35, 80, 1, playerLoader.getCharacterClass(), "Unnamed", 1, "Broken Sword", 0, 0, 0, 0, 0);                    
            file.loadPlayer(player, playerLoader);
            world.town(this);
        } else if (startscreenCounter == 1) {
            System.out.println("New game started");
//            System.out.println("Please enter your name:");
//            Scanner scanner = new Scanner(System.in);
//            String name = scanner.nextLine();
//            player.setName(name);
            world.theBeginning(this);
        }

    }

    private void chestSpawner() {
        if (!spawned && player.getNPCdialog().getDialogSpecial() && player.getWeapon().getName().equalsIgnoreCase("Wooden Training Sword") && player.getMapID().equalsIgnoreCase("Zone_ChestIntro")) {
            mob1 = new NPC(new Coordinates(500, 250), 80, 70, 1, "Mob", "Orc", 1, "Broken Sword", 0, 0, 0, 0, 0);
            mob1.setAggro(false);
            spawned = true;
        }
    }

    public void removeGameover() {
        if (gameOver) {
            gameoverCounter++;
            if (gameoverCounter >= 50) {
                gameOver = false;
            }
        }
    }

    public void teleporterMethod() {
        if (player.touches(tele1)) {
//            System.out.println("tele1");
            if (player.getMapID().equals("Zone_Town")) {
                world.area1(this);
            } else if (player.getMapID().equals("Zone_Area1")) {
                world.town(this);
                player.setObjectPosition(new Coordinates(1060, 362));
            } else if (player.getMapID().equals("Zone_Smith")) {
                world.town(this);
                player.setObjectPosition(new Coordinates(856, 362));
            }
        }
        if (player.touches(tele2)) {
            if (player.getMapID().equals("Zone_Town")) {
                world.smith(this);
            }
        }
    }

    public void antiAggro() {
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            if (mob.getLevel() == 0) {
                mob.setAggro(false);
            }
        }
    }

    public void weaponColor(Weapon weapon) {
        switch (weapon.getQuality()) {
            case "Poor":
                color1 = 157;
                color2 = 157;
                color3 = 157;
                break;
            case "Common":
                color1 = 255;
                color2 = 255;
                color3 = 255;
                break;
            case "Uncommon":
                color1 = 30;
                color2 = 255;
                color3 = 0;
                break;
            case "Rare":
                color1 = 0;
                color2 = 112;
                color3 = 255;
                break;
            case "Epic":
                color1 = 163;
                color2 = 53;
                color3 = 238;
                break;
            case "Legendary":
                color1 = 255;
                color2 = 128;
                color3 = 0;
                break;
            case "Artifact":
                color1 = 230;
                color2 = 204;
                color3 = 128;
                break;
            default:
                break;
        }
    }

    /**
     * This method holds all the methods that have to get called on every frame
     * (also known as tick) This method gets called every 20ms by the
     * ActionListener / timer defined higher up
     */
    private void doOnTick() {

        antiAggro();
        hitboxUpdate();
        teleporterMethod();
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
        chestSpawner();
        removeGameover();
        arrow();

        if (player.getNPCdialog().getDialogVisible()) {
            player.setSpeed(0);
        } else {
            player.setSpeed(player.getOriginalSpeed());
        }

        if (player.getCharacterClass() != "REKTangel") {
            player.walkingAnimation();
        }
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
            weaponColor(player.getWeapon());
            g.setColor(new Color(color1, color2, color3));
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
            g.setColor(new Color(229, 206, 102));
            g.drawString(player.getWeapon().getFlavortext(), 75, y+25);

//            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
//            g.weaponColor(Color.BLUE);
//            g.drawString(player.getStats().getStatSummary1(), 50, 750);
//            g.drawString(player.getStats().getStatSummary2() + ". Weapon damage: " + (int) player.getWeapon().getDamage(), 50, 775);
//            g.weaponColor(Color.RED);
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
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mob : mobs) {
            mob.drawObjects(g);
            drawMobHealthbar(g, mob);
        }
//        mob1.drawObjects(g);
//        drawMobHealthbar(g, mob1);
//        mob2.drawObjects(g);
//        drawMobHealthbar(g, mob2);
    }

    /**
     * This method draws all the NPCs
     *
     * @param g graphics
     */
    public void drawNPCs(Graphics g) {
        npcs = new NPC[]{npc1, npc2, npc3, npc4, npc5};
        for (NPC npc : npcs) {
            npc.drawObjects(g);
        }
//        npc2.drawObjects(g);
    }

    /**
     * This method draws the attacks (kinda obsolete since atm only the player
     * has an attack animation)
     *
     * @param g graphics
     */
    public void drawAttacks(Graphics g) {
        player.getAttackHitbox().drawObjects(g);
//        mob1.getAttackHitbox().drawObjects(g);
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
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            RoundRectangle2D hpBar = new RoundRectangle2D.Double(27, 658, 164, 122, 3, 3);
            g2d.fill(hpBar);

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
        if (!startScreen) {
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
            if (player.getTutorialComplete()) {
                g.setColor(Color.WHITE);
            }
            g.drawString(player.getName(), 40, 22);         // ", " + player.getCharacterClass() +  // + " Level " + player.getLevel()
            g.drawString("" + (int) player.getStats().getHP() + " / " + (int) player.getStats().getMaxHP() + "  " + (int) (currentHPpercent * 100) + "%", 40, 52);
        }
//        //This part is the OLD mob HP bar on top of the screen; left in just for memory's sake
//        g2d.weaponColor(Color.RED);
//        hpBar = new RoundRectangle2D.Double(400, 30, 250, 30, 3, 3);
//        g2d.fill(hpBar);
//
//        currentHPpercent = (mob1.getStats().getHP() / mob1.getStats().getMaxHP());
//
//        g2d.weaponColor(Color.GREEN);
//        hpBar = new RoundRectangle2D.Double(400, 30, 250 * currentHPpercent, 30, 3, 3);
//        g2d.fill(hpBar);
//
//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
//        g.weaponColor(Color.BLACK);
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
        mobs = new NPC[]{mob1, mob2, mob3, mob4, mob5, mob6, mob7, mob8, mob9, mob10};
        for (NPC mobObj : mobs) {
            g2d.setColor(Color.RED);
            RoundRectangle2D hpBar = new RoundRectangle2D.Double(mobObj.getObjectPosition().getX(), mobObj.getObjectPosition().getY() - 15, 75, 7, 3, 3);
            g2d.fill(hpBar);

            double currentHPpercent = (mobObj.getStats().getHP() / mobObj.getStats().getMaxHP());

            g2d.setColor(Color.GREEN);
            hpBar = new RoundRectangle2D.Double(mobObj.getObjectPosition().getX(), mobObj.getObjectPosition().getY() - 15, 75 * currentHPpercent, 7, 3, 3);
            g2d.fill(hpBar);

//        g2d.weaponColor(Color.WHITE);
//        hpBar = new RoundRectangle2D.Double(mob1.getObjectPosition().getX(), mob1.getObjectPosition().getY()-27, 75, 11, 3, 3);
//        g2d.fill(hpBar);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            g.setColor(Color.BLACK);
            g.drawString("Level " + mobObj.getLevel(), mobObj.getObjectPosition().getX() + 5, mobObj.getObjectPosition().getY() - 17);
        }
    }

    /**
     * This method draws the experience bar
     *
     * @param g graphics
     */
    public void drawXPbar(Graphics g) {
        //This part draws the players XP bar (how much experience he has and needs to level up)
        if (!startScreen) {
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
        }
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
                    weaponColor(player.getInventar().get(inventoryCounter - 1));
                    g.setColor(new Color(color1, color2, color3));
                    g.drawString("" + player.getInventar().get(inventoryCounter - 1).getName(), x, 373);
                    g.setColor(Color.WHITE);
                    g.drawString("Damage:" + player.getInventar().get(inventoryCounter - 1).getDamage() + " Strength:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getAttack() + " Dexterity:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getDexterity(), x, 398);
                    g.drawString("Stamina:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getStamina() + " Defence:" + (int) player.getInventar().get(inventoryCounter - 1).getStats().getDefence(), x, 423);
                }
            }
            if (player.getInventar().size() != 0) {
                if (player.getInventar().get(inventoryCounter) != null) {
                    weaponColor(player.getInventar().get(inventoryCounter));
                    g.setColor(new Color(color1, color2, color3));
                    g.drawString("" + player.getInventar().get(inventoryCounter).getName(), x, 490);
                    g.setColor(Color.WHITE);
                    g.drawString("Damage:" + player.getInventar().get(inventoryCounter).getDamage() + " Strength:" + (int) player.getInventar().get(inventoryCounter).getStats().getAttack() + " Dexterity:" + (int) player.getInventar().get(inventoryCounter).getStats().getDexterity(), x, 515);
                    g.drawString("Stamina:" + (int) player.getInventar().get(inventoryCounter).getStats().getStamina() + " Defence:" + (int) player.getInventar().get(inventoryCounter).getStats().getDefence(), x, 540);
                }
            }
            if ((inventoryCounter < player.getInventar().size() - 1)) {
                if (player.getInventar().get(inventoryCounter + 1) != null) {
                    weaponColor(player.getInventar().get(inventoryCounter + 1));
                    g.setColor(new Color(color1, color2, color3));
                    g.drawString("" + player.getInventar().get(inventoryCounter + 1).getName(), x, 600);
                    g.setColor(Color.WHITE);
                    g.drawString("Damage:" + player.getInventar().get(inventoryCounter + 1).getDamage() + " Strength:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getAttack() + " Dexterity:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getDexterity(), x, 625);
                    g.drawString("Stamina:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getStamina() + " Defence:" + (int) player.getInventar().get(inventoryCounter + 1).getStats().getDefence(), x, 650);
                }
            }

            //Remains of the "old" 5-slot-inventory, kept just in case I decide to pick that style back up again
//            if ((player.getInventar().size() >= invPosi4)) {
//                if (player.getInventar().get(invPosi4) != null) {
//                    g.weaponColor(Color.WHITE);
//                    if (inventoryHighlight == 4) {
//                        g.weaponColor(Color.YELLOW);
//                    }
//                    g.drawString("" + player.getInventar().get(invPosi4).getName(), 650, 555);
//                    g.drawString("Dmg:" + player.getInventar().get(invPosi4).getDamage() + " Atk:" + player.getInventar().get(invPosi4).getDamage() + " Dex:" + player.getInventar().get(invPosi4).getDamage(), 588, 575);
//                    g.drawString("Stam:" + player.getInventar().get(invPosi4).getDamage() + " Def:" + player.getInventar().get(invPosi4).getDamage(), 587, 595);
//                }
//            }
//            if ((player.getInventar().size() >= invPosi5)) {
//                if (player.getInventar().get(invPosi5) != null) {
//                    g.weaponColor(Color.WHITE);
//                    if (inventoryHighlight == 5) {
//                        g.weaponColor(Color.YELLOW);
//                    }
//                    g.drawString("" + player.getInventar().get(invPosi5).getName(), 650, 630);
//                    g.drawString("Dmg:" + player.getInventar().get(invPosi5).getDamage() + " Atk:" + player.getInventar().get(invPosi5).getDamage() + " Dex:" + player.getInventar().get(invPosi5).getDamage(), 588, 650);
//                    g.drawString("Stam:" + player.getInventar().get(invPosi5).getDamage() + " Def:" + player.getInventar().get(invPosi5).getDamage(), 587, 670);
//                }
//            }
        }
    }

    /**
     * Draws a dialog box that shows you what weapon you got
     *
     * @param g graphics
     */
    private void drawLoot(Graphics g) {
        if (player.getLootVisible()) {
            dialogBox.paintIcon(null, g, 0, (780 - 140));
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.setColor(new Color(255, 220, 70));
            g.drawString("Press [ESC] to close", 800, 695);
            g.drawString("You got loot!", 230, 695);            
            g.setColor(Color.WHITE);
            g.drawString("Weapon name: ", 230, 725);
            weaponColor(player.getLoot());
            g.setColor(new Color(color1, color2, color3));
            g.drawString(player.getLoot().getName(), 415, 725);
            g.setColor(Color.WHITE);
            g.drawString("Damage:" + player.getLoot().getDamage() + " Strength:" + (int) player.getLoot().getStats().getAttack() + " Dexterity:" + (int) player.getLoot().getStats().getDexterity() + " Stamina:" + (int) player.getLoot().getStats().getStamina() + " Defence:" + (int) player.getLoot().getStats().getDefence(), 230, 750);
        }
    }

    public void drawStartMenu(Graphics g) {
        if (startScreen) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
            g.setColor(new Color(255, 220, 70));
            g.drawString("Legend of REKT angel", 325, 300);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            g.setColor(Color.BLACK);
            if (startscreenCounter == 0) {
                g.setColor(new Color(255, 220, 70));
            }
            g.drawString("Load game", 400, 350);
            g.setColor(Color.BLACK);
            if (startscreenCounter == 1) {
                g.setColor(new Color(255, 220, 70));
            }
            g.drawString("New game", 400, 400);

            g.setColor(Color.BLACK);
            g.drawString("[J] to choose option", 325, 450);
            g.drawString("[Arrow Down] to go one option lower", 325, 475);
            g.drawString("[Arrow Up] to go one option higher", 325, 500);
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
//        g.weaponColor(Color.BLACK);
//        g.drawString("Frames: " + gameoverCounter, 22, size.height - 5);        //Parameter: Text der dasteht | x-Position | y-Position
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
        drawStartMenu(g);

        tele1.drawObjects(g);
        tele2.drawObjects(g);
        tele3.drawObjects(g);
        tele4.drawObjects(g);

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
