package main.ui.menus;

import main.exceptions.InvalidOptionException;
import main.utility.OptionsBuilder;

import static main.ui.UI.*;

public abstract class Menu {
    protected final Banner banner;
    protected final String prompt;
    protected String[] options;

    public void show() throws InterruptedException {
        while (true) {
            if (banner != null) {
                println(banner.toString());
                newline();
            }

            if (prompt != null) {
                println(prompt);
            }

            displayOptions();
            newline();

            try {
                int selection = Integer.parseInt(getInput("Selection:"));
                newline();

                if (selection > options.length) {
                    throw new InvalidOptionException();
                }

                if (selection == options.length) {
                    return;
                }

                handleSelection(selection);

            } catch (NumberFormatException | InvalidOptionException e) {
                notice("Invalid Input");
            } finally {
                newline();
            }
        }
    }

    protected abstract void handleSelection(int selection) throws InterruptedException;

    // ========================================
    //  Helper
    // ========================================

    protected void displayOptions() throws InterruptedException {
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            println((i + 1) + ". " + option);
        }
    }

    // ========================================
    //  Constructors
    // ========================================
    public Menu(Banner banner, String prompt, MenuReturnType returnType, String... options) {
        this.banner = banner;
        this.prompt = prompt;
        this.options = new OptionsBuilder().addOptions(options).setReturnType(returnType).build();
    }

    public Menu(Banner banner, String prompt, String... options) {
        this(banner, prompt, MenuReturnType.BACK, options);
    }
}
