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

public class Fenster extends JFrame {

    private final Leinwand welt;

    public Fenster() {
        this.welt = new Leinwand();                        //Ein neues Panel (Zeichenfläche) wird erstellt

        registerWindowListener();                   //Methodenaufruf der den WindowsListener erstellt
        createMenu();                               //Methodenaufruf der die Menüleiste erstellt
        add(welt);                                 //Fenster wird dem Rahmen hinzugefügt
        pack();                                     //Erstellt ein Fenster (bzw. einen Rahmen) in der richtigen Größe, passend zum Panel

        setTitle("The Legend of REKT Angel - by Auer & Wedenig");    //Titel des Fensters
        setLocation(400, 100);                      //Setzt die Startposition des Fensters
        setResizable(false);                        //Größe ist nicht änderbar. Macht sonst Probleme.
        setVisible(true);                           //Macht das Fenster sichtbar

    }

    private void registerWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);                     //Diese Methode sorgt dafür, dass das Programm auch wirklich beendet wird, sobald das Fenster (mit dem X) geschlossen wird
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                welt.spielPausieren();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                welt.spielFortsetzen();
            }
        });
    }

    private void createMenu() {

        JMenuBar menueLeiste = new JMenuBar();      //Neue Leiste wird erstellt
        this.setJMenuBar(menueLeiste);

        JMenu dateiMenue = new JMenu("Datei");      //Neue Kategorie (dropdown) wird erstellt
        JMenu charaMenue = new JMenu("Charakter");
        JMenu gegnerMenue = new JMenu("Gegner");

        menueLeiste.add(dateiMenue);                //Kategorie wird der Leiste hinzugefügt   
        menueLeiste.add(charaMenue);
        menueLeiste.add(gegnerMenue);

        dateiKategorieOptionen(dateiMenue);               //Methode zum hinzufügen von Menüpunkten in die Kategorie wird aufgerufen
        charaKategorieOptionen(charaMenue);
        gegnerKategorieOptionen(gegnerMenue);
    }

    private void dateiKategorieOptionen(JMenu menu) {

        JMenuItem item = new JMenuItem("Spiel pausieren");            //Neues Item (Menü Option) wird generiert
        menu.add(item);                                         //Item wird der Kategorie hinzugefügt
        item.addActionListener(new ActionListener() {           //Ein sogenannter "listener" wird hinzugefügt --> Javaversion von delegates & events
            @Override                                           //Methode wird überschrieben
            public void actionPerformed(ActionEvent e) {
                welt.spielPausieren();                               //Was hier steht ist, was passiert, wenn das Event getriggert wird (in dem Fall, wenn die Option gewählt wird)
            }
        });

        item = new JMenuItem("Spiel fortsetzen");                      //Das selbe (definierte) Item von oben kann einfach neu beschrieben und verwendet werden! 
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.spielFortsetzen();
            }
        });

        item = new JMenuItem("Spiel neu starten");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.spielNeustarten();
            }
        });

        menu.addSeparator();        //Trennlinie in der dropdown Liste
        item = new JMenuItem("Spiel schließen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    
    public void charaKategorieOptionen(JMenu menu) {
        JMenuItem item = new JMenuItem("Level up!");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getSpieler().levelUp();
            }
        });
        
        item = new JMenuItem("Spieler-Level auf 1 setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getSpieler().setLevel(1);
            }
        });
        
        item = new JMenuItem("Spieler-Level auf 10 setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getSpieler().setLevel(10);
            }
        });
        
        item = new JMenuItem("Spieler-Level auf 99 setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getSpieler().setLevel(99);
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Spieler in Krieger verwandeln");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setSpieler(new Spieler(new Koordinaten(460, 400), 40, 75, 1, "Krieger", "Kyle", 1));
            }
        });
        
        item = new JMenuItem("Spieler in Barbar verwandeln");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setSpieler(new Spieler(new Koordinaten(460, 400), 45, 90, 1, "Barbar", "Kyle", 1));
            }
        });
        
        item = new JMenuItem("Spieler in Jäger verwandeln");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setSpieler(new Spieler(new Koordinaten(460, 400), 45, 60, 1, "Jäger", "Kyle", 1));
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Spieler-Waffe auf 20 Schaden setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getSpieler().getWaffe().setSchaden(20);
            }
        });
    }
    
    public void gegnerKategorieOptionen(JMenu menu) {
        JMenuItem item = new JMenuItem("Gegner aufleveln");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getGegner1().levelUp();
            }
        });
        
        item = new JMenuItem("Gegner-Level auf 1 setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getGegner1().setLevel(1);
            }
        });
        
        item = new JMenuItem("Gegner-Level auf 10 setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getGegner1().setLevel(10);
            }
        });
        
        item = new JMenuItem("Gegner-Level auf 99 setzen");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.getGegner1().setLevel(99);
            }
        });
        
        menu.addSeparator();
        item = new JMenuItem("Gegner in Boss verwandeln");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setGegner1(new NPC(new Koordinaten(400, 200), 200, 159, 1, "Boss1", "Asylum Demon", 1));
            }
        });
        
        item = new JMenuItem("Gegner in normalen Gegner verwandeln");
        menu.add(item);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welt.setGegner1(new NPC(new Koordinaten(460, 200), 39, 36, 1, "Mob", "Orc", 1));
            }
        });
    }

}
