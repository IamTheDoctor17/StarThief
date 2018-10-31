package com.thedoctor.gamestates;

import com.thedoctor.game.hud.HUD;
import com.thedoctor.loading.Registry;
import com.thedoctor.menu.PauseMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class PlayingState extends BasicGameState {

    private PauseMenu pauseMenu;

    boolean isPaused;
    protected HUD hud;

    boolean drawHUD;

    GameContainer gc;
    StateBasedGame game;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        this.gc = gameContainer;
        this.isPaused = false;
        this.hud = new HUD(Registry.player, gameContainer);
        this.pauseMenu = new PauseMenu(gameContainer, stateBasedGame);
        this.drawHUD = true;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (isPaused) {
            pauseMenu.update();
        } else hud.update();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        if (isPaused){
            pauseMenu.render(g);
        }
        if (drawHUD) hud.render(g);
    }

    @Override
    public void keyReleased(int key, char c) {
        if (key == Input.KEY_ESCAPE){
            isPaused = !isPaused;
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == Input.MOUSE_LEFT_BUTTON && isPaused){
            pauseMenu.clicked();
        }
        if (button == Input.MOUSE_LEFT_BUTTON){
            hud.clicked();
        }
    }

    @Override
    public void mouseWheelMoved(int newValue) {
        hud.mouseWheel(newValue);
    }
}
