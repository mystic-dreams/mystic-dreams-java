package main.ui.screens;

public abstract class Screen {
    protected final boolean skipPreviousScreen;

    abstract protected void handleScreen() throws InterruptedException;

    public boolean show() throws InterruptedException {
        handleScreen();
        return skipPreviousScreen;
    }

    protected Screen(boolean skipPreviousScreen) {
        this.skipPreviousScreen = skipPreviousScreen;
    }
}
