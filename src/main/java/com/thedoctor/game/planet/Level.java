package com.thedoctor.game.planet;

import com.thedoctor.Util;
import com.thedoctor.game.ai.pathfinding.LogicMap;
import com.thedoctor.game.objects.Camera;
import com.thedoctor.game.objects.entity.Entity;
import com.thedoctor.game.objects.entity.EntityItem;
import com.thedoctor.gamestates.StateShip;
import com.thedoctor.loading.Registry;
import org.dom4j.Element;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class Level extends PlayableMap{

    public float playerStartX;
    public float playerStartY;

    Planet planet;

    String planetPath;
    String mapPath;
    Element element;

    public Level(Element e, String planetPath, Planet planet) {
        super();
        this.planetPath = planetPath;
        this.element = e;
        this.planet = planet;
        this.mapPath = element.attributeValue("mapurl");
        this.damageManager = new DamageManager(this);
    }

    public void init(StateBasedGame game, GameContainer gc) throws SlickException {
        this.gc = gc;
        this.game = game;
        this.map = new TiledMap(planetPath + mapPath);
        this.logicMap = new LogicMap(map);
        this.entities.add(Registry.player);
        this.playerStartX = Float.parseFloat(element.attributeValue("playerStartX"));
        this.playerStartY = Float.parseFloat(element.attributeValue("playerStartY"));
        Registry.player.setPlanet(this.planet);
        Registry.player.setMap(this.planet.getCurrentLevel().getMap());
        Registry.player.reloadTriggers();
        if (!planet.hasBeenPlayed()) {
            Registry.player.setPosX(this.playerStartX);
            Registry.player.setPosY(this.playerStartY);
        }
        for (Iterator dataTypeIter = element.elementIterator(); dataTypeIter.hasNext(); ) {
            Element dataType = (Element) dataTypeIter.next();
            switch (dataType.getName()) {
                case "entities": {
                    for (Iterator entitiesIter = dataType.elementIterator(); entitiesIter.hasNext(); ) {
                        Element entity = (Element) entitiesIter.next();
                        try {
                            entities.add((Entity) Registry.getEntityByID(entity.attributeValue("id")).getDeclaredConstructor(Element.class, Level.class, Planet.class).newInstance(entity, this, planet));
                        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                }
                case "items": {
                    for (Iterator itemsIter = dataType.elementIterator(); itemsIter.hasNext(); ) {
                        Element item = (Element) itemsIter.next();
                        items.add(new EntityItem(item));
                    }
                }
            }
        }
        this.camera = new Camera(Registry.player);
    }

    public void render(Graphics g){
        super.render(g);
    }

    public void update(int delta) throws SlickException {

        super.update(delta);

        Util.updateTriggers(Registry.player, this.map);

        for (String str : Registry.player.triggers) {
            switch (str) {
                case "nextLevel": {
                    planet.nextLevel();
                    break;
                }
                case "backShip": {
                    game.addState(new StateShip());
                    game.getState(StateShip.ID).init(gc, game);
                    game.enterState(StateShip.ID);
                    break;
                }
            }
        }
    }


    public String getMapPath() {
        return mapPath;
    }

    public Planet getPlanet() {
        return planet;
    }

}