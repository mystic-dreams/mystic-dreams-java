package main.ui.screens;

public abstract class Screen {
    protected boolean skipPreviousScreen;

    abstract protected void handleScreenLogic() throws InterruptedException;

    public boolean show() throws InterruptedException {
        handleScreenLogic();
        return skipPreviousScreen;
    }

    // ========================================
    //  Constructor
    // ========================================

    protected Screen(boolean skipPreviousScreen) {
        this.skipPreviousScreen = skipPreviousScreen;
    }
}
