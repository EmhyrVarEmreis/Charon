package xyz.morecraft.dev.components.summary;

import xyz.morecraft.dev.types.Tech;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static xyz.morecraft.dev.lang.Lang.getLang;


public class JPanelSummaryTech extends JPanel {

    private JLabel[][] t = new JLabel[][]{{null, null, null, null}, {null, null, null, null}};
    private String[] tS = new String[]{getLang(11), getLang(12), getLang(13), getLang(14)};

    public JPanelSummaryTech() {
        setBounds(600, 12, 187, 95);
        setBorder(BorderFactory.createTitledBorder(null, "Technology", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
        setLayout(null);


        for (int i = 0; i < 4; i++) {
            t[0][i] = new JLabel(tS[i] + ":");
            t[0][i].setBounds(30, 15 + i * 17, 90, 20);
            t[0][i].setFont(new Font("Tahoma", Font.BOLD, 11));
            add(t[0][i]);
        }

        for (int i = 0; i < 4; i++) {
            t[1][i] = new JLabel("", JLabel.RIGHT);
            t[1][i].setBounds(145, 15 + i * 17, 16, 20);
            t[1][i].setFont(new Font("Tahoma", Font.BOLD, 11));
            add(t[1][i]);
        }
    }

    public void setValues(Tech tech) {
        t[1][0].setText(String.valueOf(tech.getBattleTech()));
        t[1][1].setText(String.valueOf(tech.getProtectionTech()));
        t[1][2].setText(String.valueOf(tech.getArmourTech()));
        t[1][3].setText(String.valueOf(tech.getPlasmaTech()));
    }

}
