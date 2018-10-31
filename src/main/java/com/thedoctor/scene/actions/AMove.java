package com.thedoctor.scene.actions;

import com.thedoctor.scene.load.LoadAnimation;
import com.thedoctor.scene.load.LoadFile;
import com.thedoctor.scene.load.LoadImage;
import com.thedoctor.xml.Script;
import org.newdawn.slick.Graphics;

public class AMove implements Action{

    String name;
    LoadFile linked;
    private boolean isWorking;
    private double startX, startY, x, y, buffer;
    private double posX, posY;

    public AMove(String line){
        int index = 0, tabIndex = 0;
        String[] tab = new String[5];
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
        this.startX = Integer.valueOf(tab[1]);
        this.startY = Integer.valueOf(tab[2]);
        this.x = Math.cos(Double.valueOf(tab[3]));
        this.y = Math.sin(Double.valueOf(tab[3]));
        this.buffer = Integer.valueOf(tab[4]);

        this.posX = startX;
        this.posY = startY;
    }

    @Override
    public void link(Script script) {
        this.linked = script.getLinkedFile(this.name);
    }

    @Override
    public void update(int delta) {
        posX += delta * 0.001 * x * buffer;
        posY += delta * 0.001 * y * buffer;
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
        if (linked instanceof LoadImage) {
            g.drawImage(linked.getImage(), ((int) posX), ((int) posY));
        } else if (linked instanceof LoadAnimation) g.drawAnimation(linked.getAnimation(), ((int) posX), ((int) posY));
    }

    @Override
    public void keyReleased() {

    }
}
