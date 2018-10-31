package com.thedoctor.game.ai;

import com.thedoctor.game.objects.entity.EntityItem;
import com.thedoctor.game.objects.entity.SimpleEntity;
import com.thedoctor.game.objects.item.ItemHealingCoin;
import com.thedoctor.game.planet.Arena;
import org.newdawn.slick.SlickException;

import java.util.Random;

import static com.thedoctor.game.ai.States.REFLECTING;
import static com.thedoctor.game.ai.States.WAITING;

public class ArenaAI {

    int mobSpawningState, itemSpawningState;
    Arena arena;

    Random r;

    public ArenaAI(Arena arena) {
        this.mobSpawningState = WAITING;
        this.itemSpawningState = WAITING;
        this.arena = arena;
        this.r = new Random();
    }

    public void update() throws SlickException {
        switch (mobSpawningState) {
            case WAITING: {
                if (arena.entities.size() < 10) {
                    mobSpawningState = REFLECTING;
                }
                break;
            }
            case REFLECTING: {
                int toSpawn = 10 - arena.entities.size();
                for (int i = 0; i < toSpawn; i++) {
                    float rx, ry;
                    Random r = new Random();
                    rx = (15 + r.nextInt(70)) * 32;
                    ry = (15 + r.nextInt(70)) * 32;
                    arena.addEntity(new SimpleEntity(rx, ry, arena));
                }
                mobSpawningState = WAITING;
                break;
            }
        }

        switch (itemSpawningState) {
            case WAITING: {
                if (r.nextInt(800) == 1 && arena.items.size() < 10) {
                    itemSpawningState = REFLECTING;
                }
                break;
            }
            case REFLECTING: {
                float rx, ry;
                Random r = new Random();
                rx = (15 + r.nextInt(70)) * 32;
                ry = (15 + r.nextInt(70)) * 32;
                arena.addItem(new EntityItem(new ItemHealingCoin(), rx, ry));
                itemSpawningState = WAITING;
                break;
            }
        }
    }
}
