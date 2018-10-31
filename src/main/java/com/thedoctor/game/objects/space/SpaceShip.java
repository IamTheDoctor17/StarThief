package com.thedoctor.game.objects.space;

import com.thedoctor.game.ai.ShipAI;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.game.planet.PlayableMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SpaceShip extends SpaceObject {

    ShipAI ai;

    public SpaceShip(float posX, float posY, int width, int height, PlayableMap level) {
        super(posX, posY, width, height, level);
        this.ai = new ShipAI(this);
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
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.green);
        g.fillRect(posX, posY, 200, 50);

        ai.render(g);

        super.render(g);
    }

    @Override
    protected void loadAnimation() throws SlickException {

    }

    public void clicked() {
        ai.clicked();
    }
}
