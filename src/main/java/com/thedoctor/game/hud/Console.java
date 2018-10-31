package com.thedoctor.game.hud;

import com.thedoctor.game.objects.entity.Entity;
import com.thedoctor.game.planet.Level;
import com.thedoctor.loading.Registry;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Console {

    GameContainer gc;
    String txt;
    List<String> commands;

    boolean keyEnter;

    public Console(GameContainer gc) {
        this.gc = gc;
        this.txt = "";
        this.keyEnter = false;
    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, gc.getHeight() - 30, gc.getWidth(), 30);
        g.setColor(Color.white);
        g.drawString(txt, 10, gc.getHeight() - 25);
    }

    public void update() {
        transformTxt();

        if (keyEnter){
            keyEnter = false;
            txt = "";
            if (commands.size() != 0) {
                switch (commands.get(0)) {
                    case "spawn": {
                        try {
                            Registry.getPlanet(Registry.currentPlanet).getCurrentLevel().addEntity((Entity) Registry.getEntityByID(commands.get(1)).getConstructor(Float.class, Float.class, Level.class).newInstance(Float.parseFloat(commands.get(2)), Float.parseFloat(commands.get(3)), Registry.getPlanet(Registry.currentPlanet).getCurrentLevel()));
                        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void keyReleased(int keyCode, char c) {
        if (keyCode == Input.KEY_BACK && txt.toCharArray().length > 0) {
            txt = txt.substring(0, txt.toCharArray().length - 1);
        } else if (Character.isJavaIdentifierPart(c) || Input.KEY_SPACE == keyCode) this.txt += c;

        if (keyCode == Input.KEY_ENTER) keyEnter = true;

    }

    private void transformTxt() {
        commands = new ArrayList<>();
        String str = "";
        for (int i = 0; i < txt.toCharArray().length; i++) {
            char c = txt.toCharArray()[i];
            str += c;
            if (c == ' '){
                str = str.substring(0, str.toCharArray().length - 1);
                commands.add(str);
                str = "";
            } else if (i + 1 == txt.toCharArray().length){
                commands.add(str);
                str = "";
            }
        }
    }
}