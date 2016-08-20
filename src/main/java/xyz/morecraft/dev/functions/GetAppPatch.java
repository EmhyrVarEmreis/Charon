package xyz.morecraft.dev.functions;

public class GetAppPatch {

    public static String getAppDataRoaming() {
        return System.getProperty("user.home") + "\\AppData\\Roaming";
    }

}
