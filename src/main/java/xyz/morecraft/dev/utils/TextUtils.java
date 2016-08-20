package xyz.morecraft.dev.utils;

import java.text.DecimalFormat;

public class TextUtils {

    static public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }

}
