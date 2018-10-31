package com.thedoctor.gamestates;

import com.thedoctor.game.universe.Universe;
import com.thedoctor.menu.PauseMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateUniverse extends BasicGameState {

    public static final int ID = 5;

    private PauseMenu pauseMenu;

    protected boolean isPaused;

    Universe universe;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.universe = new Universe(container, game);
        this.pauseMenu = new PauseMenu(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        universe.render(g);
        if (isPaused) pauseMenu.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (isPaused) pauseMenu.update();
        else universe.update(delta);
    }

    @Override
    public void keyReleased(int key, char c) {
        if (key == Input.KEY_ESCAPE){
            isPaused = !isPaused;
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == Input.MOUSE_LEFT_BUTTON){
            if (isPaused) {
                pauseMenu.clicked();
            } else universe.clicked();
        }
    }
}
