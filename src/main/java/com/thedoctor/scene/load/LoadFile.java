package com.thedoctor.scene.load;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public interface LoadFile {

    String getName();

    Image getImage();

    Animation getAnimation();
}
