/**
 * @author Auer Marco
 */
package zeldiablo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Window extends JFrame {

    private final Canvas welt;

    public Window() {
        this.welt = new Canvas();                        //Ein neues Panel (Zeichenfläche) wird erstellt

        registerWindowListener();                   //Methodenaufruf der den WindowsListener erstellt
        createMenu();                               //Methodenaufruf der die Menüleiste erstellt
        add(welt);                                 //Fenster wird dem Rahmen hinzugefügt
        pack();                                     //Erstellt ein Window (bzw. einen Rahmen) in der richtigen Größe, passend zum Panel

        setTitle("The Legend of REKT Angel - by Auer & Wedenig");    //Titel des Fensters
        setLocation(400, 100);                      //Setzt die Startposition des Fensters
        setResizable(false);                        //Größe ist nicht änderbar. Macht sonst Probleme.
        setVisible(true);                           //Macht das Window sichtbar

    }

    private void registerWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);                     //Diese Methode sorgt dafür, dass das Programm auch wirklich beendet wird, sobald das Window (mit dem X) geschlossen wird
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                welt.pauseGame();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                welt.continueGame();
            }
        });
    }

    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();      //Neue Leiste wird erstellt
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");      //Neue Kategorie (dropdown) wird erstellt
        JMenu charaMenu = new JMenu("Character");
        JMenu mobMenu = new JMenu("Mob");

        menuBar.add(fileMenu);                //Kategorie wird der Leiste hinzugefügt   
        menuBar.add(charaMenu);
        menuBar.add(mobMenu);

        fileCategoryOptions(fileMenu);               //Methode zum hinzufügen von Menüpunkten in die Kategorie wird aufgerufen
        charaCategoryOptions(charaMenu);
        gegnerKategorieOptionen(mobMenu);
    }

    private void fileCategoryOptions(JMenu menu) {

        JMenuItem item = new JMenuItem("Pause game");            //Neues Item (Menü Option) wird generiert
        menu.add(item);                                         //Item wird der Kategorie hinzugefügt
        item.addActionListener(new ActionListener() {           //Ein sogenannter "listener" wird hinzugefügt --> Javaversion von delegates & events
            @Override                                           //Methode wird überschrieben
            public void actionPerformed(ActionEvent e) {
                welt.pauseGame();                               //Was hier steht ist, was passiert, wenn das Event getriggert wird (in dem Fall, wenn die Option gewählt wird)
            }
        });

        item = new JMenuItem("Continue game");                      //Das selbe (definierte) Item von oben kann einfach neu beschrieben und verwendet werden! 
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.continueGame();
            }
        });

        item = new JMenuItem("Restart game");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.restartGame();
            }
        });

        menu.addSeparator();        //Trennlinie in der dropdown Liste
        item = new JMenuItem("Close game");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    
    public void charaCategoryOptions(JMenu menu) {
        JMenuItem item = new JMenuItem("Level up!");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getPlayer().levelUp();
            }
        });
        
        item = new JMenuItem("Set player level to 1");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getPlayer().setLevel(1);
            }
        });
        
        item = new JMenuItem("Set player level to 10");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getPlayer().setLevel(10);
            }
        });
        
        item = new JMenuItem("Set player level to 99");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getPlayer().setLevel(99);
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Turn player into knight");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setPlayer(new Player(new Coordinates(460, 400), 40, 75, 1, "Knight", "Kyle", 1));
            }
        });
        
        item = new JMenuItem("Turn player into berserker");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setPlayer(new Player(new Coordinates(460, 400), 45, 90, 1, "Berserker", "Kyle", 1));
            }
        });
        
        item = new JMenuItem("Turn player into hunter");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setPlayer(new Player(new Coordinates(460, 400), 45, 60, 1, "Hunter", "Kyle", 1));
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Set players weapon damage to 20");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getPlayer().getWeapon().setDamage(20);
            }
        });
    }
    
    public void gegnerKategorieOptionen(JMenu menu) {
        JMenuItem item = new JMenuItem("Level up mob");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getMob1().levelUp();
            }
        });
        
        item = new JMenuItem("Set mob level to 1");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getMob1().setLevel(1);
            }
        });
        
        item = new JMenuItem("Set mob level to 10");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getMob1().setLevel(10);
            }
        });
        
        item = new JMenuItem("Set mob level to 99");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getMob1().setLevel(99);
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Turn mob into boss");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setMob1(new NPC(new Coordinates(400, 200), 200, 159, 1, "Boss1", "Asylum Demon", 1));
            }
        });
        
        item = new JMenuItem("Turn mob into regular mob");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setMob1(new NPC(new Coordinates(460, 200), 39, 36, 1, "Mob", "Orc", 1));
            }
        });
    }

}
