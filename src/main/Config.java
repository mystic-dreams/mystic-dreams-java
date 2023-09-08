package main;

import main.ui.LogLevel;

public class Config {
    public static final LogLevel LOG_LEVEL = LogLevel.DEBUG;
    public static final int DISPLAY_THROTTLE = 200; // In milliseconds
    public static final int NOTICE_DISPLAY_DELAY = 2 * DISPLAY_THROTTLE;

    public static final String DATA_FOLDER_PATH = "./data";
    public static final String CHARACTER_DATA_FILE_EXTENSION = ".character.dat";


}
