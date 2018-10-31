package com.thedoctor.game.ship;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.objects.Camera;
import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Ship {

    StateBasedGame game;
    GameContainer gc;
    String path;

    TiledMap map;

    Camera camera;
    Player player;

    public Ship(String path, StateBasedGame game, GameContainer gameContainer) throws SlickException {
        this.game = game;
        this.gc = gameContainer;
        this.path = (Main.isTest ? Util.RESOURCES_PATH_TEST : Util.RESOURCES_PATH) + path;
        this.map = new TiledMap( this.path + "/map.tmx");
        this.player = Registry.player;
        this.camera = new Camera(player);

        player.setMap(map);
        player.setPosX(64);
        player.setPosY(500);
    }

    public void render(Graphics g){
        camera.render(gc, g);
        map.render(0, 0, map.getLayerIndex("main"));
        player.render(g);
    }

    public void update(int delta) throws SlickException {
        camera.update(gc);
        player.update(delta);
    }
}
