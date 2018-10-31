package com.thedoctor.scene.actions;

import com.thedoctor.xml.Script;
import org.newdawn.slick.Graphics;

public class AEnd implements Action{

    Script script;
    boolean isWorking;

    @Override
    public void link(Script script) {
        this.script = script;
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
        this.script.setEnded(true);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void keyReleased() {
    }
}
