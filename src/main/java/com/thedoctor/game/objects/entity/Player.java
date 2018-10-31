package com.thedoctor.game.objects.entity;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.hud.Inventory;
import com.thedoctor.game.objects.Object;
import com.thedoctor.game.objects.graphics.DamageGraphic;
import com.thedoctor.game.objects.graphics.ItemHeldGraphic;
import com.thedoctor.game.objects.item.ItemHealingCoin;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.game.planet.Planet;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import java.util.Iterator;

public class Player extends EntityLiving {

    public static final String ID = "player";
    public static final float MAX_HEALTH = 20;
    public static final float BASIC_DAMAGE = 2;

    public Inventory inventory;


    public Player() throws SlickException {
        super(null, null, null, "Player");
        animations = new Animation[5];
        this.inventory = new Inventory(10, 1, this);
        this.posX = 0;
        this.posY = 0;
        this.width = 64;
        this.height = 64;
        this.textureLocation = "core/player.png";
        this.handPos = new Vector2f(32, 32);

        this.speed = 1;
        this.health = MAX_HEALTH;
        this.damage = BASIC_DAMAGE;
        this.range = 200;

        graphics.addGraphic(new ItemHeldGraphic(this, null));

        loadAnimation();
    }

    @Override
    public void update(int delta) throws SlickException {


        if (inventory.getCurrentItemStack() != null) {
            this.heldItem = inventory.getCurrentItemStack().getItem();
            inventory.getCurrentItemStack().update(delta);
            this.graphics.getItemHeldGraphic().setItem(this.heldItem);
        } else this.heldItem = null;

        nextX = posX;
        nextY = posY;
        isMoving = false;

        if (Keyboard.isKeyDown(Util.KEY_LEFT)) {
            nextX -= delta * 0.3 * speed;
            direction = 1;
            isMoving = true;
        }
        if (Keyboard.isKeyDown(Util.KEY_RIGHT)) {
            nextX += delta * 0.3 * speed;
            direction = 0;
            isMoving = true;
        }
        if (Keyboard.isKeyDown(Util.KEY_DOWN)) {
            nextY += delta * 0.3 * speed;
            isMoving = true;
        }
        if (Keyboard.isKeyDown(Util.KEY_UP)) {
            nextY -= delta * 0.3 * speed;
            isMoving = true;
        }

        if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (canHit()) {
                isHitting = true;
                if (heldItem != null) heldItem.reload();
                else reloadNullCountdown();
            } else isHitting = false;
        } else {
            isHitting = false;
        }

        for (Iterator<Object> iter = objectsCollided.iterator(); iter.hasNext();) {
            Object o = iter.next();
            if (!inventory.isFull()) {
                if (o instanceof EntityItem) {
                    iter.remove();
                    if (((EntityItem) o).getItem() instanceof ItemHealingCoin){
                        this.health += 5;
                        float change = 5;
                        if (health > 20){
                            change = health - 20;
                            health = 20;
                        }
                        this.graphics.addGraphic(new DamageGraphic(this, "+" + change));
                    } else {
                        inventory.addItem(((EntityItem) o).getItem().getID());
                    }
                }
            }
        }

        if (Collision.isNoCollision(tiledMap, nextX, nextY, width, height)) {
            posX = nextX;
            posY = nextY;
        }

        super.update(delta);
    }

    @Override
    public void render(Graphics g) {
        if (isHitting){
            g.drawAnimation(animations[4] , ((int) posX), ((int) posY));
            if (animations[4].getFrame() == animations[4].getFrameCount()){
                isHitting = false;
            }
        } else {
            g.drawAnimation(animations[direction + (isMoving ? 2 : 0)], ((int) posX), ((int) posY));
        }
        super.render(g);
    }

    @Override
    protected void loadAnimation() throws SlickException {
        this.textureLocation = (Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/player.png";
        SpriteSheet sheet = new SpriteSheet(new Image(textureLocation), 64, 64);
        animations[0] = Util.loadAnimation(sheet, 0, 1, 0, 200);
        animations[1] = Util.loadAnimation(sheet, 0, 1, 1, 200);
        animations[2] = Util.loadAnimation(sheet, 1, 3, 0, 200);
        animations[3] = Util.loadAnimation(sheet, 1, 3, 1, 200);
        animations[4] = Util.loadAnimation(sheet, 0, 3, 2, 200);
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public void setMap(TiledMap map) {
        this.tiledMap = map;
    }

    @Override
    public String getID() {
        return ID;
    }

    public float getHealth() {
        return health;
    }
}