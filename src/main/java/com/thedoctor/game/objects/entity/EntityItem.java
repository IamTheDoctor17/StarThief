package com.thedoctor.game.objects.entity;

import com.thedoctor.game.objects.item.Item;
import com.thedoctor.loading.Registry;
import org.dom4j.Element;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EntityItem extends Entity {

    public static final String ID = "item";

    Item item;

    public EntityItem(Item item, float posX, float posY) throws SlickException {
        super(null, null, null, ID);
        this.item = item;
        this.posX = posX;
        this.posY = posY;
        init();
    }

    public EntityItem(Item item) throws SlickException {
        super(null, null, null, ID);
        this.item = item;
        init();
    }

    public EntityItem(Element element) throws SlickException {
        super(element, null, null, ID + "_" + element.attributeValue("id"));
        try {
            this.item = (Item) Registry.getItemByID(Integer.parseInt(element.attributeValue("id"))).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        init();
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[0].getCurrentFrame(), this.posX, this.posY);
        super.render(g);
    }

    @Override
    protected void loadAnimation() throws SlickException {
        this.animations = new Animation[1];
        this.animations[0] = new Animation();
        this.animations[0].addFrame(this.item.getIcon(), 200);
    }

    public Item getItem() {
        return item;
    }
}
