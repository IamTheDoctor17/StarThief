package com.thedoctor.gamestates;

import com.thedoctor.game.planet.Arena;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateArena extends PlayingState {

    public static final int ID = 4;

    Arena arena;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super.init(gameContainer, stateBasedGame);
        this.arena = new Arena(this.gc, this.game);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (!isPaused) arena.update(i);
        super.update(gameContainer, stateBasedGame, i);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        arena.render(g);
        super.render(gameContainer, stateBasedGame, g);
    }
}
