/**
 * @author Auer Marco
 */
package zeldiablo;

import javax.swing.ImageIcon;

public class Dialog {

    private String dialogZeile1, dialogZeile2, npcname;
    private boolean dialogSichtbarkeit;
    private int dialogCounter;
    private String[] dialogSeite;
    private ImageIcon npc;

    public Dialog() {
        dialogZeile1 = "";
        dialogZeile2 = "";
        npcname = "";
        dialogSichtbarkeit = false;
        dialogCounter = 0;
        dialogSeite = new String[]{""};
    }

//    public NPCDialog(Koordinaten objektPosition, int breite, int hoehe) {
//        super(objektPosition, breite, hoehe);
//    }
    public String getDialogZeile1() {
        return dialogZeile1;
    }

    public void setDialogZeile1(String dialog) {
        dialogZeile1 = dialog;
    }

    public String getDialogZeile2() {
        return dialogZeile2;
    }

    public void setDialogZeile2(String dialog) {
        dialogZeile2 = dialog;
    }

    public String getNPCname() {
        return npcname;
    }

    public void setNPCname(String npcname) {
        this.npcname = npcname;
    }

    public boolean getDialogSichtbarkeit() {
        return dialogSichtbarkeit;
    }

    public void setDialogSichtbarkeit(boolean dialogSichtbarkeit) {
        this.dialogSichtbarkeit = dialogSichtbarkeit;
    }

    public ImageIcon getNPCsprite() {
        return npc;
    }

    public void dialogSunbro(Spieler spieler, Figur npc) {
        dialogSichtbarkeit = true;
        this.npc = npc.getSprite();
        npcname = npc.getName();
        dialogSeite = new String[]{"Praise the sun!"};
        dialogLogik();
    }

    public void dialogSchurke(Spieler spieler, Figur npc) {
        dialogSichtbarkeit = true;
        this.npc = npc.getSprite();
        npcname = npc.getName();
        dialogSeite = new String[]{"Langer blabla Text Zeile 1.1", "Zeile 2.1", "Zeile 3"};
        dialogLogik();
    }

    public void dialogLogik() {
        if ((dialogCounter + 1) < dialogSeite.length) {
            dialogZeile1 = dialogSeite[dialogCounter];
            dialogZeile2 = dialogSeite[dialogCounter + 1];
            dialogCounter += 2;
        } else if (dialogCounter < dialogSeite.length) {
            dialogZeile1 = dialogSeite[dialogCounter];
            dialogZeile2 = "";
            dialogCounter++;
        } else {
            dialogCounter = 0;
            dialogSichtbarkeit = false;
        }
    }
//    @Override
//    protected void zeichneObjekte(Graphics g) {
//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
//        g.setColor(Color.RED);
//        g.drawString("PRAISE THE SUN!", 400, 400);
//    }
}
