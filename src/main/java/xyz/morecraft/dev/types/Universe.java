package xyz.morecraft.dev.types;

public class Universe {

    private String nazwa;
    private double speed;

    public Universe(String nazwa, double speed) {
        super();
        this.nazwa = nazwa;
        this.speed = speed;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String toString() {
        return nazwa + ";" + String.valueOf(speed);
    }

}
