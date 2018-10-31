package com.thedoctor.game.hud.planetmap;

import com.thedoctor.Util;
import com.thedoctor.gamestates.StatePlanet;
import com.thedoctor.loading.Registry;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Iterator;
import java.util.Set;

public class MapMenu {

    private GameContainer gc;
    private StateBasedGame game;
    private int startX, startY;
    private int width, height;

    private int planetWidth, planetHeight;

    int selectedPlanet, triggeredPlanet;
    Planet[] planets;

    public MapMenu(int startX, int startY, int width, int height, StateBasedGame game, GameContainer gc) {
        this.gc = gc;
        this.game = game;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.planetWidth = 200;
        this.planetHeight = 200;
        this.selectedPlanet = 0;
        this.planets = new Planet[Registry.getPlanets().size()];
        createPlanets();
    }

    public void render(Graphics g){
        g.fillRect(startX, startY, width, height);
        if (selectedPlanet > 0) planets[selectedPlanet - 1].render(g, startX + (width / 2) - 400, startY + height / 2 - planetHeight / 2);
        planets[selectedPlanet].render(g, startX + width / 2 - planetWidth / 2, startY + height / 2 - planetHeight / 2);
        if (selectedPlanet + 1 < planets.length) planets[selectedPlanet + 1].render(g, startX + (width / 2) + 200, startY + height / 2 - planetHeight / 2);
    }

    public void update(){
        boolean isTrigger = false;
        if (Util.pointInTrigger(Mouse.getX(), 720 - Mouse.getY(), startX + (width / 2) - 400, startY + height / 2 - planetHeight / 2, planetWidth, planetHeight) && selectedPlanet > 0){
            triggeredPlanet = selectedPlanet - 1;
            isTrigger = true;
        }
        if (Util.pointInTrigger(Mouse.getX(), 720 - Mouse.getY(), startX + width / 2 - planetWidth / 2, startY + height / 2 - planetHeight / 2, planetWidth, planetHeight)){
            triggeredPlanet = selectedPlanet;
            isTrigger = true;
        }
        if (Util.pointInTrigger(Mouse.getX(), 720 - Mouse.getY(), startX + (width / 2) + 200, startY + height / 2 - planetHeight / 2, planetWidth, planetHeight) && selectedPlanet + 1 < planets.length){
            triggeredPlanet = selectedPlanet + 1;
            isTrigger = true;
        }
        if (!isTrigger) triggeredPlanet = -1;
    }

    private void createPlanets(){
        Set<String> keys = Registry.getPlanets().keySet();
        Iterator<String> keysIter = keys.iterator();
        for (int i = 0; i < planets.length; i++){
            String current = keysIter.next();
            planets[i] = new Planet(current, new Color((int)(Math.random() * 255),(int)(Math.random() * 255), (int)(Math.random() * 255)), planetWidth, planetHeight);
        }
    }

    public void keyReleased(int key){
        switch (key){
            case Input.KEY_M:{
                if (selectedPlanet + 1 < planets.length) selectedPlanet++;
                break;
            }
            case Input.KEY_L:{
                if (selectedPlanet > 0) selectedPlanet--;
                break;
            }
        }
    }

    public void clicked() throws SlickException {
        if (triggeredPlanet != -1){
            Registry.currentPlanet = planets[triggeredPlanet].getName();
            game.getState(StatePlanet.ID).init(gc, game);
            game.enterState(StatePlanet.ID);
        }
    }
}