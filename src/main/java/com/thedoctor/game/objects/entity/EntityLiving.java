package com.thedoctor.game.objects.entity;

import com.thedoctor.game.objects.item.Item;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.game.planet.PlayableMap;
import org.dom4j.Element;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityLiving extends Entity{

    int direction;

    boolean isMoving;
    
    Item heldItem;

    float health;
    float speed;
    float damage;
    float range;

    public List<String> triggers;

    private boolean canHit;
    boolean isHitting;

    int nullCountdown;

    int invincibilityBuffer = 0;
    boolean isInvincible = false;

    public EntityLiving(float posX, float posY, int width, int height, PlayableMap level) {
        super(posX, posY, width, height, level);
        this.triggers = new ArrayList<>();
        this.speed = 1;
        this.nullCountdown = 0;
    }

    public EntityLiving(Element xmlElement, Level map, Planet planet, String name) {
        super(xmlElement, map, planet, name);
        this.triggers = new ArrayList<>();
        this.speed = 1;
        this.nullCountdown = 0;
    }

    @Override
    public void update(int delta) throws SlickException {

        if (health <= 0) isDead = true;

        if (heldItem == null) {
            if (nullCountdown >= 1000){
                this.canHit = true;
            } else {
                nullCountdown += delta;
                this.canHit = false;
            }
        }

        if (isInvincible){
            invincibilityBuffer += delta;
            if (invincibilityBuffer >= 1000){
                isInvincible = false;
                invincibilityBuffer = 0;
            }
        }

        super.update(delta);
    }

    public void reloadTriggers(){
        this.triggers = new ArrayList<>();
    }


    public void setDamage(float damage){
        this.health -= damage;
    }

    public float getDamage(){
        return (heldItem == null ? damage : heldItem.getDamage());
    }

    public float getRange() {
        return range;
    }

    public float getHealth() {
        return health;
    }

    public float getSpeed() {
        return this.speed;
    }

    public Item getHeldItem() {
        return heldItem;
    }

    public boolean canHit(){
        if (heldItem == null) {
            if (canHit) {
                canHit = false;
                return true;
            }
            return false;
        } else return heldItem.isReady();
    }

    public boolean isHitting() {
        return isHitting;
    }

    public void reloadNullCountdown(){
        this.nullCountdown = 0;
    }

    public int getBufferCountdown(){
        return (heldItem == null ? nullCountdown : heldItem.getBufferCountdown());
    }

    public int getCountdown(){
        return (heldItem == null ? 1000 : heldItem.getCountdown());
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public void setHitting(boolean hitting) {
        isHitting = hitting;
    }

    public void startHitting(){
        isHitting = true;
    }
}
