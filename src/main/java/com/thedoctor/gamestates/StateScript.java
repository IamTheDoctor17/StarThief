package com.thedoctor.gamestates;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.scene.Cinematic;
import com.thedoctor.xml.Script;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateScript extends BasicGameState {

    public static final int ID = 7;
    Cinematic test;

    StateBasedGame game;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.test = new Cinematic(new Script((Main.isTest ? Util.RESOURCES_PATH_TEST: Util.RESOURCES_PATH) + "scripts/test1/test1.sts"));
        test.start();
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        test.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        test.update(delta);
        if (test.isEnded()){
            this.game.enterState(StateMenu.ID);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        test.keyReleased();
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        test.keyReleased();
    }
}
