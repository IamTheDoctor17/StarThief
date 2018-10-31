package com.thedoctor.menu;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.gamestates.StateMenu;
import com.thedoctor.loading.Fonts;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu {

    Image background;

    OptionManager[] menus;
    int currentMenu;

    GameContainer gc;
    StateBasedGame game;

    int width;
    int height;

    public PauseMenu(GameContainer gc, StateBasedGame game) throws SlickException {
        this.gc = gc;
        this.game = game;

        this.width = 320;
        this.height = 640;

        this.background = new Image((Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/pausemenu.png");
        this.menus = new OptionManager[1];
        this.currentMenu = 0;

        this.menus[0] = new OptionManager(gc.getWidth() / 2 - width / 2, gc.getHeight() / 2 - height / 2, 10, 200, 40);
        this.menus[0].addOption("save");
        this.menus[0].addOption("back to menu");
    }

    public void update() {
        menus[currentMenu].update();
    }

    public void render(Graphics g) {
        g.resetTransform();
        g.setFont(Fonts.getMenuFont30());
        g.setColor(Color.black);
        g.drawImage(background.getScaledCopy(width, height), gc.getWidth() / 2 - width / 2, gc.getHeight() / 2 - height / 2);
        menus[currentMenu].render(g);
    }

    public void clicked(){
        switch (currentMenu){
            case 0:{
                switch (menus[currentMenu].selected){
                    case 0:{
                        //Registry.currentSave.save(game);
                        break;
                    }
                    case 1:{
                        game.enterState(StateMenu.ID);
                        break;
                    }
                }
                break;
            }
        }
    }
}
