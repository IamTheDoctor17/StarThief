package com.thedoctor.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateNewGame extends PlayingState{

    public static final int ID = 3;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super.init(gameContainer, stateBasedGame);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("New StarThief State - Press any key", gc.getWidth() / 2, gc.getHeight() / 2);
    }

    @Override
    public void keyReleased(int key, char c) {
        try {
            game.addState(new StateShip());
            game.getState(StateShip.ID).init(gc, game);
            game.enterState(StateShip.ID);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
