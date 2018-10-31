package com.thedoctor.game.objects.space;

import com.thedoctor.game.objects.Object;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.game.planet.PlayableMap;
import org.dom4j.Element;
import org.newdawn.slick.SlickException;

public abstract class SpaceObject extends Object {

    public SpaceObject(float posX, float posY, int width, int height, PlayableMap level) {
        super(posX, posY, width, height, level);
    }

    public SpaceObject(Element xmlElement, Level level, Planet planet, String name) {
        super(xmlElement, level, planet, name);
    }
}
