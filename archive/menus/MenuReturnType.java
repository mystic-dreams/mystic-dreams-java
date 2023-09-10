package main.ui.menus;

public enum MenuReturnType {
    BACK("Back"),
    EXIT("Exit"),
    ESCAPE("Escape"); // Exclusive for combat menu

    public final String value;

    MenuReturnType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
