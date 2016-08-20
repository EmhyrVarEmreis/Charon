package xyz.morecraft.dev.components;

import xyz.morecraft.dev.functions.LoadFromRes;
import xyz.morecraft.dev.types.Planet;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class JPanelPlanetOnList extends JPanel {

    private JLabel lKM, lKK, lKD, lDM, lDK, lDD, lPM, lPK, lPD;
    private JButton bDelete, bChange;
    private Planet pcor;

    public JPanelPlanetOnList(Planet p, boolean color) {
        JLabel[][] lT = new JLabel[][]{{lKM, lKK, lKD}, {lDM, lDK, lDD}, {lPM, lPK, lPD}};
        String[][] str = new String[][]{
                {"Metal mine: " + p.getMetalMine().getLevel(), "Crystal mine: " + p.getCrystalMine().getLevel(), "Deuter extractor: " + p.getDeuteriumExtractor().getLevel()},
                {"Metal speeder: " + p.getMetalMine().getSpeeder(), "Crystal speeder: " + p.getCrystalMine().getSpeeder(), "Deuter speeder: " + p.getDeuteriumExtractor().getSpeeder()},
                {"Metal percent: " + p.getMetalMine().getPercent(), "Crystal percent: " + p.getCrystalMine().getPercent(), "Deuter percent: " + p.getDeuteriumExtractor().getPercent()}};
        this.pcor = p;
        setLayout(null);
        setMaximumSize(new Dimension(1200000, 60));
        setPreferredSize(new Dimension(0, 60));
        setBorder(BorderFactory.createTitledBorder(null, p.getName() + " [" + p.getCoordinates().toString() + "] " + String.valueOf(p.getTemperature()) + "�C",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
        if (color) {
            setBackground(new Color(225, 225, 225));
        }

        for (int i1 = 0; i1 < 3; i1++) {
            for (int i2 = 0; i2 < 3; i2++) {
                lT[i2][i1] = new JLabel(str[i2][i1]);
                lT[i2][i1].setBounds(15 + (i2 * 130), 15 + (i1 * 12), 125, 12);
                lT[i2][i1].setFont(new Font("Tahoma", Font.PLAIN, 10));
                add(lT[i2][i1]);
            }
        }

        bDelete = new JButton(LoadFromRes.loadImageAsIconImage("icons/Erase.png"));
        bDelete.setBounds(416, 10, 22, 22);
        bDelete.addActionListener(e -> ((JPanelNewDatabase) getParent().getParent().getParent().getParent().getParent()).deletePlanetFromListByCoordinates(pcor.getCoordinates()));
        add(bDelete);

        bChange = new JButton(LoadFromRes.loadImageAsIconImage("icons/Notes.png"));
        bChange.setBounds(416, 33, 22, 22);
        bChange.addActionListener(e -> ((JPanelNewDatabase) getParent().getParent().getParent().getParent().getParent()).loadPlanet(pcor));
        add(bChange);
    }

}
