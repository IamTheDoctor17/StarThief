package com.thedoctor.gamestates;

import com.thedoctor.game.planet.Planet;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StatePlanet extends PlayingState {

    public static final int ID = 1;

    private StateBasedGame game;
    private GameContainer gameContainer;

    Planet currentplanet;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        this.gameContainer = gameContainer;
        currentplanet = Registry.getPlanet(Registry.currentPlanet);
        currentplanet.reload();
        this.currentplanet.init(this.game, this.gameContainer);
        Registry.player.inventory.addItem(2);
        super.init(gameContainer, stateBasedGame);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        currentplanet.getCurrentLevel().render(g);
        super.render(gameContainer, stateBasedGame, g);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (!isPaused) {
            currentplanet.getCurrentLevel().update(delta);
        }
        super.update(gameContainer, stateBasedGame, delta);
    }
}