package xyz.morecraft.dev.objects;

import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfMaker {

    private String path;
    private List<String> lastV, l;
    private boolean isValid = true;
    private String instantLoadPath = null;

    public ConfMaker(String path) {
        this.path = path + "\\conf.OGConf";
        lastV = new ArrayList<>();
        read();
    }

    private void read() {
        File f = new File(path);
        File fdir = new File(Main.MAIN_APPPATH);
        if (!(fdir.isDirectory())) {
            FileUtils.makeDirectory(Main.MAIN_APPPATH);
        }
        if (f.exists() && f.isFile()) {
            BufferedReader br = null;
            String line;
            try {
                br = new BufferedReader(new FileReader(path));
                try {
                    while ((line = br.readLine()) != null) {
                        parse(line);
                    }
                    br.close();
                } catch (IOException e) {
                    // e.printStackTrace();
                    isValid = false;
                }
            } catch (FileNotFoundException e) {
                // e.printStackTrace();
            }
        } else {
            isValid = false;
        }
    }

    public boolean save() {
        boolean bSV = true;
        buildNewList();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
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

    private void buildNewList() {
        l = new ArrayList<String>();
        for (String y : lastV) {
            l.add("L: [" + y + "]");
        }
        if (instantLoadPath != null) {
            l.add("I: [" + instantLoadPath + "]");
        }
    }

    private void parse(String ll) {
        if (isValid() && (ll.startsWith("L"))) {
            parseLV(ll);
        }
        if (isValid() && (ll.startsWith("I"))) {
            parseLoadInstant(ll);
        }
        // if (isValid() && (ll.startsWith("X")))
        // parsePlayer(ll);
    }

    private void parseLV(String s) {
        lastV.add(s.substring(4, s.length() - 1));
        /*if (lastV.size() == 1)
            Main.DEFAULT_LOC = lastV.get(0).split("<=>")[0];*/
    }

    private void parseLoadInstant(String s) {
        if (((File) new File(s.substring(4, s.length() - 1))).exists()) {
            this.instantLoadPath = s.substring(4, s.length() - 1);
        }
        Main.DEFAULT_LOC = s.split("<=>")[0];
        //Main.app.coreSetCurrentFile(s.substring(4, s.length() - 1));
    }

    public String getCurFileInstantLoad() {
        return instantLoadPath;
    }

    public void setLastV(List<String> lastV) {
        this.lastV = lastV;
    }

    public List<String> getLastV() {
        return lastV;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setInstantLoadPath(String instantLoadPath) {
        this.instantLoadPath = instantLoadPath;
        save();
    }

}
