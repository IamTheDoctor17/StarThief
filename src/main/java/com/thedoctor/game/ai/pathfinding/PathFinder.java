package com.thedoctor.game.ai.pathfinding;

import com.thedoctor.game.objects.Object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {



    public List<Node> findPath(Object objectStart, Object objectEnd){
        return findPath(objectStart.getLevel().getLogicMap(), new Node(objectStart.getMapX(), objectStart.getMapY()), new Node(objectEnd.getMapX(), objectEnd.getMapY()));
    }

    public List<Node> findPath(LogicMap map, Node start, Node end){
        List<Node> open = new ArrayList<>();
        List<Node> closed = new LinkedList<>();
        open.add(start);

        while (open.size() > 0){

            Node current = open.get(0);
            for (int i = 1; i < open.size(); i++){
                if (open.get(i).getfCost() < current.getfCost()){
                    current = open.get(i);
                }
            }

            open.remove(current);
            closed.add(current);


            if (current.getPosX() == end.getPosX() && current.getPosY() == end.getPosY()) {
                return retracePath(start, closed);
            }

            for (Node node : map.getNeighbours(current)){
                if (!node.isWalkable() || closed.contains(node)) continue;
                int costToNeighbour = current.getgCost() + getDistance(node, current);
                if (costToNeighbour < node.getgCost() || !open.contains(node)) {
                    node.setParent(current);
                    node.setgCost(current.getgCost() + getDistance(node, current));
                    node.sethCost(getDistance(node, end));

                    if (!open.contains(node)) open.add(node);
                }
            }
        }
        return null;
    }

    private List<Node> retracePath(Node start, List<Node> closedList){
        List<Node> path = new ArrayList<>();
        Node current = closedList.get(closedList.size() - 1);
        while (current.getPosX()!=start.getPosX() || current.getPosY()!=start.getPosY()){
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    private int getDistance(Node nodeA, Node nodeB){
        int distX = Math.abs(nodeA.getPosX() - nodeB.getPosX());
        int distY = Math.abs(nodeA.getPosY() - nodeB.getPosY());

        if (distX > distY) return 14 * distY + 10 * (distX - distY);
        return 14 * distX + 10 * (distY - distX);
    }
}
