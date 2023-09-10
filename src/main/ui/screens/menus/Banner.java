package main.ui.screens.menus;

import java.util.Arrays;

public class Banner {
    private static final int spacesPerCharacter = 7;

    // We'll map letter 'A' to index 1. Index 0 will be space.
    private static final String[] alphabetTemplateLine1 = {"       ", "  ###  ", "###### ", " ##### ", "###### ", "#######", "#######", " ##### ", "#     #", "#######", "#######", "#     #", "#      ", "##   ##", "##    #", " ##### ", "###### ", " ##### ", "###### ", " ##### ", "#######", "#     #", "#     #", "#  #  #", "#     #", "#     #", "#######"};
    private static final String[] alphabetTemplateLine2 = {"       ", " #   # ", "#     #", "#     #", "#     #", "#      ", "#      ", "#     #", "#     #", "   #   ", "   #   ", "#   #  ", "#      ", "# # # #", "# #   #", "#     #", "#     #", "#     #", "#     #", "#      ", "   #   ", "#     #", "#     #", "#  #  #", " #   # ", " #   # ", "     # "};
    private static final String[] alphabetTemplateLine3 = {"       ", "#######", "###### ", "#      ", "#     #", "#######", "#######", "#      ", "#######", "   #   ", "   #   ", "# #    ", "#      ", "#  #  #", "#  #  #", "#     #", "###### ", "#   # #", "###### ", " ##### ", "   #   ", "#     #", " #   # ", "#  #  #", "  ###  ", "  # #  ", "   #   "};
    private static final String[] alphabetTemplateLine4 = {"       ", "#     #", "#     #", "#     #", "#     #", "#      ", "#      ", "#   ###", "#     #", "   #   ", "#  #   ", "#   #  ", "#      ", "#     #", "#   # #", "#     #", "#      ", "#    # ", "#   #  ", "      #", "   #   ", "#     #", "  # #  ", "#  #  #", " #   # ", "   #   ", " #     "};
    private static final String[] alphabetTemplateLine5 = {"       ", "#     #", "###### ", " ##### ", "###### ", "#######", "#      ", " ##### ", "#     #", "#######", " ##    ", "#     #", "#######", "#     #", "#    ##", " ##### ", "#      ", " #### #", "#     #", " ##### ", "   #   ", " ##### ", "   #   ", " ## ## ", "#     #", "   #   ", "#######"};

    private final StringBuilder titleLine1 = new StringBuilder(alphabetTemplateLine1[0]);
    private final StringBuilder titleLine2 = new StringBuilder(alphabetTemplateLine2[0]);
    private final StringBuilder titleLine3 = new StringBuilder(alphabetTemplateLine3[0]);
    private final StringBuilder titleLine4 = new StringBuilder(alphabetTemplateLine4[0]);
    private final StringBuilder titleLine5 = new StringBuilder(alphabetTemplateLine5[0]);

    public Banner(String title) {
        for (Character c : title.toUpperCase().toCharArray()) {
            int index = c == ' ' ? 0 : c - 64;
            titleLine1.append(alphabetTemplateLine1[index]).append(" ");
            titleLine2.append(alphabetTemplateLine2[index]).append(" ");
            titleLine3.append(alphabetTemplateLine3[index]).append(" ");
            titleLine4.append(alphabetTemplateLine4[index]).append(" ");
            titleLine5.append(alphabetTemplateLine5[index]).append(" ");
        }
    }

    @Override
    public String toString() {
        char[] longStreamer = new char[titleLine1.length() + spacesPerCharacter];
        Arrays.fill(longStreamer, '*');

        return new String(longStreamer) + "\n"
                + new String(longStreamer) + "\n"
                + titleLine1 + "\n"
                + titleLine2 + "\n"
                + titleLine3 + "\n"
                + titleLine4 + "\n"
                + titleLine5 + "\n"
                + new String(longStreamer) + "\n"
                + new String(longStreamer)
                ;
    }
}
