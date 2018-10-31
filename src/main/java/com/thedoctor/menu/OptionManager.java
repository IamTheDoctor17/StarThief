package com.thedoctor.menu;

import com.thedoctor.Util;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class OptionManager {

    int startX, startY;
    int widthOption, heightOption;

    int gap;
    Cursor cursor;
    List<Option> options;

    int selected;

    public OptionManager(int startX, int startY, int gap, int widthOption, int heightOption) throws SlickException {
        this.startX = startX;
        this.startY = startY;
        this.widthOption = widthOption;
        this.heightOption = heightOption;
        this.gap = gap;
        this.cursor = new Cursor(startX, startY);
        this.options = new ArrayList<>();
        this.selected = -1;
    }

    public void addOption(String option) {
        options.add(new Option(option, startX + 40, startY + gap + options.size() * heightOption));
    }

    public void update() {
        boolean trigger = false;
        for (Option o : options) {
            if (Util.pointInTrigger(Mouse.getX(), 720 - Mouse.getY(), o.posX, o.posY, widthOption, heightOption)) {
                cursor.posX = o.posX - 40;
                cursor.posY = o.posY + 10;
                trigger = true;
                selected = options.indexOf(o);
            }
        }
        cursor.inTrigger = trigger;
        if (!trigger) selected = -1;
    }

    public void render(Graphics g) {
        for (Option o : options) {
            o.render(g);
        }
        cursor.render(g);
    }
}
