package xyz.morecraft.dev.objects;

import xyz.morecraft.dev.components.summary.JPanelSummary;
import xyz.morecraft.dev.components.tabs.JTabbedPanePlanets;
import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.types.Coordinates;
import xyz.morecraft.dev.types.Planet;
import xyz.morecraft.dev.types.Tech;
import xyz.morecraft.dev.types.Universe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Generator extends Container {

    private static boolean reGenerate = false;
    private static CharonDbParser p;
    private static JTabbedPanePlanets jTPP;
    private static JPanelSummary jPS;
    private static ConfMaker cM;
    private static LastVisitor lV;
    private String currentFile = null;
    private boolean isToSaveB = false;
    public static String EXTENSION_DB = "CharonDb";

    public Generator() {
        cM = new ConfMaker(Main.MAIN_APPPATH);
        this.currentFile = cM.getCurFileInstantLoad();
        p = new CharonDbParser();
        jTPP = new JTabbedPanePlanets();
        jPS = new JPanelSummary();
        lV = new LastVisitor();
        if (cM.isValid()) {
            lV.setLastVisited(cM.getLastV());
            processMenu(lV.getMenuItems());
        }
    }

    public void refresh() {
        refreshTabs();
        refreshSummary();
    }

    public void saveFileAndProcess(String path) {
        saveFile(path);
        processFile(path);
    }

    public void saveFile(String path) {
        if (path == null) {
            path = currentFile;
        }
        p.saveAs(path);
        isToSaveB = false;
        lastVisitorProc(path, p.getPlayer());
    }

    public void processFile(String path) {
        mainRegenBefore();
        p.readFile(path, true);
        if (p.isValid()) {
            lastVisitorProc(path, p.getPlayer());
            refresh();
            mainRegenAfter();
            currentFile = path;
            Main.app.setContentEditable(true);
        } else {
            // TODO Alert
        }
    }

    public void processAll(List<Planet> arp, Tech tre, String nm, Universe uni) {
        p.setPlanets(arp, true);
        p.setTech(tre);
        p.setPlayer(nm);
        p.setUni(uni);
    }

    public List<Planet> getPlanets() {
        return p.getPlanets();
    }

    public Tech getTech() {
        return p.getTech();
    }

    public String getPlayer() {
        return p.getPlayer();
    }

    public Universe getUni() {
        return p.getUni();
    }

    public Planet getPlanetByCoordinates(Coordinates c) {
        return p.getPlanet(selectFromPlanetListByCoordinates(c));
    }

    public void changeAndCalculateOnPlanet(int what, int value, Coordinates coordinates, int tab) {
        int where = selectFromPlanetListByCoordinates(coordinates);
        Planet plot = p.getPlanet(where);
        switch (what) {
            case 0:
                plot.getMetalMine().setLevel(value);
                break;
            case 1:
                plot.getCrystalMine().setLevel(value);
                break;
            case 2:
                plot.getDeuteriumExtractor().setLevel(value);
                break;
            case 3:
                plot.getMetalMine().setSpeeder(value);
                break;
            case 4:
                plot.getCrystalMine().setSpeeder(value);
                break;
            case 5:
                plot.getDeuteriumExtractor().setSpeeder(value);
                break;
            case 6:
                plot.getMetalMine().setPercent(value);
                break;
            case 7:
                plot.getCrystalMine().setPercent(value);
                break;
            case 8:
                plot.getDeuteriumExtractor().setPercent(value);
                break;
        }
        p.setPlanet(where, plot);
        refreshSummary();
        if (tab == -1) {
            refreshTabs();
        } else {
            refreshTab(tab);
        }
        isToSaveB = true;
    }

    public void setCurrentFile(String t) {
        this.currentFile = t;
    }

    public void changeAndCalculateOnPlanet(int what, int whereChange, boolean increase, Coordinates coordinates) {
        int where = selectFromPlanetListByCoordinates(coordinates);
        Planet planet = p.getPlanet(where);
        p.setPlanet(where, planet);
        switch (what) {
            case 0:
                switch (whereChange) {
                    case 0:
                        if (increase) {
                            planet.getMetalMine().setLevel(planet.getMetalMine().getLevel() + 1);
                        } else {
                            if (planet.getMetalMine().getLevel() > 0) {
                                planet.getMetalMine().setLevel(planet.getMetalMine().getLevel() - 1);
                            }
                        }
                        break;
                    case 1:
                        if (increase) {
                            planet.getCrystalMine().setLevel(planet.getCrystalMine().getLevel() + 1);
                        } else {
                            if (planet.getCrystalMine().getLevel() > 0) {
                                planet.getCrystalMine().setLevel(planet.getCrystalMine().getLevel() - 1);
                            }
                        }
                        break;
                    case 2:
                        if (increase) {
                            planet.getDeuteriumExtractor().setLevel(planet.getDeuteriumExtractor().getLevel() + 1);
                        } else {
                            if (planet.getDeuteriumExtractor().getLevel() > 0) {
                                planet.getDeuteriumExtractor().setLevel(planet.getDeuteriumExtractor().getLevel() - 1);
                            }
                        }
                        break;
                }
                break;
            case 1:
                switch (whereChange) {
                    case 0:
                        if (increase) {
                            if (planet.getMetalMine().getSpeeder() < 30) {
                                planet.getMetalMine().setSpeeder(planet.getMetalMine().getSpeeder() + 10);
                            }
                        } else {
                            if (planet.getMetalMine().getSpeeder() > 0) {
                                planet.getMetalMine().setSpeeder(planet.getMetalMine().getSpeeder() - 10);
                            }
                        }
                        break;
                    case 1:
                        if (increase) {
                            if (planet.getCrystalMine().getSpeeder() < 30) {
                                planet.getCrystalMine().setSpeeder(planet.getCrystalMine().getSpeeder() + 10);
                            }
                        } else {
                            if (planet.getCrystalMine().getSpeeder() > 0) {
                                planet.getCrystalMine().setSpeeder(planet.getCrystalMine().getSpeeder() - 10);
                            }
                        }
                        break;
                    case 2:
                        if (increase) {
                            if (planet.getDeuteriumExtractor().getSpeeder() < 30) {
                                planet.getDeuteriumExtractor().setSpeeder(planet.getDeuteriumExtractor().getSpeeder() + 10);
                            }
                        } else {
                            if (planet.getDeuteriumExtractor().getSpeeder() > 0) {
                                planet.getDeuteriumExtractor().setSpeeder(planet.getDeuteriumExtractor().getSpeeder() - 10);
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (whereChange) {
                    case 0:
                        if (increase) {
                            if (planet.getMetalMine().getPercent() < 100) {
                                planet.getMetalMine().setPercent(planet.getMetalMine().getPercent() + 10);
                            }
                        } else {
                            if (planet.getMetalMine().getPercent() > 0) {
                                planet.getMetalMine().setPercent(planet.getMetalMine().getPercent() - 10);
                            }
                        }
                        break;
                    case 1:
                        if (increase) {
                            if (planet.getCrystalMine().getPercent() < 100) {
                                planet.getCrystalMine().setPercent(planet.getCrystalMine().getPercent() + 10);
                            }
                        } else {
                            if (planet.getCrystalMine().getPercent() > 0) {
                                planet.getCrystalMine().setPercent(planet.getCrystalMine().getPercent() - 10);
                            }
                        }
                        break;
                    case 2:
                        if (increase) {
                            if (planet.getDeuteriumExtractor().getPercent() < 100) {
                                planet.getDeuteriumExtractor().setPercent(planet.getDeuteriumExtractor().getPercent() + 10);
                            }
                        } else {
                            if (planet.getDeuteriumExtractor().getPercent() > 0) {
                                planet.getDeuteriumExtractor().setPercent(planet.getDeuteriumExtractor().getPercent() - 10);
                            }
                        }
                        break;
                }
                break;
        }
        planet.generateProduction();
        p.setPlanet(where, planet);
        refreshSummary();
        // refreshTabs();
        isToSaveB = true;
    }

    public boolean isToSave() {
        return isToSaveB;
    }

    public void makeCurrentPathLoadInstant() {
        cM.setInstantLoadPath(currentFile);
    }

    private int selectFromPlanetListByCoordinates(Coordinates c) {
        int ret = 0;
        for (int i = (p.getPlanets().size() - 1); i >= 0; i--) {
            if (c.equals(p.getPlanets().get(i).getCoordinates())) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    private void mainRegenBefore() {
        if (reGenerate) {
            Main.app.revalidate();
            Main.app.remove(jTPP);
            Main.app.remove(jPS);
        }
    }

    private void mainRegenAfter() {
        Main.app.setTitle("Charon OG Miner System PreAlpha - " + p.getPlayer());
        Main.app.add(jTPP);
        Main.app.add(jPS);
        Main.app.repaint();
        reGenerate = true;
    }

    private void refreshSummary() {
        jPS.setValues(p.getTech(), p.getWydSum(), p.getSrSts());
    }

    private void refreshTabs() {
        jTPP.refresh(p.getPlanets());
    }

    private void refreshTab(int ktora) {

    }

    private void lastVisitorProc(String path, String name) {
        lV.add(path, name);
        processMenu(lV.getMenuItems());
        cM.setLastV(lV.getLastVisited());
        cM.save();
    }

    private void processMenu(List<JMenuItem> i) {
        Main.app.mFileOpenRecent.removeAll();
        if (!(i == null)) {
            for (JMenuItem item : i) {
                Main.app.mFileOpenRecent.add(item);
            }
        } else {
            Main.app.mFileOpenRecent.add(Main.app.mFileOpenRecent);
        }
        Main.app.mFileOpenRecent.revalidate();
        Main.app.mFileOpenRecent.repaint();
    }

    public void abandonedActions() {
        if (!(currentFile == null)) {
            processFile(currentFile);
        }
    }

}
