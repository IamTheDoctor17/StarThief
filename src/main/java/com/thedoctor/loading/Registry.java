package com.thedoctor.loading;

import com.thedoctor.game.objects.entity.Entity;
import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.game.save.GameSave;
import com.thedoctor.game.ship.Ship;
import com.thedoctor.game.objects.space.SpaceShip;
import org.newdawn.slick.SlickException;

import java.util.Hashtable;
import java.util.Map;

public class Registry {

    public static int score = 0;
    private static PlanetManager planets = new PlanetManager();
    private static ItemManager items = new ItemManager();
    private static EntityManager entities = new EntityManager();

    public static String currentPlanet = "null";

    public static int currentState;

    public static GameSave currentSave;
    public static Player player;
    public static Ship ship;
    public static SpaceShip spaceShip;

    public static void addItem(int id, Class item) {
        items.addItem(id, item);
    }

    public static Class getItemByID(int id) {
        return items.getItem(id);
    }

    public static Class getEntityByID(String id) {
        return entities.getEntity(id);
    }

    public static Planet getPlanet(String name) {
        return planets.getPlanet(name);
    }

    public static void addPlanet(Planet planet) {
        planets.addPlanet(planet);
    }

    public static void reloadPlanets() {
        planets.reloadPlanets();
    }

    public static Map<String, Planet> getPlanets() {
        return planets.getPlanets();
    }

    public static Map<String, Class> getEntities() {
        return entities.getEntities();
    }

    public static void addEntity(String id, Class<? extends Entity> entityClass) {
        entities.addEntity(id, entityClass);
    }
}

class PlanetManager {

    private Map<String, Planet> planets;

    PlanetManager() {
        this.planets = new Hashtable<>();
    }

    void reloadPlanets() {
        for (Planet p : planets.values()) {
            try {
                p.reload();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    void addPlanet(Planet planet) {
        this.planets.put(planet.getName(), planet);
    }

    Planet getPlanet(String name) {
        return planets.get(name);
    }

    Map<String, Planet> getPlanets() {
        return planets;
    }
}

class ItemManager {

    private Map<Integer, Class> items;

    public ItemManager() {
        this.items = new Hashtable<>();
    }

    void addItem(int id, Class item) {
        this.items.put(id, item);
    }

    Class getItem(int id) {
        return items.get(id);
    }
}

class EntityManager {

    private Map<String, Class> entities;

    public EntityManager() {
        this.entities = new Hashtable<>();
    }

    Map<String, Class> getEntities() {
        return entities;
    }

    void addEntity(String id, Class<? extends Entity> object) {
        entities.put(id, object);
    }

    Class getEntity(String id) {
        return entities.get(id);
    }
}