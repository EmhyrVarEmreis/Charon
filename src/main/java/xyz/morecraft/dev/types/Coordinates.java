package xyz.morecraft.dev.types;

public class Coordinates {

    private int galaxy;
    private int system;
    private int position;

    public Coordinates() {
        this("0:0:0");
    }

    public Coordinates(String c) {
        this(Integer.valueOf(c.split(":")[0]), Integer.valueOf(c.split(":")[1]), Integer.valueOf(c.split(":")[2]));
    }

    public Coordinates(int galaxy, int system, int position) {
        this.galaxy = galaxy;
        this.system = system;
        this.position = position;
    }

    public int getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(int galaxy) {
        this.galaxy = galaxy;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int toInt() {
        return Integer.valueOf(getGalaxy() + "" + getSystem() + "" + getPosition());
    }

    public String toString() {
        return String.valueOf(getGalaxy()) + ":" + String.valueOf(getSystem()) + ":" + String.valueOf(getPosition());
    }

    public boolean equals(Object c) {
        return (toInt() == ((Coordinates) c).toInt());
    }

}
