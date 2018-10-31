package com.thedoctor.scene.load;

import com.thedoctor.Main;
import com.thedoctor.Util;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LoadImage implements LoadFile{

    String name, textureLocation;
    Image image;

    public LoadImage(String args) throws SlickException {
        this.name = args.substring(0, args.indexOf(" "));
        this.textureLocation = args.substring(args.indexOf(" ") + 1);
        this.image = new Image((Main.isTest ? Util.RESOURCES_PATH_TEST : Util.RESOURCES_PATH) + textureLocation);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public Animation getAnimation() {
        return null;
    }
}
