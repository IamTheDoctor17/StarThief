package com.thedoctor.scene;

import com.thedoctor.xml.Script;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Cinematic{

    List<Image> textures;
    Script script;
    boolean isEnded;

    public Cinematic(Script script) throws SlickException {
        this.script = script;
        script.load();
        this.textures = new ArrayList<>();
        isEnded = false;
    }

    public void update(int delta) {
        script.update(delta);
        if (script.isEnded()) isEnded = true;
    }

    public void render(Graphics g){
        script.render(g);
    }

    public void start(){
        script.start();
    }

    public void keyReleased() {
        script.keyReleased();
    }

    public boolean isEnded() {
        return isEnded;
    }
}