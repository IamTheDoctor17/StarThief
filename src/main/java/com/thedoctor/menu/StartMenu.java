package com.thedoctor.menu;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.game.planet.Arena;
import com.thedoctor.game.save.GameSave;
import com.thedoctor.gamestates.*;
import com.thedoctor.loading.Fonts;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class StartMenu {

    Image background;
    OptionManager menus[];

    int currentMenu = 0;

    GameContainer gc;
    StateBasedGame game;

    public StartMenu(GameContainer gc, StateBasedGame game) throws SlickException {
        this.gc = gc;
        this.game = game;
        this.background = new Image((Main.isTest ? "core/" : Util.RESOURCES_PATH) + "core/b2.png");
        this.menus = new OptionManager[5];
        this.menus[0] = new OptionManager(700, 430, 10, 150, 40);
        menus[0].addOption("saves");
        menus[0].addOption("alpha");
        menus[0].addOption("options");
        menus[0].addOption("credits");
        menus[0].addOption("quit");
        this.menus[1] = new OptionManager(700, 430, 10, 100, 40);
        menus[1].addOption("Save 1");
        menus[1].addOption("Save 2");
        menus[1].addOption("Save 3");
        menus[1].addOption("back");
        this.menus[2] = new OptionManager(700, 430, 10, 150, 40);
        menus[2].addOption("fullscreen");
        menus[2].addOption("controls");
        menus[2].addOption("back");
        this.menus[3] = new OptionManager(700, 430, 10, 210, 40);
        menus[3].addOption("set AZERTY");
        menus[3].addOption("set QWERTY");
        menus[3].addOption("back");
        this.menus[4] = new OptionManager(700, 430, 10, 150, 40);
        menus[4].addOption("arena");
        menus[4].addOption("universe");
        menus[4].addOption("script");
        menus[4].addOption("back");
    }

    public void update(){
        menus[currentMenu].update();
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.setFont(Fonts.getMenuFont30());
        g.drawImage(background, 0, 0);
        menus[currentMenu].render(g);
    }

    public void clicked(){
        switch (currentMenu){
            case 0:{
                switch (menus[currentMenu].selected){
                    case 0:{
                        currentMenu = 1;
                        break;
                    }
                    case 1:{
                        currentMenu = 4;
                        break;
                    }
                    case 2:{
                        currentMenu = 2;
                        break;
                    }
                    case 3:{
                        try {
                            this.game.addState(new StateCredit());
                            this.game.getState(StateCredit.ID).init(gc, game);
                            this.game.enterState(StateCredit.ID);
                        } catch (SlickException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 4:{
                        gc.exit();
                        break;
                    }
                }
                break;
            }
            case 1:{
                switch (menus[currentMenu].selected){
                    case 0:{
                        Registry.currentSave = new GameSave("save1.xml");
                        this.game.addState(new StatePlanet());
                        loadSave();
                        this.game.enterState(Registry.currentState);
                        break;
                    }
                    case 1:{
                        Registry.currentSave = new GameSave("save2.xml");
                        this.game.addState(new StatePlanet());
                        loadSave();
                        this.game.enterState(Registry.currentState);
                        break;
                    }
                    case 2:{
                        Registry.currentSave = new GameSave("save3.xml");
                        this.game.addState(new StatePlanet());
                        loadSave();
                        this.game.enterState(Registry.currentState);
                        break;
                    }
                    case 3:{
                        currentMenu = 0;
                        break;
                    }
                }
                break;
            }
            case 2:{
                switch (menus[currentMenu].selected){
                    case 0: {
                        try {
                            gc.setFullscreen(!gc.isFullscreen());
                        } catch (SlickException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 1:{
                        currentMenu = 3;
                        break;
                    }
                    case 2:{
                        currentMenu = 0;
                        break;
                    }
                }
                break;
            }
            case 3:{
                switch (menus[currentMenu].selected){
                    case 0:{
                        Util.setKeyboard("azerty");
                        break;
                    }
                    case 1:{
                        Util.setKeyboard("qwerty");
                        break;
                    }
                    case 2:{
                        currentMenu = 0;
                        break;
                    }
                }
                break;
            }
            case 4:{
                switch (menus[currentMenu].selected){
                    case 0:{
                        try {
                            Arena.preInitArena();
                            this.game.addState(new StateArena());
                            this.game.getState(StateArena.ID).init(gc, game);
                            this.game.enterState(StateArena.ID);
                        } catch (SlickException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 1:{
                        try {
                            this.game.addState(new StateUniverse());
                            this.game.getState(StateUniverse.ID).init(gc, game);
                            this.game.enterState(StateUniverse.ID);
                        } catch (SlickException e){
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 2:{
                        try {
                            this.game.addState(new StateScript());
                            this.game.getState(StateScript.ID).init(gc, game);
                            this.game.enterState(StateScript.ID);
                        } catch (SlickException e){
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 3:{
                        currentMenu = 0;
                        break;
                    }
                }
                break;
            }
        }
    }

    private void loadSave() {
        try {
            Registry.player = new Player();
            Registry.currentSave.loadSave(game);
            this.game.getState(Registry.currentState).init(gc, game);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
