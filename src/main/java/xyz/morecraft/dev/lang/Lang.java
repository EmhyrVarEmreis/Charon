package xyz.morecraft.dev.lang;

public class Lang {

    private static int lang = 0;
    private static String[][] lang_array = {
            {
                    "Metal mine",
                    "Crystal mine",
                    "Deuterium extractor",

                    //3
                    "Metal",
                    "Crystal",
                    "Deuterium",
                    "Points",

                    //7
                    "Hourly:",
                    "Daily:",
                    "Weekly:",
                    "Monthly:",

                    //11
                    "Battle",
                    "Protect",
                    "Armour",
                    "Plasma",

                    //15
                    "Speeder",
                    "Percent",
                    "Level",

                    //18
                    "Ratio:"
            }
    };

    public void setLang(int l) {
        Lang.lang = l;
    }

    public static String getLang(int i) {
        return lang_array[lang][i];
    }


}
