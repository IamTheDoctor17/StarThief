package com.thedoctor.game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {

    private Object object;
    private float xCamera, yCamera;

    public Camera(Object object) {
        this.object = object;
    }

    public void update(GameContainer container){

        int w = container.getWidth() / 4;
        if (this.object.getCenterX() > this.xCamera + w) {
            this.xCamera = this.object.getCenterX() - w;
        } else if (this.object.getCenterX() < this.xCamera - w) {
            this.xCamera = this.object.getCenterX() + w;
        }
        int h = container.getHeight() / 6;
        if (this.object.getCenterY() > this.yCamera + h) {
            this.yCamera = this.object.getCenterY() - h;
        } else if (this.object.getCenterY() < this.yCamera - h) {
            this.yCamera = this.object.getCenterY() + h;
        }
    }


    public void render(GameContainer container, Graphics g){
        g.translate(container.getWidth() / 2 - (int) this.xCamera, container.getHeight() / 2 - (int) this.yCamera);
    }

    public float getxCamera() {
        return xCamera;
    }

    public float getyCamera() {
        return yCamera;
    }
}
