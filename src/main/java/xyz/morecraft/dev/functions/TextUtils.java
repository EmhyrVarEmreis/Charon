package xyz.morecraft.dev.functions;

import xyz.morecraft.dev.objects.Generator;

public class TextUtils {

    public static String processFileNameToSave(String s) {
        String ret;
        if (s.toLowerCase().endsWith("." + Generator.EXTENSION_DB.toLowerCase())) {
            ret = s;
        } else {
            ret = s + "." + Generator.EXTENSION_DB;
        }
        return ret;
    }

}
