package zeldiablo;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * This class contains the logic for the dialogs and determines which NPC has which dialog by having a method for every NPC / dialog
 * 
 * @author Auer Marco
 */
public class Dialog implements Serializable {

    private String dialogLine1, dialogLine2, npcName;
    private boolean dialogVisible;
    private int dialogCounter;
    private String[] dialogPage;
    private ImageIcon npc;

    /**
     * Sets all the data members to their beginning state
     */
    public Dialog() {
        dialogLine1 = "";
        dialogLine2 = "";
        npcName = "";
        dialogVisible = false;
        dialogCounter = 0;
        dialogPage = new String[]{""};
    }

    public String getDialogLine1() {
        return dialogLine1;
    }

    public void setDialogLine1(String dialog) {
        dialogLine1 = dialog;
    }

    public String getDialogLine2() {
        return dialogLine2;
    }

    public void setDialogLine2(String dialog) {
        dialogLine2 = dialog;
    }

    public String getNPCname() {
        return npcName;
    }

    public void setNPCname(String npcname) {
        this.npcName = npcname;
    }

    public boolean getDialogVisible() {
        return dialogVisible;
    }

    public void setDialogVisible(boolean dialogVisible) {
        this.dialogVisible = dialogVisible;
    }

    public ImageIcon getNPCsprite() {
        return npc;
    }

    /**
     * Determines the dialog for the NPC Solaire
     * 
     * @param player The player character - the figure you control
     * @param npc the NPC whose dialog you will see upon interaction
     */
    public void dialogSunbro(Player player, Character npc) {
        dialogVisible = true;
        this.npc = npc.getSprite();
        npcName = npc.getName();
        dialogPage = new String[]{"Praise the sun!"};
//        dialogLogic();
    }

    /**
     * Determines the dialog for the NPC Unknown Rogue. 
     * This one is a demo that shows that a dialog can have multiple lines and multiple pages
     * 
     * @param player The player character - the figure you control
     * @param npc the NPC whose dialog you will see upon interaction
     */
    public void dialogRogue(Player player, Character npc) {
        dialogVisible = true;
        this.npc = npc.getSprite();
        npcName = npc.getName();
        dialogPage = new String[]{"blabla long text line 1.1", "line 2.1", "line 3"};
//        dialogLogic();
    }
    
    /**
     * The logic for switching between dialog pages and displaying 2 lines in the dialog box
     * You get the next dialog page (if there is one) by pressing J while talking to an NPC
     * If there is no next page, it closes the dialogbox
     */
    public void dialogLogic() {
        if ((dialogCounter + 1) < dialogPage.length) {
            dialogLine1 = dialogPage[dialogCounter];
            dialogLine2 = dialogPage[dialogCounter + 1];
            dialogCounter += 2;
        } else if (dialogCounter < dialogPage.length) {
            dialogLine1 = dialogPage[dialogCounter];
            dialogLine2 = "";
            dialogCounter++;
        } else {
            dialogCounter = 0;
            dialogVisible = false;
        }
    }
    
    //The dialog and its box is drawn directly on the canvas, so there is no method for it here
}
