package com.thedoctor;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import com.thedoctor.loading.ThreadLoading;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.lang.reflect.Field;

public class Main {

    public static boolean isTest;

    public static void main(String[] args) throws SlickException {

        System.setProperty("java.library.path", "natives");
        resetJavaLibraryPath();

        isTest = args[0].equals("true");

        try {
            if (SteamAPI.init()) {
                System.out.println("Steam API loaded");
            } else System.out.println("Couldn't load Steam API");
        } catch (SteamException e) {
            e.printStackTrace();
        }

        new ThreadLoading(isTest).start();

        new AppGameContainer(new StarThief("Star Thief - 0.1"), 1280, 720, false).start();

    }

    public static void resetJavaLibraryPath() {
        synchronized (Runtime.getRuntime()) {
            try {
                Field field = ClassLoader.class.getDeclaredField("usr_paths");
                field.setAccessible(true);
                field.set(null, null);

                field = ClassLoader.class.getDeclaredField("sys_paths");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
