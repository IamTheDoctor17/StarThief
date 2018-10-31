package com.thedoctor.game.objects.graphics;

import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.game.objects.item.Item;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ItemHeldGraphic extends Graphic {

    Item item;
    Player player;

    public ItemHeldGraphic(Player object, Item item) {
        super(object);
        this.player = object;
        this.item = item;
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void render(Graphics g) {
        try {
            if (item != null) {
                g.drawImage(item.getIcon(), object.getX() + object.getHandPos().x, object.getY() + object.getHandPos().y);
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
