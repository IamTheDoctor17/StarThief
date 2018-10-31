package com.thedoctor.gamestates;

import com.codedisaster.steamworks.SteamApps;
import com.thedoctor.Util;
import com.thedoctor.loading.Fonts;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class StateCredit extends BasicGameState {

    public static final int ID = 6;

    private List<String> str;
    private float buffer;

    private StateBasedGame game;
    private GameContainer gc;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {
        this.game = game;
        this.gc = container;

        SteamApps apps = new SteamApps();

        str = new ArrayList<>();
        System.out.println(apps.getCurrentGameLanguage());
        switch (apps.getCurrentGameLanguage()) {
            case "french": {
                str.add("Star Thief");
                str.add("Editeur : Maxime Letemple");
                str.add("Développeur : Maxime Letemple");
                str.add("Artworks : Paco Roullet");
                str.add("Textures : Paco Roullet");
                break;
            }
            default: {
                str.add("Star Thief");
                str.add("Editor : Maxime Letemple");
                str.add("StarThief developer : Maxime Letemple");
                str.add("Artworks : Paco Roullet");
                str.add("Textures : Paco Roullet");
                break;
            }
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        g.setColor(Color.white);
        g.setFont(Fonts.getMenuFont30());

        for (int i = 0; i < str.size(); i++) {
            g.drawString(str.get(i), container.getWidth() / 2 - str.get(i).length() / 2 * 15, buffer + i * 40);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        buffer += delta * 0.1;
        if (buffer > container.getHeight()) buffer = 0;
    }

    @Override
    public void keyReleased(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            try {
                this.game.addState(new StateMenu());
                this.game.getState(StateMenu.ID).init(gc, game);
                this.game.enterState(StateMenu.ID);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }
}
