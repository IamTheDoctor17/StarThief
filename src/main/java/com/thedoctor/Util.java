package com.thedoctor;

import com.thedoctor.game.objects.Object;
import com.thedoctor.game.objects.entity.EntityLiving;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tiled.TiledMap;

public class Util {


    public static int KEY_UP = Input.KEY_Z;
    public static int KEY_DOWN = Input.KEY_S;
    public static int KEY_LEFT = Input.KEY_Q;
    public static int KEY_RIGHT = Input.KEY_D;
    public static int KEY_INVENTORY = Input.KEY_E;

    public static final String RESOURCES_PATH_TEST = System.getProperty("user.dir").replace("\\", "/") + "/src/main/assets/core/";
    public static final String RESOURCES_PATH = System.getProperty("user.dir").replace("\\", "/").substring(0, System.getProperty("user.dir").length() - 8) + "/core/assets/";

    public static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y, int delay) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), delay);
        }
        return animation;
    }

    public static void updateTriggers(EntityLiving e, TiledMap tiledMap) {
        for (int id = 0; id < tiledMap.getObjectCount(0); id ++) {
            if (e.getX() > tiledMap.getObjectX(0, id)
                    && e.getX() < tiledMap.getObjectX(0, id) + tiledMap.getObjectWidth(0, id)
                    && e.getY() > tiledMap.getObjectY(0, id)
                    && e.getY() < tiledMap.getObjectY(0, id) + tiledMap.getObjectHeight(0, id)) {
                e.triggers.add(tiledMap.getObjectType(0, id));
            }
        }
    }

    public static boolean pointInTrigger(int x1, int y1, int x2, int y2, int width2, int height2) {
        return x1 > x2 && x1 < x2 + width2 && y1 > y2 && y1 < y2 + height2;
    }

    public static float getDistance(Object objectA, Object objectB){
        return (float) Math.sqrt(Math.pow(objectA.getCenterX() - objectB.getCenterX(), 2) + Math.pow(objectB.getCenterY() - objectA.getCenterY(), 2));
    }

    public static float getRadianAngle(float xa, float xb, float ya, float yb){
        if (ya < yb){
            return ((float) Math.acos((xb - xa) / Math.hypot(xb - xa, yb - ya)));
        } else return -((float) Math.acos((xb - xa) / Math.hypot(xb - xa, yb - ya)));
    }

    public static void setKeyboard(String format){
        switch (format){
            case "qwerty":{
                KEY_UP = Input.KEY_Z;
                KEY_DOWN = Input.KEY_S;
                KEY_LEFT = Input.KEY_Q;
                KEY_RIGHT = Input.KEY_D;
                KEY_INVENTORY = Input.KEY_E;
                break;
            }
            case "azerty":{
                KEY_UP = Input.KEY_W;
                KEY_DOWN = Input.KEY_S;
                KEY_LEFT = Input.KEY_A;
                KEY_RIGHT = Input.KEY_D;
                KEY_INVENTORY = Input.KEY_E;
                break;
            }
        }
    }
}
