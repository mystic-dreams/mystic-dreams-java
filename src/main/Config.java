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

    // ========================================
    //  Data
    // ========================================
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String CHARACTER_DATA_FILE_EXTENSION = ".character.dat";
    public static final String SKILL_DATA_FILE_EXTENSION = ".skill.dat";


}
