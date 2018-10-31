package com.thedoctor.scene.actions;

import com.thedoctor.xml.Script;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class AStart implements Action {

    boolean isWorking;
    List<Action> linked;

    public AStart() {
        this.linked = new ArrayList<>();
        this.isWorking = false;
    }

    @Override
    public void link(Script script) {
        for (int i = script.getActions().indexOf(this) + 1; i < script.getActions().size(); i++) {

            linked.add(script.getActions().get(i));

            if (script.getActions().get(i) instanceof AWait) break;
        }
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
        for (int i = 0; i < linked.size() - 1; i++){
            linked.get(i).setWorking(isWorking);
        }
        if (!isWorking) linked.get(linked.size() - 1).setWorking(true);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void keyReleased() {
        this.setWorking(false);
    }
}
