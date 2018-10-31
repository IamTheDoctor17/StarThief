package com.thedoctor.game.objects.item;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.objects.entity.EntityItem;
import com.thedoctor.game.objects.entity.EntityLiving;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Item {

    String iconPath;
    EntityItem entity;

    int maxStackSize;

    float damage;
    int countdown, bufferCountdown;
    int knockback;

    boolean isReady;

    public Item(String iconPath) throws SlickException {
        this.iconPath = (Main.isTest ? "core/" : Util.RESOURCES_PATH) + iconPath;
        this.entity = new EntityItem(this);
        this.maxStackSize = 16;
        this.damage = 1;
        this.bufferCountdown = 0;
        this.countdown = 0;
        this.knockback = 3;
    }

    public void update(int delta){
        if (bufferCountdown >= countdown){
            isReady = true;
        } else {
            bufferCountdown += delta;
            isReady = false;
        }
    }

    public Image getIcon() throws SlickException {
        return new Image(iconPath);
    }

    public abstract int getID();

    public EntityItem getEntity() {
        return entity;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public float getDamage() {
        return damage;
    }

    public boolean isReady() {
        return isReady;
    }

    public void shoot(EntityLiving sender) throws SlickException {

    }

    public void reload(){
        bufferCountdown = 0;
    }

    public int getBufferCountdown() {
        return bufferCountdown;
    }

    public int getCountdown() {
        return countdown;
    }

    public float getKnockback() {
        return knockback;
    }
}
