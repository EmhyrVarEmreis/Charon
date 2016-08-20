package xyz.morecraft.dev.types.mines;

import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.types.Mine;

public class CrystalMine extends Mine {

    public CrystalMine(int level, int percent, int speeder) {
        super(level, percent, speeder);
    }

    @Override
    public int calculate() {
        return (int) (Main.app.getUni().getSpeed() * 15 + (int) Math.round((Main.app.getUni().getSpeed() * 20 * getLevel() * Math.pow(1.1, getLevel()))
                * ((getSpeeder() + 100 + Main.app.getTech().getPlasmaTech() * 0.66) / 100.0)));
    }

}
