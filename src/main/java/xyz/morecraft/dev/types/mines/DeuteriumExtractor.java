package xyz.morecraft.dev.types.mines;

import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.types.Mine;

public class DeuteriumExtractor extends Mine {

    private int temperature;

    public DeuteriumExtractor(int level, int percent, int speeder, int temperature) {
        super(level, percent, speeder);
        this.temperature = temperature;
    }

    @Override
    public int calculate() {
        return (int) Math.round((Main.app.getUni().getSpeed() * 10 * getLevel() * Math.pow(1.1, getLevel())) * ((1.44 - (0.004 * getTemperature()))) * ((getSpeeder() + 100) / 100.0));
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}
