package main.utility;

import main.ui.screens.menus.MenuReturnType;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsBuilder {
    private final ArrayList<String> options = new ArrayList<>();
    private MenuReturnType returnType = MenuReturnType.BACK; // Defaults to Back

    public OptionsBuilder addOptions(String[] options) {
        this.options.addAll(Arrays.asList(options));
        return this;
    }

    public OptionsBuilder setReturnType(MenuReturnType returnType) {
        this.returnType = returnType;
        return this;
    }

    public String[] build() {
        this.options.add(returnType.toString());
        return this.options.toArray(new String[]{});
    }

}
