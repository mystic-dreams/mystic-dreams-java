package main.ui.menus;

import main.exceptions.InvalidOptionException;
import main.ui.screens.Screen;
import main.utility.OptionsBuilder;

import static main.Messages.INVALID_SELECTION;
import static main.Messages.SELECTION_PROMPT;
import static main.ui.UI.*;

public abstract class Menu extends Screen {
    protected final Banner banner;
    protected final String prompt;
    protected String[] options;
    protected boolean skipPreviousScreen;

    public void handleScreen() throws InterruptedException {
        boolean escape = false;
        while (!escape) {
            if (banner != null) {
                println(banner.toString());
                newline();
            }

            if (prompt != null) {
                println(prompt);
            }

            displayOptions(options);
            newline();

            try {
                int selection = Integer.parseInt(getInput(SELECTION_PROMPT));
                newline();

                if (selection > options.length) {
                    throw new InvalidOptionException();
                }

                if (selection == options.length) {
                    // Returns true for breaking out of loop if last option is escape/exit, false if the last option is
                    // back. Useful for nested menus.
                    return false;
                }

                escape = handleSelection(selection);

            } catch (NumberFormatException | InvalidOptionException e) {
                notice(INVALID_SELECTION);
            } finally {
                newline();
            }
        }
        return skipPreviousScreen;
    }

    // Return true if do not want to show selection after execution
    protected abstract boolean handleSelection(int selection) throws InterruptedException;

    // ========================================
    //  Constructors
    // ========================================
    public Menu(Banner banner, String prompt, MenuReturnType returnType, boolean skipPreviousScreen,
                String... options) {
        super(skipPreviousScreen);
        this.banner = banner;
        this.prompt = prompt;
        this.options = new OptionsBuilder().addOptions(options).setReturnType(returnType).build();
    }

    public Menu(Banner banner, String prompt, boolean skipPreviousScreen, String... options) {
        this(banner, prompt, MenuReturnType.BACK, skipPreviousScreen, options);
    }
}
