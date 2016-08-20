package xyz.morecraft.dev.utils;

import java.io.File;

public class FileUtils {

    public static boolean DeleteFile(File file) {
        boolean b = false;
        try {
            if (file.isDirectory()) {
                if (file.list().length == 0) {
                    if (file.delete()) {
                        b = true;
                    } else {
                        b = false;
                    }
                } else {
                    String files[] = file.list();
                    for (String temp : files) {
                        File fileDelete = new File(file, temp);
                        DeleteFile(fileDelete);
                    }
                    if (file.list().length == 0) {
                        if (file.delete()) {
                            b = true;
                        }
                    }
                }

            } else {

                if (file.delete()) {
                    b = true;
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return b;
    }

    public static boolean DeleteFile(String path) {
        File file = new File(path);
        return DeleteFile(file);
    }

    public static boolean makeDirectory(String path) {
        boolean bol = false;
        File theDir = new File(path);
        if (!theDir.exists()) {
            if (theDir.mkdir()) {
                bol = true;
            }
        }
        return bol;
    }

}
