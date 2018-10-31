package com.thedoctor.game.objects.graphics;

import com.thedoctor.game.objects.Object;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class DamageGraphic extends Graphic{

    int age;
    String value;

    public DamageGraphic(Object object, String value) {
        super(object);
        this.age = 0;
        this.value = value;
    }

    @Override
    public void update(int delta) {
        this.posY += delta * 0.01;
        this.age += delta;
        if (this.age > 3000) isDead = true;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawString(value, object.getX() + this.posX, object.getY() + this.posY);
    }
}
