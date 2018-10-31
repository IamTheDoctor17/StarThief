package com.thedoctor.game.ai;

import com.thedoctor.Util;
import com.thedoctor.game.ai.pathfinding.Node;
import com.thedoctor.game.ai.pathfinding.PathFinder;
import com.thedoctor.game.objects.entity.SimpleEntity;
import com.thedoctor.loading.Registry;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

import java.util.List;

import static com.thedoctor.Util.getDistance;
import static com.thedoctor.game.ai.States.*;

public class SimpleMobAI extends BaseAI{

    SimpleEntity entity;

    int huntCooldown;

    public SimpleMobAI(SimpleEntity entity) {
        this.entity = entity;
        this.aiState = IDLE;
        this.huntCooldown = 0;
    }

    public void update(int delta) {

        if (Keyboard.isKeyDown(Input.KEY_Y)) aiState = TARGETING;

        if (getDistance(entity, Registry.player) <= 400 && aiState == IDLE) {
            this.aiState = TARGETING;
        }

        if (this.huntCooldown >= 2000 && getDistance(entity, Registry.player) <= 400){
            this.aiState = TARGETING;
            this.huntCooldown = 0;
        } else huntCooldown += delta;

        switch (aiState) {
            case IDLE: {
                break;
            }
            case TARGETING: {
                this.setPath(pathFinder.findPath(entity, Registry.player));
                this.aiState = HUNTING;
                break;
            }
            case HUNTING: {
                if (hasPath) {
                    if (Util.pointInTrigger((int) entity.getCenterX(), (int) entity.getCenterY(), currentNode.getPosX() * entity.getMap().getTileWidth() - 30, currentNode.getPosY() * entity.getMap().getTileHeight() - 30, 60, 60)) {
                        updateNode();
                    } else {
                        if (this.aiState > IDLE && getDistance(entity, Registry.player) > 550) {
                            this.aiState = IDLE;
                            this.hasPath = false;
                        }
                        entity.setNextX((entity.getX() + delta * dx * entity.getSpeed()));
                        entity.setNextY((entity.getY() + delta * dy * entity.getSpeed()));
                    }
                }
                if (getDistance(entity, Registry.player) < entity.getRange()){
                    entity.setHitting(true);
                }
            }
        }
    }

    @Override
    public void setPath(List<Node> path) {
        super.setPath(path);
        if (path.size() != 0) {
            changeDirection(entity.getMapX(), entity.getMapY(), currentNode.getPosX(), currentNode.getPosY());
        }
    }

}
