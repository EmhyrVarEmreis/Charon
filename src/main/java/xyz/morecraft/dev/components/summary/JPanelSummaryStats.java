package xyz.morecraft.dev.components.summary;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class JPanelSummaryStats extends JPanel {

    private JLabel l1, l2;
    private JLabel l1a, l2a;

    public JPanelSummaryStats() {
        setBounds(600, 105, 187, 94);
        setBorder(BorderFactory.createTitledBorder(null, "Statisticts", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
        setLayout(null);

        l1 = new JLabel("Average levels M/C/D");
        l1.setBounds(30, 15, 130, 20);
        l1.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l1);

        l1a = new JLabel("");
        l1a.setBounds(30, 32, 130, 20);
        l1a.setFont(new Font("Tahoma", Font.PLAIN, 11));
        add(l1a);

        l2 = new JLabel("Average production M/C/D");
        l2.setBounds(30, 49, 130, 20);
        l2.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l2);

        l2a = new JLabel("");
        l2a.setBounds(30, 66, 130, 20);
        l2a.setFont(new Font("Tahoma", Font.PLAIN, 11));
        add(l2a);

    }


    public void setValues(double[] srSts) {
        l1a.setText(srSts[0] + "/" + srSts[1] + "/" + srSts[2]);
        l2a.setText((int) srSts[3] + "/" + (int) srSts[4] + "/" + (int) srSts[5]);
    }
}
