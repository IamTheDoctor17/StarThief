package com.thedoctor.game.objects.item;

import org.newdawn.slick.SlickException;

public class ItemHealingCoin extends Item{

    public static final int ID = 3;

    public ItemHealingCoin() throws SlickException {
        super("core/healing_coin.png");
    }

    @Override
    public int getID() {
        return ID;
    }
}
