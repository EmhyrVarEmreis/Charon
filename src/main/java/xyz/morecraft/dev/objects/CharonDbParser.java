package xyz.morecraft.dev.objects;

import xyz.morecraft.dev.types.Coordinates;
import xyz.morecraft.dev.types.Planet;
import xyz.morecraft.dev.types.Tech;
import xyz.morecraft.dev.types.Universe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static xyz.morecraft.dev.functions.NumberUtils.isInteger;
import static xyz.morecraft.dev.functions.NumberUtils.round;

public class CharonDbParser {
    private List<String> l;
    private Tech tech;
    private List<Planet> planets;
    private String player = "";
    private Universe uni;

    private int[] wydSum = new int[]{0, 0, 0};
    private double[] srSts = new double[]{0, 0, 0, 0, 0, 0};

    private boolean isValid = true;

    public CharonDbParser() {
        this(null);
    }

    public CharonDbParser(String path) {
        this(path, false);
    }

    public CharonDbParser(String path, boolean b) {
        l = new ArrayList<>();
        planets = new ArrayList<>();
        if (path != null) {
            if (new File(path).exists()) {
                readFile(path, b);
            }
        }
    }

    public void readFile(String path, boolean b) {
        l.clear();
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                l.add(line);
            }
            br.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }

        if (b) {
            parse();
        } else {
            isValid = false;
        }
    }

    public void parse() {
        planets.clear();
        for (String ll : l) {
            if (isValid() && (ll.startsWith("T"))) {
                parseTech(ll);
            } else if (isValid() && (ll.startsWith("U"))) {
                parseUniverse(ll);
            } else if (isValid() && (ll.startsWith("P"))) {
                parsePlanet(ll);
            } else if (isValid() && (ll.startsWith("X"))) {
                parsePlayer(ll);
            }
        }
        if (isValid() && (planets.size() > 0)) {
            generateAll();
        }
    }

    public void generateAll() {
        generateSummaryProduction();
        generateAverages();
    }

    public void generateSummaryProduction() {
        wydClear();
        for (Planet p : planets) {
            this.wydSum[0] = wydSum[0] + p.getMetalMine().getProduction();
            this.wydSum[1] = wydSum[1] + p.getMetalMine().getProduction();
            this.wydSum[2] = wydSum[2] + p.getDeuteriumExtractor().getProduction();
        }
    }

    public void generateAverages() {
        srClear();

        for (Planet p : planets) {
            this.srSts[0] = srSts[0] + p.getMetalMine().getLevel();
            this.srSts[1] = srSts[1] + p.getCrystalMine().getLevel();
            this.srSts[2] = srSts[2] + p.getDeuteriumExtractor().getLevel();

            this.srSts[3] = srSts[3] + p.getMetalMine().getProduction();
            this.srSts[4] = srSts[4] + p.getCrystalMine().getProduction();
            this.srSts[5] = srSts[5] + p.getDeuteriumExtractor().getProduction();
        }

        for (int i = 0; i < 6; i++) {
            this.srSts[i] = srSts[i] / planets.size();
        }

        for (int i = 0; i < 4; i++) {
            this.srSts[i] = round(srSts[i], 2);
        }

    }

    public void parseTech(String s) {
        boolean b = true;

        s = s.substring(4, s.length() - 1);
        String[] ss = s.split(";");
        int[] si = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            if (isInteger(ss[i])) {
                si[i] = (int) Integer.valueOf(ss[i]);
            } else {
                b = false;
                break;
            }
        }
        if (b) {
            tech = new Tech(si[0], si[1], si[2], si[3]);
        } else {
            isValid = false;
        }
    }

    public void parseUniverse(String s) {
        boolean b = true;

        s = s.substring(4, s.length() - 1);
        String[] ss = s.split(";");

        if (b) {
            uni = new Universe(ss[0], Double.valueOf(ss[1]));
        } else {
            isValid = false;
        }
    }

    public void parsePlanet(String s) {
        boolean b = true;

        s = s.substring(4, s.length() - 1);
        String[] ss = s.split(";");
        int[] si = new int[ss.length - 2];
        for (int i = 2; i < ss.length; i++) {
            if (!(isInteger(ss[i]))) {
                b = false;
            } else {
                si[i - 2] = Integer.valueOf(ss[i]);
            }
        }
        if (b) {
            planets.add(new Planet(ss[0], new Coordinates(ss[1]), si[0], si[1], si[2], si[3], si[4], si[5], si[6], si[7], si[8], si[9]/*, uni*/));
        } else {
            isValid = false;
        }
    }

    public boolean saveAs(String fileName) {
        boolean bSV = true;
        buildNewList();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            bSV = false;
        }
        if (bSV) {
            for (String sv : l) {
                pw.println(sv);
            }
            pw.close();
        }
        return bSV;
    }

    public void parsePlayer(String s) {
        player = s.substring(4, s.length() - 1);
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Universe getUni() {
        return uni;
    }

    public int getNumberofPlanets() {
        return this.planets.size();
    }

    public Planet getPlanet(int i) {
        return this.planets.get(i);
    }

    public Tech getTech() {
        return tech;
    }

    public double[] getSrSts() {
        return srSts;
    }

    public int[] getWydSum() {
        return wydSum;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setPlanets(List<Planet> planets, boolean br) {
        this.planets.clear();
        this.planets = planets;
        if (br) {
            generateAll();
        }
    }

    public void setPlanet(int i, Planet plp) {
        this.planets.set(i, plp);
        generateAll();
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public void setUni(Universe uni) {
        this.uni = uni;
    }

    private void buildNewList() {
        l.clear();
        l.add("X: [" + player + "]");
        l.add("U: [" + uni.toString() + "]");
        l.add("T: [" + tech.getBattleTech() + ";" + tech.getArmourTech() + ";" + tech.getProtectionTech() + ";" + tech.getPlasmaTech() + "]");
        for (Planet po : planets) {
            l.add("P: [" + po.toString() + "]");
        }
    }

    private void wydClear() {
        for (int i = 0; i < wydSum.length; i++) {
            wydSum[i] = 0;
        }
    }

    private void srClear() {
        for (int i = 0; i < srSts.length; i++) {
            srSts[i] = 0;
        }
    }

}
