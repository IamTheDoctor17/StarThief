package com.thedoctor.game.hud.planetmap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Planet {

    private int width, height;
    Color color;
    String name;

    public Planet(String name, Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void render(Graphics g, int startX, int startY){
        g.setColor(color);
        g.fillRect(startX, startY, width, height);
        g.setColor(Color.white);
        g.drawString(name, startX  +  (width / 2 - name.length() * 5 ), startY + height + 30);
    }

    public String getName() {
        return name;
    }
}
