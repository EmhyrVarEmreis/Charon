package xyz.morecraft.dev.types;

public class Tech {

    private int battleTech, armourTech, protectionTech, plasmaTech;

    public Tech() {
        this(0, 0, 0, 0);
    }

    public Tech(int battleTech, int armourTech, int protectionTech, int plasmaTech) {
        this.battleTech = battleTech;
        this.armourTech = armourTech;
        this.protectionTech = protectionTech;
        this.plasmaTech = plasmaTech;
    }

    public int getBattleTech() {
        return battleTech;
    }

    public void setBattleTech(int battleTech) {
        this.battleTech = battleTech;
    }

    public int getArmourTech() {
        return armourTech;
    }

    public void setArmourTech(int armourTech) {
        this.armourTech = armourTech;
    }

    public int getProtectionTech() {
        return protectionTech;
    }

    public void setProtectionTech(int protectionTech) {
        this.protectionTech = protectionTech;
    }

    public int getPlasmaTech() {
        return plasmaTech;
    }

    public void setPlasmaTech(int plasmaTech) {
        this.plasmaTech = plasmaTech;
    }

}
