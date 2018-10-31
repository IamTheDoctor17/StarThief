package com.thedoctor.game.objects.item;

import org.newdawn.slick.SlickException;

public class ItemCoin extends Item {

    public static final int ID = 1;

    public ItemCoin() throws SlickException {
        super("core/coin.png");
    }

    @Override
    public int getID() {
        return ID;
    }
}
