package zeldiablo;

/**
 * In the game you will be able to move between different screens.
 * Each screen has its own mobs, chests, NPCs etc.
 * This class is responsible for loading the correct entities for each screen.
 *
 * @author Auer Marco
 */
public class World {
    
    //Placeholder for data members
    
    //Place for constructor(s)
        
    public void theMatrix(Canvas canvas) {
        canvas.setPlayer(new Player(new Coordinates(450, 500), 35, 80, 1, "Knight", "debug", 1, "Broken Sword", 0, 0, 0, 0, 0));        
        canvas.setNPC1( new NPC(new Coordinates(500, 400), 48, 100, 1, "Solaire", "Solaire, Champion of the sun", 1));
        canvas.setNPC2(new NPC(new Coordinates(350, 400), 48, 100, 4, "Rogue", "Unknown rogue", 1));
        canvas.setMob1(new NPC(new Coordinates(460, 300), 80, 70, 1, "Mob", "Orc", 1));
        canvas.setChest1(new InteractionObject(new Coordinates(600, 400), 37, 35, "Chest1", "StartWeapon"));
    }
}
