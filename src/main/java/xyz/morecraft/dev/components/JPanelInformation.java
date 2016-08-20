package xyz.morecraft.dev.components;

import javax.swing.*;
import java.awt.*;

import static xyz.morecraft.dev.functions.LoadFromRes.loadImageAsImage;

public class JPanelInformation extends JPanel {

    private JLabel l1, l2, l3;
    private Image img1;

    public JPanelInformation() {
        setLayout(null);

        img1 = loadImageAsImage("icons/icon_app.png");

        l1 = new JLabel("Charon OG Miner System");
        l1.setBounds(60, 10, 300, 20);
        l1.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(l1);

        l2 = new JLabel("produced by EmhyrVarEmreis (Mateusz Stefaniak) 2013");
        l2.setBounds(60, 30, 200, 30);
        l2.setFont(new Font("Tahoma", Font.ITALIC | Font.BOLD, 9));
        add(l2);

        l3 = new JLabel("Contact: mateusz.stefaniak@morecraft.pl", JLabel.RIGHT);
        l3.setBounds(210, 70, 180, 30);
        l3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        add(l3);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img1, 15, 15, this);
    }

}
