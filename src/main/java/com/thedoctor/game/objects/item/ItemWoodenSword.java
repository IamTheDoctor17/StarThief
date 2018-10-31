package com.thedoctor.game.objects.item;

import org.newdawn.slick.SlickException;

public class ItemWoodenSword extends Item{

    public static final int ID = 2;

    public ItemWoodenSword() throws SlickException {
        super("core/wooden_sword.png");
        this.maxStackSize = 1;
        this.damage = 3;
        this.countdown = 1000;
        this.knockback = 4;
    }

    @Override
    public int getID() {
        return ID;
    }
}
