package com.thedoctor.game.ai;

import com.thedoctor.Util;
import com.thedoctor.game.ai.pathfinding.LogicMap;
import com.thedoctor.game.objects.space.SpaceShip;
import com.thedoctor.loading.Registry;
import org.lwjgl.input.Mouse;

import static com.thedoctor.Util.getDistance;

public class ShipAI extends BaseAI{

    private static final int IDLE = 0;
    private static final int TARGETING = 1;
    private static final int MOVING = 2;

    SpaceShip spaceShip;

    public ShipAI(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
        this.aiState = 0;
    }

    public void update(int delta){
        switch (aiState){
            case IDLE:{
                break;
            }
            case TARGETING:{
                aiState = MOVING;
                setPath(pathFinder.findPath(spaceShip.getLevel().getLogicMap(), LogicMap.getNode(spaceShip.getLevel(), spaceShip), LogicMap.getNode(spaceShip.getLevel(), Mouse.getX(), 720 - Mouse.getY())));
                break;
            }
            case MOVING:{
                System.out.println(spaceShip.getMapX());
                System.out.println(path.get(path.size() - 1).getPosX());
                System.out.println(spaceShip.getMapY());
                System.out.println(path.get(path.size() - 1).getPosY());
                if (spaceShip.getMapX() == path.get(path.size() - 1).getPosX() && spaceShip.getMapY() == path.get(path.size() - 1).getPosX()){
                    updateNode();
                } else {
                    spaceShip.setNextX((spaceShip.getX() + delta * dx * 0.5f));
                    spaceShip.setNextY((spaceShip.getY() + delta * dy * 0.5f));
                }
            }
        }
    }

    public void clicked(){
        this.aiState = TARGETING;
    }
}
