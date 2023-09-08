package main;

import main.ui.Logger;
import main.ui.menus.MainMenu;

import java.io.IOException;

public class MysticDreams {
    public static void main(String[] arg) throws InterruptedException, IOException {
        Logger.setLogLevel(Config.LOG_LEVEL);
        new MainMenu().show();
    }
}
