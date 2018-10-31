package com.thedoctor.game.ai.pathfinding;

public class Node {

    private int posX, posY;
    private boolean walkable;
    private int gCost, hCost;

    private Node parent;

    boolean isPath;

    public Node(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.walkable = true;
    }

    public Node(int posX, int posY, boolean walkable) {
        this.posX = posX;
        this.posY = posY;
        this.walkable = walkable;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getfCost(){
        return hCost + gCost;
    }

    @Override
    public String toString() {
        return posX + " " + posY;
    }
}
