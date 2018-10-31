package com.thedoctor.game.objects.entity;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.game.planet.PlayableMap;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

public class Bullet extends Entity{

    public static final String ID = "bullet";
    float direction, x, y;
    int age;

    public Bullet(float posX, float posY, PlayableMap level, Entity sender) throws SlickException {
        super(posX, posY, 64, 64, level);
        this.direction = Util.getRadianAngle(posX - level.camera.getxCamera() + level.getGc().getWidth() / 2, Mouse.getX(), posY - level.camera.getyCamera() + level.getGc().getHeight() / 2, 720 - Mouse.getY());
        this.x = (float) (Math.cos(direction) * 5);
        this.y = (float) (Math.sin(direction) * 5);
        this.doesDamage = true;
        this.damage = 2;
        this.sender = sender;
        this.age = 0;
        init();
    }

    @Override
    public void render(Graphics g) {
        g.drawAnimation(animations[0], posX, posY);
        super.render(g);
    }

    @Override
    public void update(int delta) throws SlickException {

        age += delta;
        if (age > 3000) this.isDead = true;

        nextX = posX;
        nextY = posY;

        nextX += x;
        nextY += y;

        if (Collision.isNoCollision(tiledMap, nextX, nextY, width, height)) {
            posX = nextX;
            posY = nextY;
        }

        super.update(delta);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    protected void loadAnimation() throws SlickException {
        this.textureLocation = (Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/bullet.png";
        SpriteSheet sheet = new SpriteSheet(new Image(textureLocation), 30, 30);
        animations = new Animation[1];
        animations[0] = Util.loadAnimation(sheet, 0, 1, 0, 100);
    }
}
