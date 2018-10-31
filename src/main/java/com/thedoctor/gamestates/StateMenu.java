package com.thedoctor.gamestates;

import com.thedoctor.menu.StartMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateMenu extends BasicGameState {

    public static final int ID = 0;

    private StateBasedGame game;
    private GameContainer gameContainer;

    StartMenu menu;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        this.gameContainer = gameContainer;
        this.menu = new StartMenu(gameContainer, stateBasedGame);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        menu.render(g);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        menu.update();
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        if (Input.MOUSE_LEFT_BUTTON == button) {
            menu.clicked();
        }
    }
}
