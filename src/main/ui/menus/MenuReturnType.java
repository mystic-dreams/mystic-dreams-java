package main.ui.menus;

public enum MenuReturnType {
    BACK("Back"),
    EXIT("Exit");

    private final String option;

    MenuReturnType(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    }
}
