package main;

import main.ui.LogLevel;

public class Config {
    // ========================================
    //  Application
    // ========================================
    public static final LogLevel LOG_LEVEL = LogLevel.DEBUG;

    // ========================================
    //  UI
    // ========================================
    public static final int DISPLAY_THROTTLE = 100; // In milliseconds
    public static final int NOTICE_DISPLAY_DELAY = 2 * DISPLAY_THROTTLE;

    // ========================================
    //  Gameplay
    // ========================================
    public static final int STATS_PER_ATT = 5;
    public static final int DMG_PER_ATT = 5;

    public static final int AP_PER_LEVEL = 5;
    public static final int SP_PER_LEVEL = 3;

    public static int getExpRequirement(int level) {
        return (int) Math.pow(level, 2) * 10;
    }

    public static int getHPIncrementOnLevel(int str, int currentHP) {
        return HP_PER_STR * str + Math.round(currentHP * 0.05f);
    }

    public static int getMPIncrementOnLevel(int wis, int currentHP) {
        return MP_PER_WIS * wis + Math.round(currentHP * 0.05f);
    }

    private static final int HP_PER_STR = 10;
    private static final int MP_PER_WIS = 10;

    // ========================================
    //  Data
    // ========================================
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String CHARACTER_DATA_FILE_EXTENSION = ".character.dat";
    public static final String SKILL_DATA_FILE_EXTENSION = ".skill.dat";


}
