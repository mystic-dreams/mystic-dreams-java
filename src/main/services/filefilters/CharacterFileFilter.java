package main.services.filefilters;

import java.io.File;
import java.io.FilenameFilter;

public class CharacterFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File file, String filename) {
        // Character data filename format will be <Character name>.character.dat
        return filename.split("\\.").length == 3 && filename.endsWith(".character.dat");
    }
}
