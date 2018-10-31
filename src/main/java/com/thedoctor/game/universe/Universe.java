package com.thedoctor.game.universe;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.ai.pathfinding.LogicMap;
import com.thedoctor.game.objects.Camera;
import com.thedoctor.game.objects.space.SpaceObject;
import com.thedoctor.game.objects.space.SpaceShip;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.game.planet.PlayableMap;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Universe extends PlayableMap {

    List<SpaceObject> entities;

    public Universe(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        super();
        this.gc = gameContainer;
        this.game = game;
        this.map = new TiledMap(((Main.isTest ? Util.RESOURCES_PATH_TEST : Util.RESOURCES_PATH)) + "core/test.tmx");

        Registry.spaceShip = new SpaceShip(0, 0, 200, 50, this);
        this.entities = new ArrayList<>();
        this.addEntity(Registry.spaceShip);
        this.camera = new Camera(Registry.spaceShip);
        this.logicMap = new LogicMap(map);
    }

    public void update(int delta) throws SlickException {
        camera.update(gc);
        updateEntities(delta);
    }

    public void render(Graphics g) {
        camera.render(gc, g);
        map.render(0, 0);

        for (SpaceObject entity : entities) {
            entity.render(g);
        }
    }


    void updateEntities(int delta) throws SlickException {
        for (Iterator<SpaceObject> entityIterator = entities.iterator(); entityIterator.hasNext(); ) {
            SpaceObject e = entityIterator.next();
            if (e.isDead()) {
                e.onDeath();
                entityIterator.remove();
            } else {
                e.update(delta);
                for (SpaceObject f : entities) {
                    if (e != f) {
                        if (Collision.doesObjectsCollide(e, f) && !e.objectsCollided.contains(f)) {
                            e.objectsCollided.add(f);
                        } else e.objectsCollided.remove(f);
                    }
                }
            }

        }
    }

    public static void preInit() {
    }

    public void addEntity(SpaceObject e) {
        e.setLevel(this);
        this.entities.add(e);
    }

    public void clicked(){
        Registry.spaceShip.clicked();
    }
}
