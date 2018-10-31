package com.thedoctor.game.objects.entity;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.ai.SimpleMobAI;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.game.planet.PlayableMap;
import com.thedoctor.loading.Registry;
import org.dom4j.Element;
import org.newdawn.slick.*;

public class SimpleEntity extends EntityLiving {

    public static final String ID = "simple_entity";

    SimpleMobAI ai;

    public SimpleEntity(float posX, float posY, PlayableMap level) throws SlickException {
        super(posX, posY, 64, 64, level);
        this.ai = new SimpleMobAI(this);
        this.health = 20;
        this.range = 100;
        this.speed = 0.15f;
        this.damage = 4;
        init();
    }

    public SimpleEntity(Element xmlElement, Level map, Planet planet) throws SlickException {
        super(xmlElement, map, planet, ID);
        this.ai = new SimpleMobAI(this);
        this.health = 20;
        this.range = 100;
        this.speed = 0.15f;
        init();
    }

    @Override
    public void update(int delta) throws SlickException {

        nextX = posX;
        nextY = posY;

        ai.update(delta);

        if (Collision.isNoCollision(tiledMap, nextX, nextY, width, height)) {
            posX = nextX;
            posY = nextY;
        }

        super.update(delta);
    }

    @Override
    public void render(Graphics g) {
        g.drawAnimation(animations[0], posX, posY);
        super.render(g);
    }

    @Override
    protected void loadAnimation() throws SlickException {
        this.textureLocation = (Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/simpleentity.png";
        SpriteSheet sheet = new SpriteSheet(new Image(textureLocation), 64, 64);
        animations = new Animation[1];
        animations[0] = Util.loadAnimation(sheet, 0, 1, 0, 100);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onDeath() {
        Registry.score ++;
    }
}
