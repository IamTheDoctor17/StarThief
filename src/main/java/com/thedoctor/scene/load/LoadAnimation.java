package com.thedoctor.scene.load;

import com.thedoctor.Main;
import com.thedoctor.Util;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class LoadAnimation implements LoadFile{

    private String name;
    private Animation animation;

    public LoadAnimation(String line) throws SlickException {
        int index = 0, tabIndex = 0;
        String[] tab = new String[8];
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
        String textureLocation = tab[1];
        this.animation = Util.loadAnimation(new SpriteSheet(new Image((Main.isTest ? Util.RESOURCES_PATH_TEST: Util.RESOURCES_PATH) + textureLocation), Integer.valueOf(tab[2]), Integer.valueOf(tab[3])), Integer.valueOf(tab[4]), Integer.valueOf(tab[5]), Integer.valueOf(tab[6]), Integer.valueOf(tab[7]));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }
}
