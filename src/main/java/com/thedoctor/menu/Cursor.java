package com.thedoctor.menu;

import com.thedoctor.Main;
import com.thedoctor.Util;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Cursor {

    Image image;

    int posX;
    int posY;

    boolean inTrigger;

    public Cursor(int posX, int posY) throws SlickException {
        this.image = new Image((Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/cursor.png");
        this.posX = posX;
        this.posY = posY;
    }

    public void render(Graphics g){
        if (inTrigger) {
            g.drawImage(image, posX, posY);
        }
    }

    public void update(int change){
        posY += change;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
