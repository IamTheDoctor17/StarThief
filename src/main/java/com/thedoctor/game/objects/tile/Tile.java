package com.thedoctor.game.objects.tile;

import com.thedoctor.game.objects.Object;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import org.dom4j.Element;

public abstract class Tile extends Object {

    int mapX, mapY;

    public Tile(Element xmlElement, Level map, Planet planet, String name, int mapX, int mapY) {
        super(xmlElement, map, planet, name);

        this.mapX = mapX;
        this.mapY = mapY;
    }
}
