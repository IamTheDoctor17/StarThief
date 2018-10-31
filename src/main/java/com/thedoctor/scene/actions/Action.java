package com.thedoctor.scene.actions;

import com.thedoctor.xml.Script;
import org.newdawn.slick.Graphics;

public interface Action {

    void link(Script script);

    void update(int delta);

    boolean isWorking();

    void setWorking(boolean isWorking);

    void render(Graphics g);

    void keyReleased();
}
