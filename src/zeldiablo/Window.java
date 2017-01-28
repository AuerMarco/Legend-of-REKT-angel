package zeldiablo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class is responsible for the border around the canvas. e.g. the X to close the window
 * 
 * @author Auer Marco
 */
public class Window extends JFrame {

    private final Canvas world;

    /**
     * creates the Canvas on which everything plays / gets drawn on
     */
    public Window() {
        this.world = new Canvas();                        //A new panel (canvas) is created

        registerWindowListener();                   //Calls the method that creates the WindowListener (that for example reacts to the X button being pressed)
        createMenu();                               //Calls the method that creates the windows
        add(world);                                 //The canvas (world) gets added to the window
        pack();                                     //Creates a window (border) fitting to the panel (canvas) size

        setTitle("The Legend of REKT Angel v2.4.1 - by Auer & Wedenig");    //Game (window) title
        setLocation(400, 100);                      //The start position of the window
        setResizable(false);                        //Makes the size fixed. I read that it can otherwise lead to problems
        setVisible(true);                           //Makes the window visible

    }
    
    /**
     * Makes the program react to things like closing the window or using the menu bar
     */
    private void registerWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);                     //Properly ends the software when you press the X button / close the window
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                world.pauseGame();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                world.continueGame();
            }
        });
    }

    /**
     * creates a menubar and options to use there
     */
    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();      //A new bar is created
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");      //New category (dropdown) is created
        JMenu charaMenu = new JMenu("Character");
        JMenu mobMenu = new JMenu("Mob");

        menuBar.add(fileMenu);                //Category gets added to the bar 
        menuBar.add(charaMenu);
        menuBar.add(mobMenu);

        fileCategoryOptions(fileMenu);               //Method that adds options to the dropdown gets called
        charaCategoryOptions(charaMenu);
        mobCategoryOptions(mobMenu);
    }

    /**
     * Creates the options for a certain category (drop down) 
     * 
     * @param menu the "category" an option gets added to
     */
    private void fileCategoryOptions(JMenu menu) {

        JMenuItem item = new JMenuItem("Pause game");            //New item (option) gis created
        menu.add(item);                                         //Item gets added to the dropdown list
        item.addActionListener(new ActionListener() {           //A so called "listener" is added --> Java version of delegates & events
            @Override                                           //Method is overriden
            public void actionPerformed(ActionEvent e) {
                world.pauseGame();                               //Everything in here is what happens when the Listener gets triggered
            }
        });

        item = new JMenuItem("Continue game");                      //The same item (variable) can be reused, just like any other variable 
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.continueGame();
            }
        });

        item = new JMenuItem("Restart game");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.restartGame();
            }
        });

        menu.addSeparator();        //Separation line in the dropdown 
        item = new JMenuItem("Close game");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    
    /**
     * Creates the options for a certain category (drop down) 
     * 
     * @param menu the "category" an option gets added to
     */
    public void charaCategoryOptions(JMenu menu) {
        JMenuItem item = new JMenuItem("Level up!");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getPlayer().levelUp();
            }
        });
        
        item = new JMenuItem("Set player level to 1");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getPlayer().setLevel(1);
            }
        });
        
        item = new JMenuItem("Set player level to 10");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getPlayer().setLevel(10);
            }
        });
        
        item = new JMenuItem("Set player level to 99");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getPlayer().setLevel(99);
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Turn player into knight");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                world.setPlayer(new Player(new Coordinates(460, 400), 40, 75, 1, "Knight", "Kyle", 1));
            }
        });
        
        item = new JMenuItem("Turn player into berserker");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                world.setPlayer(new Player(new Coordinates(460, 400), 45, 90, 1, "Berserker", "Kyle", 1));
            }
        });
        
        item = new JMenuItem("Turn player into hunter");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                world.setPlayer(new Player(new Coordinates(460, 400), 45, 60, 1, "Hunter", "Kyle", 1));
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Set players weapon damage to 20");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getPlayer().getWeapon().setDamage(20);
            }
        });
    }
    
    /**
     * Creates the options for a certain category (drop down) 
     * 
     * @param menu the "category" an option gets added to
     */
    public void mobCategoryOptions(JMenu menu) {
        JMenuItem item = new JMenuItem("Level up mob");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getMob1().levelUp();
            }
        });
        
        item = new JMenuItem("Set mob level to 1");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getMob1().setLevel(1);
            }
        });
        
        item = new JMenuItem("Set mob level to 10");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getMob1().setLevel(10);
            }
        });
        
        item = new JMenuItem("Set mob level to 99");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getMob1().setLevel(99);
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Turn mob into boss");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setMob1(new NPC(new Coordinates(400, 200), 200, 159, 1, "Boss1", "Asylum Demon", 1));
            }
        });
        
        item = new JMenuItem("Turn mob into regular mob");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setMob1(new NPC(new Coordinates(460, 200), 39, 36, 1, "Mob", "Orc", 1));
            }
        });
    }

}
