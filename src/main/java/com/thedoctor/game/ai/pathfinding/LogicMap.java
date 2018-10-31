package com.thedoctor.game.ai.pathfinding;

import com.thedoctor.game.objects.Object;
import com.thedoctor.game.planet.PlayableMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.List;

public class LogicMap {

    int width, height;

    public Node[][] nodes;

    public LogicMap(TiledMap map) {
        this.width = map.getWidth();
        this.height = map.getHeight();

        nodes = new Node[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                nodes[i][j] = new Node(i, j, !isLogicTile(map, i, j));
            }
        }
    }

    private boolean isLogicTile(TiledMap map, int x, int y) {
        Image tile = map.getTileImage(x, y, map.getLayerIndex("logic"));
        return tile != null;
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print((nodes[x][y].isPath ? "X " : (nodes[x][y].isWalkable() ? "- " : "| ")));
            }
            System.out.println();
        }
    }

    List<Node> getNeighbours(Node node) {
        List<Node> nodeList = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                int checkX = node.getPosX() + x;
                int checkY = node.getPosY() + y;

                if (checkX >= 0 && checkX < width && checkY >= 0 && checkY < height) {
                    nodeList.add(nodes[checkX][checkY]);
                }
            }
        }
        return nodeList;
    }

    public static Node getNode(PlayableMap map, Object object) {
        int x = (int) (object.getX() / map.getMap().getTileWidth());
        int y = (int) (object.getY() / map.getMap().getTileHeight());
        return map.getLogicMap().nodes[x][y];
    }

    public static Node getNode(PlayableMap map, int mouseX, int mouseY) {
        int x = (int) (( map.camera.getxCamera() + mouseX ) / map.getMap().getTileWidth());
        int y = (int) (( map.camera.getyCamera() + mouseY ) / map.getMap().getTileHeight());
        return map.getLogicMap().nodes[x][y];
    }
}