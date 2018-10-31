package com.thedoctor.game.planet;

import com.thedoctor.game.ai.pathfinding.LogicMap;
import com.thedoctor.game.objects.Camera;
import com.thedoctor.game.objects.entity.Entity;
import com.thedoctor.game.objects.entity.EntityItem;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class PlayableMap {

    protected GameContainer gc;
    protected StateBasedGame game;

    protected DamageManager damageManager;
    protected TiledMap map;
    protected LogicMap logicMap;
    public Camera camera;

    public List<Entity> entities;
    public List<EntityItem> items;

    public PlayableMap() {
        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void update(int delta) throws SlickException {
        this.camera.update(gc);
        this.updateEntities(delta);

        damageManager.update();
    }

    public void render(Graphics g){
        camera.render(gc, g);
        map.render(0, 0, map.getLayerIndex("main"));
        for (EntityItem e : items){
            e.render(g);
        }
        for (Entity e : entities) {
            e.render(g);
        }
    }

    void updateEntities(int delta) throws SlickException {
        for (Iterator<Entity> entityIterator = entities.iterator(); entityIterator.hasNext(); ) {
            Entity e = entityIterator.next();
            if (e.isDead()) {
                e.onDeath();
                entityIterator.remove();
            } else {
                e.update(delta);
                for (Entity f : entities) {
                    if (e != f) {
                        if (Collision.doesObjectsCollide(e, f) && !e.objectsCollided.contains(f)) {
                            e.objectsCollided.add(f);
                        } else e.objectsCollided.remove(f);
                    }
                }
            }

        }
        for (Iterator<EntityItem> iter = items.iterator(); iter.hasNext();){
            EntityItem item = iter.next();
            if (item.isDead()){
                iter.remove();
            }
            if (Collision.doesObjectsCollide(Registry.player, item)){
                Registry.player.objectsCollided.add(item);
                iter.remove();
            } else Registry.player.objectsCollided.remove(item);
        }
    }

    public void addEntity(Entity e) {
        e.setLevel(this);
        entities.add(e);
    }

    public void addItem(EntityItem item){
        item.setLevel(this);
        items.add(item);
    }

    public LogicMap getLogicMap() {
        return logicMap;
    }

    public TiledMap getMap() {
        return map;
    }

    public GameContainer getGc() {
        return gc;
    }
}
