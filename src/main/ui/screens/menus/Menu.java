package main.ui.screens.menus;

import main.exceptions.InvalidOptionException;
import main.ui.screens.Screen;
import main.utility.OptionsBuilder;
import org.jetbrains.annotations.NotNull;

import static main.Messages.*;
import static main.ui.UI.*;

public abstract class Menu extends Screen {
    protected final Banner banner;
    protected final String prompt;
    protected final String[] options;

    /**
     * Handle accordingly to the options that is being selected
     *
     * @param selection index of the chosen options (from display, not array)
     * @return true if screen should be terminated on completion. false if otherwise
     */
    abstract boolean handleSelection(int selection) throws InterruptedException;

    @Override
    protected void handleScreenLogic() throws InterruptedException {
        boolean exitFromScreen = false;
        while (!exitFromScreen) {
            if (banner != null) {
                displayBanner();
            }

            println(prompt);
            displayOptions(options);

            try {
                // Get selection
                String input = getInput(SELECTION_PROMPT);
                int selection = Integer.parseInt(input);

                // Validate selection
                // Note: Selection is 1-indexed.
                if (selection < 1 || selection > options.length) {
                    throw new InvalidOptionException();
                }
                newline();

                // Handle selection
                if (isLastOption(selection)) {
                    if (getReturnOption().equals(MenuReturnType.ESCAPE.value)) {
                        skipPreviousScreen = true;
                        println(ESCAPE_MESSAGE);
                    }
                    exitFromScreen = true;
                } else {
                    exitFromScreen = handleSelection(selection);
                }
            } catch (InvalidOptionException e) {
                notice(INVALID_SELECTION_MESSAGE);
                // Will start loop again
            } finally {
                if (!skipPreviousScreen) { // Previous screen will have its newline. This prevents double newlines.
                    newline();
                }
            }
        }
    }

    // ========================================
    //  Helper
    // ========================================
    private void displayBanner() throws InterruptedException {
        println(banner.toString());
        newline();
    }

    private boolean isLastOption(int selection) {
        return selection == options.length;
    }

    private String getReturnOption() {
        return options[options.length - 1];
    }

    // ========================================
    //  Constructor
    // ========================================
    protected Menu(Banner banner, @NotNull String prompt, MenuReturnType returnType, boolean skipPreviousScreen, @NotNull String... options) {
        super(skipPreviousScreen);
        this.banner = banner;
        this.prompt = prompt;
        this.options = new OptionsBuilder().addOptions(options).setReturnType(returnType).build();
    }

    protected Menu(String prompt, MenuReturnType returnType, boolean skipPreviousScreen, String... options) {
        this(null, prompt, returnType, skipPreviousScreen, options);
    }

    protected Menu(String prompt, boolean skipPreviousScreen, String... options) {
        this(null, prompt, MenuReturnType.BACK, skipPreviousScreen, options);
    }

    protected Menu(String prompt, MenuReturnType returnType, String... options) {
        this(null, prompt, returnType, false, options);
    }

    protected Menu(String prompt, String... options) {
        this(null, prompt, MenuReturnType.BACK, false, options);
    }
}
