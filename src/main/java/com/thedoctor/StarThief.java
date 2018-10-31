package com.thedoctor;

import com.thedoctor.gamestates.StateLoading;
import com.thedoctor.gamestates.StateMenu;
import com.thedoctor.loading.Fonts;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class StarThief extends StateBasedGame {

    StarThief(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        Fonts.loadFonts();
        addState(new StateLoading());
        addState(new StateMenu());
        this.enterState(StateLoading.ID);
    }
}
