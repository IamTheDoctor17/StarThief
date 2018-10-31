package com.thedoctor.loading;

import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class Fonts {

    public static TrueTypeFont MENU_FONT_30;
    public static TrueTypeFont MENU_FONT_16;

    public static void loadFonts() {
        MENU_FONT_30 = new TrueTypeFont(new Font("Dialog", Font.BOLD, 30), false);
        MENU_FONT_16 = new TrueTypeFont(new Font("Dialog", Font.BOLD, 16), false);
    }

    public static TrueTypeFont getMenuFont30() {
        return MENU_FONT_30;
    }

    public static TrueTypeFont getMenuFont16() {
        return MENU_FONT_16;
    }
}
