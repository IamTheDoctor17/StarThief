package com.thedoctor.gamestates;

import com.thedoctor.Util;
import com.thedoctor.game.hud.ShipInventoryMenu;
import com.thedoctor.game.ship.Ship;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import static com.thedoctor.loading.Registry.ship;

public class StateShip extends PlayingState {

    public static final int ID = 2;

    private ShipInventoryMenu inventoryMenu;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super.init(gameContainer, stateBasedGame);
        ship = new Ship("core/ship", stateBasedGame, gameContainer);
        this.inventoryMenu = new ShipInventoryMenu(gameContainer, stateBasedGame);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        ship.render(g);
        if (!isPaused) {
            inventoryMenu.render(g);
        }
        super.render(gameContainer, stateBasedGame, g);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (!isPaused) {
            ship.update(delta);
            inventoryMenu.update();
        }
        super.update(gameContainer, stateBasedGame, delta);
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);

        if (key == Util.KEY_INVENTORY && !isPaused){
            inventoryMenu.setActive(!inventoryMenu.isActive());
            drawHUD = !drawHUD;
        }

        if (inventoryMenu.isActive()) inventoryMenu.keyReleased(key);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);

        if (button == Input.MOUSE_LEFT_BUTTON){
            try {
                inventoryMenu.clicked();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }
}