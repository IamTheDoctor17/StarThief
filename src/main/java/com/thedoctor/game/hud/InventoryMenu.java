package com.thedoctor.game.hud;

import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class InventoryMenu {

    private Player player;

    private int startX, startY;
    private int width, height;

    InventoryMenu(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.player = Registry.player;
    }

    public void render(Graphics g) throws SlickException {
        player.inventory.render(g);
    }
}
