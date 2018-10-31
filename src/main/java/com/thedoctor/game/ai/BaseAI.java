package com.thedoctor.game.ai;

import com.thedoctor.game.ai.pathfinding.Node;
import com.thedoctor.game.ai.pathfinding.PathFinder;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import java.util.List;

import static com.thedoctor.game.ai.States.IDLE;

public abstract class BaseAI {

    List<Node> path;
    Node currentNode, lastNode;

    PathFinder pathFinder;
    int index;
    boolean hasPath;
    float dx, dy;

    int aiState;

    public BaseAI() {
        this.pathFinder = new PathFinder();
    }

    public void setPath(List<Node> path) {
        if (path.size() != 0) {
            this.index = 0;
            this.path = path;
            this.currentNode = path.get(index);
            this.hasPath = true;
        }
    }

    void changeDirection(int xa, int ya, int xb, int yb) {
        this.dx = Integer.compare(xb, xa);
        this.dy = Integer.compare(yb, ya);
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        if (path != null) {
            for (Node n : path) {
                g.fillRect(n.getPosX() * 32, n.getPosY() * 32, 32, 32);
            }
        }
    }

    void updateNode(){
        if (index +  1 < path.size()) {
            index++;
            this.lastNode = currentNode;
            this.currentNode = path.get(index);
            changeDirection(lastNode.getPosX(), lastNode.getPosY(), currentNode.getPosX(), currentNode.getPosY());
        } else {
            this.aiState = IDLE;
            this.hasPath = false;
        }
    }
}
