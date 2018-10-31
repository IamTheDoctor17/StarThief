package com.thedoctor.game.hud;


import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.loading.Fonts;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HUD {

    Player player;
    GameContainer gc;

    public HUD(Player player, GameContainer gameContainer) {
        this.player = player;
        this.gc = gameContainer;
    }

    public void render(Graphics g) throws SlickException {
        g.resetTransform();
        g.setFont(Fonts.getMenuFont16());
        g.setColor(Color.blue);
        g.fillRect(20, 40, 200, 80);
        g.setColor(Color.black);
        renderHealth(30, 50, g);
        g.setColor(Color.black);
        g.drawString("Score : " + Registry.score, 30, 75);
        player.inventory.setStart(10, gc.getHeight() - 50);
        player.inventory.render(g);
        g.setColor(Color.gray);
        g.fillRect(10, 500, 30, 30);
        g.setColor(Color.cyan);
    }

    public void renderHealth(int startX, int startY, Graphics g) {
        float scale = 180 / Player.MAX_HEALTH;
        g.setColor(Color.gray);
        g.fillRect(startX, startY, 180, 20);
        g.setColor(Color.red);
        g.fillRect(startX, startY, scale * player.getHealth(), 20);
    }

    public void clicked() {
        player.inventory.clicked();
    }

    public void update() {
        player.inventory.update();
    }

    public void mouseWheel(int value) {
        player.inventory.mouseWheel(value);
    }
}