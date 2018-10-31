package com.thedoctor.game.hud;

import com.thedoctor.Util;
import com.thedoctor.game.objects.item.ItemStack;
import com.thedoctor.game.objects.entity.Player;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Inventory {

    private int startX, startY;
    private int height;
    private int width;
    private ItemStack inventory[][];

    private Player player;
    private boolean isFull;

    private int xTrigger, yTrigger;

    int selectedItem;
    int selectedSlot;

    public Inventory(int width, int height, Player player) {
        this.width = width;
        this.height = height;
        this.inventory = new ItemStack[width][height];
        this.player = player;
        this.isFull = false;
        this.xTrigger = -1;
        this.yTrigger = -1;
        this.selectedItem = 0;
        this.selectedSlot = 0;
    }

    public void addItem(int id) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (inventory[j][i] != null) {
                    if (inventory[j][i].getId() == id) {
                        if (inventory[j][i].addItem()) return;
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (inventory[j][i] == null) {
                    inventory[j][i] = new ItemStack(id);
                    return;
                }
            }
        }
        isFull = true;
    }

    public void addItemStack(int id, int count, int x, int y){
        if (inventory[x][y] == null) {
            inventory[x][y] = new ItemStack(id, count);
            return;
        }
        isFull = true;
    }

    public void render(Graphics g) throws SlickException {
        int buffer = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor((i == xTrigger && j == yTrigger ? Color.blue : Color.cyan));
                g.fillRect(startX + i * 40, startY + j * 40, 39, 39);
                g.setColor(Color.black);
                if (inventory[i][j] != null) {
                    inventory[i][j].render(g, startX + i * 40, startY + j * 40, 39, 39);
                }
                if (buffer == this.selectedSlot){
                    g.setColor(Color.red);
                    g.drawRect((startX + i * 40) - 1, (startY + j * 40) -1, 39, 39);
                }
                buffer++;
            }
        }
    }

    public ItemStack getItemStack(int x, int y){
        return inventory[x][y];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isFull() {
        return isFull;
    }

    public void clicked() {
        if (xTrigger != -1){
            for (int i = 0; i < width; i++){
                for (int j = 0; j < height; j++){
                    if (inventory[i][j] != null) inventory[i][j].setSelected(false);
                    this.selectedItem = 0;
                }
            }
            if (inventory[xTrigger][yTrigger] != null) {
                inventory[xTrigger][yTrigger].setSelected(true);
                this.selectedItem = inventory[xTrigger][yTrigger].getId();
            }
        }
    }

    public void update() {
        int mouseX = Mouse.getX();
        int mouseY = 720 - Mouse.getY();

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (Util.pointInTrigger(mouseX, mouseY, startX + i * 40, startY + j * 40, 39, 39)){
                    this.xTrigger = i;
                    this.yTrigger = j;
                    return;
                } else {
                    this.xTrigger = -1;
                    this.yTrigger = -1;
                }
            }
        }

    }

    public void setStart(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
    }

    public void mouseWheel(int value) {
        if (value < 0 && selectedSlot + 1 < width * height) selectedSlot++;
        else if (value > 0 && selectedSlot > 0) selectedSlot--;

        int[] tab = getTabPosition(selectedSlot);
        selectedItem = (inventory[tab[0]][tab[1]] != null ? inventory[tab[0]][tab[1]].getId() : 0);
    }

    public ItemStack getCurrentItemStack() {
        int[] tab = getTabPosition(selectedSlot);
        return inventory[tab[0]][tab[1]];
    }

    public int[] getTabPosition(int raw){
        int i = 0;
        int w = 0;
        int h = 0;
        while (i != raw){
            if (w > width) {
                w = 0;
                h++;
            } else w++;
            i++;
        }
        int[] tab = new int[2];
        tab[0] = w;
        tab[1] = h;
        return tab;
    }

}