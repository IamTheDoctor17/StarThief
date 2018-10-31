package com.thedoctor.game.hud;

import com.thedoctor.Util;
import com.thedoctor.game.hud.planetmap.MapMenu;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ShipInventoryMenu {


    int width;
    int height;

    int startX, startY;

    boolean isActive;

    int selectedMenu, triggerMenu;

    InventoryMenu inventoryMenu;
    MapMenu mapMenu;

    public ShipInventoryMenu(GameContainer gc, StateBasedGame game) {
        this.width = (int) (gc.getWidth() / 1.2);
        this.height = (int) (gc.getHeight() / 1.2);
        this.selectedMenu = 0;
        this.triggerMenu = -1;
        this.startX = gc.getWidth() / 2 - width / 2;
        this.startY = gc.getHeight() / 2 - height / 2;
        this.inventoryMenu = new InventoryMenu(this.startX + 5, this.startY + 45, width - 10, height - 5);
        this.mapMenu = new MapMenu(this.startX + 5, this.startY + 45, width - 10, height - 50, game, gc);
    }

    public void render(Graphics g) throws SlickException {
        if (isActive) {
            g.resetTransform();
            g.setColor(new Color(255, 255, 255, 0.5f));
            g.fillRect(startX, startY, width, height);
            g.setColor(Color.orange);
            g.fillRect(startX, startY, 100, 40);
            g.fillRect(startX + 100, startY, 100, 40);
            g.setColor(Color.black);
            g.drawString("inventory", startX + 10, startY + 10);
            g.drawString("map", startX + 110, startY + 10);
            switch (selectedMenu){
                case 0:{
                    inventoryMenu.render(g);
                    break;
                }
                case 1:{
                    mapMenu.render(g);
                    break;
                }
            }
        }
    }

    public void update(){
        if (isActive){
            boolean isTrigger = false;
            if (Util.pointInTrigger(Mouse.getX(), 720 - Mouse.getY(), startX + 10, startY, 100, 40)){
                triggerMenu = 0;
                isTrigger = true;
            }
            if (Util.pointInTrigger(Mouse.getX(), 720 - Mouse.getY(), startX + 100, startY, 100, 40)){
                triggerMenu = 1;
                isTrigger = true;
            }
            if (!isTrigger) triggerMenu = -1;

            switch (selectedMenu){
                case 0:{

                }
                case 1:{
                    mapMenu.update();
                }
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void clicked() throws SlickException {
        if (triggerMenu != -1) selectedMenu = triggerMenu;
        switch (selectedMenu){
            case 1:{
                mapMenu.clicked();
            }
        }
    }

    public void keyReleased(int key){
        switch (selectedMenu){
            case 1:{
                mapMenu.keyReleased(key);
            }
        }
    }
}