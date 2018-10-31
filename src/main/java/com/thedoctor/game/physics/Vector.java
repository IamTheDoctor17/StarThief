package com.thedoctor.game.physics;

import com.thedoctor.game.objects.entity.Entity;

public abstract class Vector {

    float x, y, deg, norm;

    Entity entity;

    boolean isEnded;

    public Vector(float deg, Entity entity) {
        this.deg = deg;
        this.x = (float) Math.cos(deg) * 10;
        this.y = (float) Math.sin(deg) * 10;
        this.norm = (float) Math.hypot(x, y);
        this.entity = entity;
    }

    public float getX(int delta) {
        return x *delta;
    }

    public float getY(int delta) {
        return y * delta;
    }

    public boolean isEnded() {
        return isEnded;
    }
}
