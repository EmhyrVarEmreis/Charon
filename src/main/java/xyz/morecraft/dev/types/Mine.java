package xyz.morecraft.dev.types;

public abstract class Mine {

    private int level;
    private int percent;
    private int speeder;
    private int production;

    public Mine(int level, int percent, int speeder) {
        this.level = level;
        this.percent = percent;
        this.speeder = speeder;
        recalculateProduction();
    }

    public int recalculateProduction() {
        return this.production = calculate();
    }

    public abstract int calculate();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getSpeeder() {
        return speeder;
    }

    public void setSpeeder(int speeder) {
        this.speeder = speeder;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

}
