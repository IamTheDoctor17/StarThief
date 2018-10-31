package com.thedoctor.loading;

import com.thedoctor.Util;
import com.thedoctor.game.objects.entity.Bullet;
import com.thedoctor.game.objects.entity.EntityItem;
import com.thedoctor.game.objects.entity.SimpleEntity;
import com.thedoctor.game.objects.item.ItemCoin;
import com.thedoctor.game.objects.item.ItemHealingCoin;
import com.thedoctor.game.objects.item.ItemSimpleGun;
import com.thedoctor.game.objects.item.ItemWoodenSword;
import com.thedoctor.game.planet.Planet;
import org.newdawn.slick.SlickException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.thedoctor.Util.RESOURCES_PATH;
import static com.thedoctor.Util.RESOURCES_PATH_TEST;

public class ThreadLoading extends Thread {

    public static boolean isLoadingEnded = false;

    private boolean isTest;

    public ThreadLoading(boolean isTest) {
        this.isTest = isTest;
    }

    @Override
    public void run() {

        try {
            Registry.addEntity(EntityItem.ID, EntityItem.class);

            Registry.addEntity(SimpleEntity.ID, SimpleEntity.class);
            Registry.addEntity(Bullet.ID, Bullet.class);

            Registry.addItem(ItemCoin.ID, ItemCoin.class);
            Registry.addItem(ItemWoodenSword.ID, ItemWoodenSword.class);
            Registry.addItem(ItemHealingCoin.ID, ItemHealingCoin.class);
            Registry.addItem(ItemSimpleGun.ID, ItemSimpleGun.class);

            loadPlanets();

            String OS = System.getProperty("os.name").toLowerCase();

            if (OS.contains("win")){
                Util.setKeyboard("qwerty");
            } else if (OS.contains("mac")) Util.setKeyboard("azerty");

        } catch (SlickException e) {
            e.printStackTrace();
        }

        isLoadingEnded = true;
    }

    private void loadPlanets() throws SlickException {

        File file = new File((isTest ? RESOURCES_PATH_TEST : RESOURCES_PATH) + "planets/planets.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                Registry.addPlanet(new Planet((isTest ? RESOURCES_PATH_TEST : RESOURCES_PATH) + "planets/" + line + "/planet.xml"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}