package xyz.morecraft.dev.types;

import xyz.morecraft.dev.types.mines.CrystalMine;
import xyz.morecraft.dev.types.mines.DeuteriumExtractor;
import xyz.morecraft.dev.types.mines.MetalMine;

public class Planet {

    private MetalMine metalMine;
    private CrystalMine crystalMine;
    private DeuteriumExtractor deuteriumExtractor;
    private int temperature;
    private String name;
    private Coordinates coordinates;

    public Planet(String name, Coordinates coordinates, int temperature, int metalMineLevel, int crystalMineLevel, int deuteriumExtractorLevel, int metalMinePercent, int crystalMinePercent, int deuteriumExtractorPercent, int metalMineSpeeder, int crystalMineSpeeder, int deuteriumExtractorSpeeder) {
        this.metalMine = new MetalMine(metalMineLevel, metalMinePercent, metalMineSpeeder);
        this.crystalMine = new CrystalMine(crystalMineLevel, crystalMinePercent, crystalMineSpeeder);
        this.deuteriumExtractor = new DeuteriumExtractor(deuteriumExtractorLevel, deuteriumExtractorPercent, deuteriumExtractorSpeeder, temperature);
        this.coordinates = coordinates;
        this.temperature = temperature;
        this.name = name;
    }

    public String toString() {
        return name
                + ";" + coordinates
                + ";" + temperature
                + ";" + metalMine.getLevel()
                + ";" + crystalMine.getLevel()
                + ";" + deuteriumExtractor.getLevel()
                + ";" + metalMine.getPercent()
                + ";" + crystalMine.getPercent()
                + ";" + deuteriumExtractor.getPercent()
                + ";" + metalMine.getSpeeder()
                + ";" + crystalMine.getSpeeder()
                + ";" + deuteriumExtractor.getSpeeder();
    }

    public MetalMine getMetalMine() {
        return metalMine;
    }

    public void setMetalMine(MetalMine metalMine) {
        this.metalMine = metalMine;
    }

    public CrystalMine getCrystalMine() {
        return crystalMine;
    }

    public void setCrystalMine(CrystalMine crystalMine) {
        this.crystalMine = crystalMine;
    }

    public DeuteriumExtractor getDeuteriumExtractor() {
        return deuteriumExtractor;
    }

    public void setDeuteriumExtractor(DeuteriumExtractor deuteriumExtractor) {
        this.deuteriumExtractor = deuteriumExtractor;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void generateProduction() {
        getMetalMine().recalculateProduction();
        getCrystalMine().recalculateProduction();
        getDeuteriumExtractor().recalculateProduction();
    }

}
