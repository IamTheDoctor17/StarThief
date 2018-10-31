package com.thedoctor.game.objects.graphics;

import com.thedoctor.game.objects.Object;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectGraphics {

    Object object;
    List<Graphic> graphics;

    public ObjectGraphics(Object object) {
        this.object = object;
        this.graphics = new ArrayList<>();
    }

    public void update(int delta) {
        for (Iterator<Graphic> iterator = graphics.iterator(); iterator.hasNext(); ) {
            Graphic graphic = iterator.next();
            graphic.update(delta);
            if (graphic.isDead()) {
                iterator.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (Graphic graphic : graphics) {
            graphic.render(g);
        }
    }

    public void addGraphic(Graphic graphic) {
        this.graphics.add(graphic);
    }

    public ItemHeldGraphic getItemHeldGraphic(){
        for (Graphic g : graphics){
            if (g instanceof ItemHeldGraphic) return (ItemHeldGraphic) g;
        }
        return null;
    }
}
