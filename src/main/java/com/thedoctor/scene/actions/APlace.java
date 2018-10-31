package com.thedoctor.scene.actions;

import com.thedoctor.scene.load.LoadAnimation;
import com.thedoctor.scene.load.LoadFile;
import com.thedoctor.scene.load.LoadImage;
import com.thedoctor.xml.Script;
import org.newdawn.slick.Graphics;

public class APlace implements Action{

    String name;
    int posX, posY;
    LoadFile linked;
    boolean isWorking;

    public APlace(String line){
        int index = 0, tabIndex = 0;
        String[] tab = new String[3];
        tab[0] = "";
        while (index < line.length()){
            if (line.charAt(index) != ' '){
                tab[tabIndex] += line.charAt(index);
            } else {
                tabIndex++;
                tab[tabIndex] = "";
            }
            index++;
        }

        this.name = tab[0];
        this.posX = Integer.valueOf(tab[1]);
        this.posY = Integer.valueOf(tab[2]);
    }

    @Override
    public void link(Script script) {
        this.linked = script.getLinkedFile(this.name);
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
    }

    @Override
    public void render(Graphics g) {
        if (linked instanceof LoadImage) g.drawImage(linked.getImage(), posX, posY);
        else if (linked instanceof LoadAnimation) g.drawAnimation(linked.getAnimation(), posX, posY);
    }

    @Override
    public void keyReleased() {

    }
}
