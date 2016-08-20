package xyz.morecraft.dev.types.mines;

import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.types.Mine;

public class MetalMine extends Mine {

    public MetalMine(int level, int percent, int speeder) {
        super(level, percent, speeder);
        recalculateProduction();
    }

    @Override
    public int calculate() {
        return (int) (Main.app.getUni().getSpeed() * 30 + (int) Math.round((Main.app.getUni().getSpeed() * 30 * getLevel() * Math.pow(1.1, getLevel()))
                * ((getSpeeder() + 100 + Main.app.getTech().getPlasmaTech()) / 100.0)));
    }

}
