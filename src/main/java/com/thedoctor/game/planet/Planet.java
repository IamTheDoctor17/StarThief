package com.thedoctor.game.planet;

import org.dom4j.Element;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.thedoctor.Main.isTest;
import static com.thedoctor.Util.RESOURCES_PATH;
import static com.thedoctor.Util.RESOURCES_PATH_TEST;
import static com.thedoctor.xml.Reader.getElement;

public class Planet {

    private GameContainer gc;
    private StateBasedGame game;

    String name;

    Element xmlElement;
    String planetPath;

    int levelsCount;
    int index;

    List<Level> levels;

    private boolean hasBeenPlayed;

    public Planet(Element xmlElement) throws SlickException {
        this.hasBeenPlayed = true;
        this.xmlElement = xmlElement;
        this.planetPath = (isTest ? RESOURCES_PATH_TEST : RESOURCES_PATH) + "planets/" + this.xmlElement.attributeValue("name") + "/";
        this.name = this.xmlElement.attributeValue("name");
        this.levels = new ArrayList<>();
        this.index = 0;
        loadLevels();
    }

    public Planet(String planetPath) throws SlickException {
        this.hasBeenPlayed = false;
        this.planetPath = planetPath.substring(0, planetPath.length() - 10);
        this.xmlElement = getElement(planetPath);
        this.name = xmlElement.attributeValue("name");
        this.levels = new ArrayList<>();
        loadLevels();
        this.index = 0;
    }

    private void loadLevels() throws SlickException {
        levelsCount = 0;

        for (Iterator levelsIter = xmlElement.elementIterator(); levelsIter.hasNext(); ) {
            Element level = (Element) levelsIter.next();
            levels.add(new Level(level, planetPath, this));
            levelsCount++;
        }

    }

    public void init(StateBasedGame game, GameContainer gc) throws SlickException {
        this.gc = gc;
        this.game = game;
        levels.get(index).init(game, gc);
        this.hasBeenPlayed = true;
    }

    public Level getCurrentLevel() {
        return levels.get(index);
    }

    public void nextLevel() throws SlickException {
        index++;
        levels.get(index).init(game, gc);
    }

    public String getName() {
        return name;
    }

    public void reload() throws SlickException {
        this.levels = new ArrayList<>();
        loadLevels();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int currentLevel) {
        index = currentLevel;
    }

    public int getLevelsCount() {
        return levelsCount;
    }

    public Level getLevel(int index) {
        return levels.get(index);
    }

    public boolean hasBeenPlayed() {
        return hasBeenPlayed;
    }

    public void setPlayed(boolean b) {
        this.hasBeenPlayed = b;
    }
}