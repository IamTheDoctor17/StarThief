package com.thedoctor.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class Option {

    String option;

    int posX, posY;

    public Option(String option, int posX, int posY) {
        this.option = option;
        this.posX = posX;
        this.posY = posY;
    }

    public Option(String option, int posX, int posY, Font font) {
        this.option = option;
        this.posX = posX;
        this.posY = posY;
    }

    public void render(Graphics g){
        g.drawString(option, posX, posY);
    }
}
