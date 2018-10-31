package com.thedoctor.gamestates;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.loading.ThreadLoading;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateLoading extends BasicGameState {

    public static final int ID = -1;

    private StateBasedGame game;
    private Image background;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        this.background = new Image((Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/b1.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        background.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
        g.setColor(Color.black);
        g.drawString("Loading...", gameContainer.getWidth() / 2 - 30, gameContainer.getHeight() / 2);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (ThreadLoading.isLoadingEnded) {
            stateBasedGame.enterState(StateMenu.ID);
        }
    }
}
