package xyz.morecraft.dev.components.summary;

import xyz.morecraft.dev.types.Tech;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class JPanelSummary extends JPanel {

    private JPanelSummaryMain jPSM;
    private JPanelSummaryTech jPST;
    private JPanelSummaryStats jPSS;

    public JPanelSummary() {
        this.setBounds(0, 350, 792, 205);
        this.setBorder(BorderFactory.createTitledBorder(null, "SUMMARY", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
        this.setLayout(null);
        this.jPSM = new JPanelSummaryMain();
        this.jPST = new JPanelSummaryTech();
        this.jPSS = new JPanelSummaryStats();
        this.add(jPSM);
        this.add(jPST);
        this.add(jPSS);
    }

    public void setValues(Tech tech, int[] wydSum, double[] srSts) {
        jPSM.setValues(wydSum);
        jPSS.setValues(srSts);
        jPST.setValues(tech);
    }


}
