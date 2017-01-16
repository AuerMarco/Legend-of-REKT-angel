/**
 * @author Auer Marco
 */
package zeldiablo;

import javax.swing.ImageIcon;

public class Dialog {

    private String dialogLine1, dialogLine2, npcName;
    private boolean dialogVisible;
    private int dialogCounter;
    private String[] dialogPage;
    private ImageIcon npc;

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

    public void dialogSunbro(Player player, Character npc) {
        dialogVisible = true;
        this.npc = npc.getSprite();
        npcName = npc.getName();
        dialogPage = new String[]{"Praise the sun!"};
        dialogLogic();
    }

    public void dialogRogue(Player player, Character npc) {
        dialogVisible = true;
        this.npc = npc.getSprite();
        npcName = npc.getName();
        dialogPage = new String[]{"blabla long text line 1.1", "line 2.1", "line 3"};
        dialogLogic();
    }

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
//    @Override
//    protected void drawObjects(Graphics g) {
//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
//        g.setColor(Color.RED);
//        g.drawString("PRAISE THE SUN!", 400, 400);
//    }
}
