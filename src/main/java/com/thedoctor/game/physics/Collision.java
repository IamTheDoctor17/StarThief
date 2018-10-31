package com.thedoctor.game.physics;

import com.thedoctor.game.objects.Object;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

public enum Collision {
    ;
    public static boolean doesObjectsCollide(Object e1, Object e2){
        return !((e1.getX() >= e2.getX() + e2.getWidth())      // trop à droite
                || (e1.getX() + e1.getWidth() <= e2.getX()) // trop à gauche
                || (e1.getY() >= e2.getY() + e2.getHeight()) // trop en bas
                || (e1.getY() + e1.getHeight() <= e2.getY()));  // trop en haut

    }

    public static boolean isWestCollision(TiledMap map, float x, float y, int width, int height){
        return isCollision(map, x, y) || isCollision(map, x, y + height);
    }
    public static boolean isEastCollision(TiledMap map, float x, float y, int width, int height){
        return isCollision(map, x + width, y) || isCollision(map, x + width, y + height);
    }
    public static boolean isNorthCollision(TiledMap map, float x, float y, int width, int height){
        return isCollision(map, x, y) || isCollision(map, x + width, y);
    }
    public static boolean isSouthCollision(TiledMap map, float x, float y, int width, int height){
        return isCollision(map, x, y + height) || isCollision(map, x + width, y + height);
    }


    public static boolean isNoCollision(TiledMap map, float x, float y, int width, int height){
        return !isEastCollision(map, x, y, width, height) &&
                !isWestCollision(map, x, y, width, height) &&
                !isNorthCollision(map, x, y, width, height) &&
                !isSouthCollision(map, x, y, width, height);
    }

    private static boolean isCollision(TiledMap map, float x, float y){
        int tileW = map.getTileWidth();
        int tileH = map.getTileHeight();
        int logicLayer = map.getLayerIndex("logic");
        Image tile = map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }
}
