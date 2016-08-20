package xyz.morecraft.dev.components.tabs;

import xyz.morecraft.dev.functions.LoadFromRes;
import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.types.Coordinates;
import xyz.morecraft.dev.types.Planet;

import javax.swing.*;

public class JPanelPlanetOnTabButtons extends JButton {

    private int whatToChange, whereToChange;
    private Coordinates coordinates;
    private ImageIcon img1;
    private boolean increase;

    public JPanelPlanetOnTabButtons(boolean increase, int whereToChange, int whatToChange, Coordinates coordinates) {

        if (increase) {
            img1 = LoadFromRes.loadImageAsIconImage("icons/Up.png");
        } else {
            img1 = LoadFromRes.loadImageAsIconImage("icons/Down.png");
        }

        this.increase = increase;
        this.whatToChange = whatToChange;
        this.whereToChange = whereToChange;
        this.coordinates = coordinates;

        this.setIcon(img1);
        this.setSize(22, 22);
        this.addActionListener(e -> {
            Main.app.changeSthLiczOnPlanet(this.whatToChange, this.whereToChange, this.increase, this.coordinates);
            Planet planet = Main.app.getPlanetByCoords(this.coordinates);
            switch (this.whereToChange) {
                case 0:
                    ((JPanelPlanetOnTabMines) getParent()).refresh(planet.getMetalMine());
                    break;
                case 1:
                    ((JPanelPlanetOnTabMines) getParent()).refresh(planet.getCrystalMine());
                    break;
                case 2:
                    ((JPanelPlanetOnTabMines) getParent()).refresh(planet.getDeuteriumExtractor());
                    break;
            }

        });
    }

}
