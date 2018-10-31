package com.thedoctor.game.objects.graphics;

import com.thedoctor.game.objects.Object;
import org.newdawn.slick.Graphics;

public abstract class Graphic {

    float posX, posY;
    Object object;
    boolean isDead;

    public Graphic(Object object) {
        this.object = object;
        this.isDead = false;
        this.posX = 0;
        this.posY = 0;
    }

    public abstract void update(int delta);

    public abstract void render(Graphics g);

    public boolean isDead() {
        return isDead;
    }
}
