package xyz.morecraft.dev.components.tabs;

import xyz.morecraft.dev.types.Planet;

import javax.swing.*;
import java.util.List;

public class JTabbedPanePlanets extends JTabbedPane {
    private boolean isTP = false;

    public JTabbedPanePlanets() {
        setBounds(0, 0, 796, 345);
        isTP = true;
    }

    public void refresh(List<Planet> planetList) {
        reV();
        for (Planet p : planetList) {
            this.addTab("[" + p.getCoordinates() + "] " + p.getName(), new JPanelPlanetOnTab(p));
        }
        reP();
    }

    private void reV() {
        if (isTP) {
            removeAll();
        }
    }

    private void reP() {
        if (isTP) {
            repaint();
        }
    }

}
