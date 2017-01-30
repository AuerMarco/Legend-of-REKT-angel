package zeldiablo;

/**
 * In the game you will be able to move between different screens. Each screen
 * has its own mobs, chests, NPCs etc. This class is responsible for loading the
 * correct entities for each screen.
 *
 * @author Auer Marco
 */
public class World {

    //Placeholder for data members
    //Place for constructor(s)
    public void theMatrix(Canvas canvas) {
        canvas.setStartscreenFalse();
        canvas.setBackground(0);
//        canvas.setPlayer(new Player(new Coordinates(450, 500), 35, 80, 1, "Knight", "debug", 1, "Broken Sword", 0, 0, 0, 0, 0));  
        canvas.getPlayer().setObjectPosition(new Coordinates(600, 550));
        canvas.getPlayer().setMapID("Zone_Matrix");
        canvas.createGameObjects();
        canvas.setNPC1(new NPC(new Coordinates(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion of the sun", 1, "Solaire, Champion of the sun"));
        canvas.setNPC2(new NPC(new Coordinates(350, 400), 48, 100, 4, "Rogue", "Unknown rogue", 1, "Unknown rogue"));
        canvas.setMob1(new NPC(new Coordinates(100, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob2(new NPC(new Coordinates(200, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob3(new NPC(new Coordinates(300, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob4(new NPC(new Coordinates(400, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob5(new NPC(new Coordinates(500, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob6(new NPC(new Coordinates(600, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob7(new NPC(new Coordinates(700, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob8(new NPC(new Coordinates(800, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob9(new NPC(new Coordinates(900, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setMob10(new NPC(new Coordinates(1000, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setChest1(new InteractionObject(new Coordinates(600, 400), 37, 35, "Chest1", "StartWeapon"));
    }

    public void theBeginning(Canvas canvas) {
        canvas.setStartscreenFalse();
        canvas.getPlayer().setObjectPosition(new Coordinates(500, 550));
        canvas.getPlayer().setMapID("Zone_Start");
        canvas.createGameObjects();
        canvas.setNPC1(new NPC(new Coordinates(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion of the sun", 1, "Intro"));
        canvas.setNPC2(new NPC(new Coordinates(300, 150), 35, 80, 1, "Knight", "Unknown Knight", 1, "KnightIntro"));
        canvas.setNPC3(new NPC(new Coordinates(500, 125), 52, 102, 1, "Berserker", "Unknown Berserker", 1, "BerserkerIntro"));
        canvas.setNPC4(new NPC(new Coordinates(700, 150), 33, 75, 1, "Hunter", "Unknown Hunter", 1, "HunterIntro"));
    }

    public void chestIntro(Canvas canvas) {
        canvas.getPlayer().setObjectPosition(new Coordinates(500, 550));
        canvas.getPlayer().setMapID("Zone_ChestIntro");
        canvas.createGameObjects();
        canvas.setNPC1(new NPC(new Coordinates(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion of the sun", 1, "ChestIntro"));
        canvas.setChest1(new InteractionObject(new Coordinates(600, 400), 37, 35, "Chest1", "StartWeapon"));
    }

    public void town(Canvas canvas) {
        canvas.setStartscreenFalse();
        canvas.getPlayer().setTutorialComplete(true);
        canvas.getPlayer().getNPCdialog().setDialogSpecial(false);
        canvas.setBackground(1);
        canvas.getPlayer().setMapID("Zone_Town");
        canvas.createGameObjects();
        canvas.getPlayer().setObjectPosition(new Coordinates(600, 550));
    }
}
