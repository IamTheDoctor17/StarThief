package com.thedoctor.game.objects.entity;

import com.thedoctor.game.objects.Object;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.game.planet.PlayableMap;
import org.dom4j.Element;

public abstract class Entity extends Object {

    protected Entity sender;
    boolean doesDamage;
    int damage;

    public Entity(float posX, float posY, int width, int height, PlayableMap level) {
        super(posX, posY, width, height, level);
        this.doesDamage = false;
    }

    public Entity(Element xmlElement, Level map, Planet planet, String name) {
        super(xmlElement, map, planet, name);
    }

    public abstract String getID();

    public boolean doesDamage() {
        return doesDamage;
    }

    public float getDamage() {
        return damage;
    }

    public Entity getSender() {
        return sender;
    }
}
