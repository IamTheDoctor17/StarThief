package com.thedoctor.game.planet;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.ai.ArenaAI;
import com.thedoctor.game.ai.pathfinding.LogicMap;
import com.thedoctor.game.objects.Camera;
import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.game.objects.item.ItemSimpleGun;
import com.thedoctor.game.objects.item.ItemWoodenSword;
import com.thedoctor.loading.Fonts;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class Arena extends PlayableMap {

    Player player;
    ArenaAI ai;

    boolean playerDead;

    public Arena(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super();
        this.gc = gameContainer;
        this.game = stateBasedGame;
        this.player = Registry.player;
        this.ai = new ArenaAI(this);

        this.entities = new ArrayList<>();
        this.map = new TiledMap((Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/arena/map.tmx");
        this.logicMap = new LogicMap(map);
        this.damageManager = new DamageManager(this);

        this.entities.add(player);
        this.camera = new Camera(player);

        player.setPosX(1600);
        player.setPosY(1600);
        player.setMap(map);
        player.setLevel(this);
        this.playerDead = false;

        player.inventory.addItem(ItemWoodenSword.ID);
        player.inventory.addItem(ItemSimpleGun.ID);
    }

    public void update(int delta) throws SlickException {
        super.update(delta);

        if (Registry.player.isDead()){
            playerDead = true;
        }
        ai.update();
    }


    public void render(Graphics g) {
        super.render(g);
        if (playerDead){
            g.resetTransform();
            g.setFont(Fonts.getMenuFont30());
            g.drawString("GAME OVER", gc.getWidth() / 2, gc.getHeight() / 2);

        }
    }

    public static void preInitArena() throws SlickException {
        Registry.player = new Player();
    }
}