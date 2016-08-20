package xyz.morecraft.dev.components.tabs;

import xyz.morecraft.dev.types.Planet;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class JPanelPlanetOnTab extends JPanel {

    private JPanelPlanetOnTabMines kM, kK, kD;


    public JPanelPlanetOnTab(Planet p) {
        this.setBorder(BorderFactory.createTitledBorder(null, p.getName() + " [" + p.getCoordinates() + "] " + String.valueOf(p.getTemperature()) + "�C", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        this.setLayout(new GridLayout(1, 3));

        kM = new JPanelPlanetOnTabMines(p.getMetalMine(), p.getCoordinates());
        add(kM);

        kK = new JPanelPlanetOnTabMines(p.getCrystalMine(), p.getCoordinates());
        add(kK);

        kD = new JPanelPlanetOnTabMines(p.getDeuteriumExtractor(), p.getCoordinates());
        add(kD);

    }


}
