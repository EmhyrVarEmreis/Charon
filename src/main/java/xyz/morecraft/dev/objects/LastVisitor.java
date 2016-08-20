package xyz.morecraft.dev.objects;

import xyz.morecraft.dev.main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static xyz.morecraft.dev.functions.LoadFromRes.loadImageAsIconImage;

public class LastVisitor {

    private List<String> lastVisited;
    private int MAX_LASTVISITED = 5;

    public LastVisitor() {
        lastVisited = new ArrayList<>();
    }

    public void add(String path, String name) {
        Main.DEFAULT_LOC = path;
        if (!(contains(path, lastVisited))) {
            lastVisited.add(0, path + "<=>" + name);
        }
        if (lastVisited.size() > MAX_LASTVISITED) {
            lastVisited.remove(lastVisited.size() - 1);
        }
    }

    public void reGenerate() {
        while (lastVisited.size() > MAX_LASTVISITED) {
            lastVisited.remove(lastVisited.size() - 1);
        }
    }

    public boolean isSomething() {
        if (lastVisited.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<JMenuItem> getMenuItems() {
        int kEve[] = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8,
                KeyEvent.VK_9};
        ArrayList<JMenuItem> itm = new ArrayList<JMenuItem>();
        if (isSomething()) {
            int n = 0;
            for (int i = 0; i < lastVisited.size(); i++) {
                String[] st = lastVisited.get(i).split("<=>");
                if (new File(st[0]).exists()) {
                    itm.add(
                            new JMLV(
                                    st[1] +
                                            " - " + ((st[0].length() > 60) ? st[0].substring(st[0].length() - 60) : st[0]),
                                    st[0],
                                    kEve[n++]));
                }
            }
        }

        for (int i = lastVisited.size(); i > 0; i--) {
            if (!(new File(lastVisited.get(i - 1).split("<=>")[0]).exists())) {
                lastVisited.remove(i - 1);
            }
        }

        if (itm.size() == 0) {
            itm = null;
        }
        return itm;
    }

    public String getPath(String playerName) {
        String ret = null;
        for (String s : lastVisited) {
            String[] str = s.split("<=>");
            if (str[1].equalsIgnoreCase(playerName)) {
                ret = str[0];
            }
        }
        return ret;
    }

    private boolean contains(String st, List<String> l) {
        boolean b = false;
        for (String s : l) {
            if (s.toLowerCase().startsWith(st.toLowerCase())) {
                b = true;
                break;
            }
        }
        return b;
    }

    public void setLastVisited(List<String> lastVisited) {
        this.lastVisited = lastVisited;
        reGenerate();
    }

    public List<String> getLastVisited() {
        return lastVisited;
    }


    public class JMLV extends JMenuItem {
        private String path = null;

        public JMLV(String text, String pather, int kE) {
            setAccelerator(KeyStroke.getKeyStroke(kE, ActionEvent.CTRL_MASK));
            setIcon(loadImageAsIconImage("icons/Male.png"));
            setText(text);
            this.path = pather;
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Main.app.coreProcessFile(path);
                }
            });
        }
    }
}
