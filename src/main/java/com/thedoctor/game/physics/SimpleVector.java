package com.thedoctor.game.physics;

import com.thedoctor.game.objects.entity.Entity;

public class SimpleVector extends Vector{

    float buffer;

    public SimpleVector(float deg, Entity entity, float buffer) {
        super(deg, entity);
        this.buffer = buffer + 0.1f;
        this.norm = (float) (Math.hypot(x, y) * buffer);
    }

    @Override
    public float getX(int delta) {
        this.x /= 1 + buffer * delta * 0.01;
        return this.x;
    }

    @Override
    public float getY(int delta) {
        this.y /=  1 + buffer * delta * 0.01;
        return this.y;
    }

    @Override
    public boolean isEnded() {
        if (( x < 1 && x > 1) ||
                ( y < 1 && y > 1)) {
            this.isEnded = true;
        }
        return isEnded;
    }
}
