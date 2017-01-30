package zeldiablo;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * This class saves the players state in a file.
 * It allows to save and load all the player data like the level etc.
 *
 * @author Auer Marco
 * @author mkyong.com
 */
public class SaveFile implements Serializable {

    /**
     * This method useds File and Object OutputStreams to save
     * Object-data (in this case player-data) into a Serializiable file (.ser).     * 
     * 
     * @param player The player whose data gets saved
     */
    public void createSaveFile(Player player) {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = new FileOutputStream("c:\\temp\\player.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(player);

            System.out.println("Game saved.");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads data from a Serializiable file (.ser) and
     * puts it into a temporary player-object. 
     * Then it calls the method that puts this data into the actual player.
     * See below for more information.
     * 
     * @param filename The path where the file is
     * @return the player-data in a new, temporary Player-object. 
     */
    public Player loadSaveFile(String filename) {

        Player player = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {
            fin = new FileInputStream(filename);
            ois = new ObjectInputStream(fin);
            player = (Player) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }
    
    /**
     * Takes the actual player (that you control) and a player-object 
     * that contains all the data and sets all the important variables
     * to the stat that is supposed to be loaded by calling all the 
     * individual set-methods. 
     * 
     * @param player The player-object that you actually control
     * @param loadState The object that holds the data from the .ser file
     *
     */
    public void loadPlayer(Player player, Player loadState) {
        player.setCurrency(loadState.getCurrency());
        player.setInventar(loadState.getInventar());
        player.setWeapon(loadState.getWeapon());
        player.setName(loadState.getName());
        player.setCharacterClass(loadState.getCharacterClass());
        player.setLevel(loadState.getLevel());      
        player.setXP(loadState.getXP());
        player.setStartWeaponChest(loadState.getStartWeaponChest());
        player.setTutorialComplete(true);
//        player.setObjectPosition(loadState.getObjectPosition());
    }
}
