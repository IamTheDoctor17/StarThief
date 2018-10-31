package com.thedoctor.game.objects.item;

import org.newdawn.slick.SlickException;

public abstract class ItemRangeWeapon extends Item{

    float range;

    public ItemRangeWeapon(String iconPath, float damage, int countdown, float range) throws SlickException {
        super(iconPath);
        this.damage = damage;
        this.countdown = countdown;
        this.range = range;
    }

    public float getRange() {
        return range;
    }
}
