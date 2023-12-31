package main.ui.menus;

import java.io.IOException;

import static main.ui.UI.println;

public class MainMenu extends Menu {

    @Override
    protected void handleSelection(int selection) throws InterruptedException {
        try {
            switch (selection) {
                case 1 -> new CharacterSelectionMenu().show();
                case 2 -> println("Work in Progress");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ========================================
    //  Constructors
    // ========================================

    public MainMenu() {
        super(new Banner("Mystic Dreams"), "Welcome to Mystic Dreams", MenuReturnType.EXIT, "Start", "Setting");
    }
}
