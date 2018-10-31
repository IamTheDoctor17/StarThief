package com.thedoctor.game.objects.item;

import com.thedoctor.loading.Registry;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ItemStack {

    int maxStackSize = 16;
    int size;

    Item item;

    int id;
    boolean isSelected;

    public ItemStack(int id) {
        this.id = id;
        try {
            this.item = (Item) Registry.getItemByID(this.id).newInstance();
            this.maxStackSize = item.getMaxStackSize();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.size = 1;
        this.isSelected = false;
    }

    public ItemStack(int id, int size) {
        this.id = id;
        try {
            this.item = (Item) Registry.getItemByID(this.id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.size = size;
    }

    public void update(int delta){
        item.update(delta);
    }

    public boolean addItem() {
        if (maxStackSize >= size + 1) {
            size++;
            return true;
        } else return false;
    }

    public boolean addItem(int count) {
        if (maxStackSize >= size + count) {
            size++;
            return true;
        } else return false;
    }

    public void render(Graphics g, int startX, int startY, int width, int height) throws SlickException {
        item.getIcon().draw(startX, startY, width, height);
        g.drawString((size == 1 ? "" : (String.valueOf(size))), startX + width * 3 / 4, startY + height / 2);
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Item getItem() {
        return item;
    }
}
