package com.thedoctor.game.objects;

import com.thedoctor.game.objects.graphics.Graphic;
import com.thedoctor.game.objects.graphics.ObjectGraphics;
import com.thedoctor.game.physics.Collision;
import com.thedoctor.game.physics.Vector;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.game.planet.PlayableMap;
import org.dom4j.Element;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Object {

    protected float posX, posY;
    protected float nextX, nextY;
    protected int width, height;

    protected boolean isDead;

    protected List<Vector> vectorList;
    protected Animation[] animations;
    protected int currentAnim;
    protected Vector2f handPos;
    protected ObjectGraphics graphics;

    protected String textureLocation;
    protected Element xmlElement;

    protected PlayableMap level;
    protected TiledMap tiledMap;
    protected Planet planet;

    public List<Object> objectsCollided;

    protected String name;

    public Object(float posX, float posY, int width, int height, PlayableMap level) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.level = level;
        this.tiledMap = level.getMap();
        this.graphics = new ObjectGraphics(this);
        this.vectorList = new ArrayList<>();
        this.objectsCollided = new ArrayList<>();
        this.handPos = null;
    }

    public Object(Element xmlElement, Level level, Planet planet, String name) {
        this.xmlElement = xmlElement;
        this.vectorList = new ArrayList<>();
        this.graphics = new ObjectGraphics(this);
        this.level = level;
        if (level != null) this.tiledMap = level.getMap();
        this.objectsCollided = new ArrayList<>();
        this.planet = planet;
        this.name = name;
        this.handPos = null;
    }

    public void init() throws SlickException {
        if (xmlElement != null) {
            for (Iterator datasIter = xmlElement.elementIterator(); datasIter.hasNext(); ) {
                Element data = (Element) datasIter.next();
                switch (data.getName()) {
                    case "data":
                        for (Iterator valuesIter = data.elementIterator(); valuesIter.hasNext(); ) {
                            Element value = (Element) valuesIter.next();
                            switch (value.getName()) {
                                case "posx":
                                    posX = Float.valueOf(value.getText());
                                    break;
                                case "posy":
                                    posY = Float.valueOf(value.getText());
                                    break;
                                case "width":
                                    width = Integer.valueOf(value.getText());
                                    break;
                                case "height":
                                    height = Integer.valueOf(value.getText());
                                    break;
                            }

                        }
                        break;
                }

            }
        }
        loadAnimation();
    }

    public void update(int delta) throws SlickException {

        nextX = posX;
        nextY = posY;

        for (Iterator<Vector> iter = vectorList.iterator(); iter.hasNext(); ) {
            Vector v = iter.next();
            nextX += v.getX(delta);
            nextY += v.getY(delta);
            if (v.isEnded()) iter.remove();
        }

        if (Collision.isNoCollision(tiledMap, nextX, nextY, width, height)) {
            posX = nextX;
            posY = nextY;
        }

        graphics.update(delta);

    }

    public void render(Graphics g){
        graphics.render(g);
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean implement(Class c) {
        for (Class d : this.getClass().getInterfaces()) {
            if (d == c) return true;
        }
        return false;
    }

    public float distanceWith(Object object) {
        return (float) Math.sqrt(Math.pow(object.getX() - posX, 2) + Math.pow(posY - object.getY(), 2));
    }

    protected abstract void loadAnimation() throws SlickException;

    public TiledMap getMap() {
        return tiledMap;
    }

    public Planet getPlanet() {
        return planet;
    }

    public String getName() {
        return name;
    }

    public void setLevel(PlayableMap level) {
        this.level = level;
    }

    public int getMapX() {
        return (int) (posX / tiledMap.getTileWidth());
    }

    public int getMapY() {
        return (int) (posY / tiledMap.getTileHeight());
    }

    public PlayableMap getLevel() {
        return level;
    }

    public void setNextX(float nextX) {
        this.nextX = nextX;
    }

    public void setNextY(float nextY) {
        this.nextY = nextY;
    }

    public void addVector(Vector vector) {
        this.vectorList.add(vector);
    }

    public void addGraphic(Graphic graphic){
        graphics.addGraphic(graphic);
    }

    public void onDeath(){}

    public float getCenterX() {
        return posX + width / 2;
    }

    public float getCenterY() {
        return posY + height / 2;
    }

    public Vector2f getHandPos() {
        return handPos;
    }


}
