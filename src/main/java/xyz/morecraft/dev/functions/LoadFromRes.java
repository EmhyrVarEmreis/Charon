package xyz.morecraft.dev.functions;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class LoadFromRes {

    public static Image loadImageAsImage(String path) {
        Image ret = null;
        try {
            //System.out.println("/resources/img/" + path);
            ret = Toolkit.getDefaultToolkit().createImage(LoadFromRes.class.getClass().getResource("/img/" + path));
        } catch (Exception e) {
            // TODO Exception
        }
        return ret;
    }

    public static InputStream loadAsInputStream(String path) {
        InputStream ret = null;
        try {
            //System.out.println("/resources/" + path);
            ret = LoadFromRes.class.getResourceAsStream("/" + path);
        } catch (Exception e) {
            // TODO Exception
        }
        return ret;
    }

    public static InputStream loadImageAsInputStream(String path) {
        InputStream ret = null;
        try {
            //System.out.println("/resources/img/" + path);
            ret = LoadFromRes.class.getResourceAsStream("/img/" + path);
        } catch (Exception e) {
            // TODO Exception
        }
        return ret;
    }

    public static ImageIcon loadImageAsIconImage(String path) {
        ImageIcon ret = null;
        try {
            ret = new ImageIcon(loadImageAsImage(path));
        } catch (Exception e) {
            // TODO Exception
        }
        return ret;
    }

}
