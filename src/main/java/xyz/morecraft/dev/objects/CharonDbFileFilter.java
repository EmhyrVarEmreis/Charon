package xyz.morecraft.dev.objects;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CharonDbFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        } else {
            return f.getName().toLowerCase().endsWith("." + Generator.EXTENSION_DB.toLowerCase());
        }
    }

    @Override
    public String getDescription() {
        return "Charon OGame DataBase Files (*." + Generator.EXTENSION_DB + ")";
    }

}
