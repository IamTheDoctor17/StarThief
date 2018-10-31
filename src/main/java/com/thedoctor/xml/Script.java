package com.thedoctor.xml;

import com.thedoctor.scene.actions.*;
import com.thedoctor.scene.load.LoadAnimation;
import com.thedoctor.scene.load.LoadFile;
import com.thedoctor.scene.load.LoadImage;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Script {

    File file;
    List<Action> actions;
    List<LoadFile> load;
    String fullFile;

    int currentAction;
    boolean isEnded;

    public Script(String path) {
        this.file = new File(path);
        this.fullFile = "";
        this.load = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public void load() throws SlickException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            this.actions = new ArrayList<>();
            while (true) {
                String str = reader.readLine();
                if (str == null) break;

                fullFile += str;
            }

            setLoad();
            setActions();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        currentAction = 0;
        for (Action action : actions) {
            action.link(this);
        }
        actions.get(0).setWorking(true);
    }

    public void update(int delta) {
        for (Action action : actions) {
            if (action.isWorking()) action.update(delta);
        }
    }

    public void render(Graphics g) {
        for (Action action : actions) {
            if (action.isWorking()) action.render(g);
        }
    }

    private void setLoad() throws SlickException {
        int iStart = 0, iEnd = 0;
        while (fullFile.charAt(iStart) != '{') iStart++;
        while (fullFile.charAt(iEnd) != '}') iEnd++;

        String load = fullFile.substring(iStart + 1, iEnd);
        load = load.replace("\t", "");

        String line = "";
        for (char c : load.toCharArray()) {
            if (c == ';') {
                this.load.add(getLoadFile(line));
                line = "";
            } else line += c;
        }
    }

    private void setActions() {
        int iStart = 0, iEnd = 0;
        String load = fullFile.substring(fullFile.indexOf("script{"));
        while (load.charAt(iStart) != '{') iStart++;
        while (load.charAt(iEnd) != '}') iEnd++;

        load = load.substring(iStart + 1, iEnd);
        load = load.replace("\t", "");

        String line = "";
        for (char c : load.toCharArray()) {
            if (c == ';') {
                this.actions.add(getAction(line));
                line = "";
            } else line += c;
        }
    }

    private static LoadFile getLoadFile(String line) throws SlickException {
        switch (line.charAt(0)) {
            case 'i': {
                return new LoadImage(line.substring(4));
            }
            case 'a': {
                return new LoadAnimation(line.substring(5));
            }
        }
        return null;
    }

    private static Action getAction(String line) {
        switch (line.charAt(0)) {
            case 'm': {
                return new AMove(line.substring(5));
            }
            case 'p': {
                return new APlace(line.substring(6));
            }
            case 'w': {
                return new AWait();
            }
            case 's': {
                return new AStart();
            }
            case 'e': {
                return new AEnd();
            }
        }
        return null;
    }

    public LoadFile getLinkedFile(String name) {
        for (LoadFile file : load) {
            if (file.getName().equals(name)) return file;
        }
        return null;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void keyReleased() {
        for (Action action : actions) {
            if (action.isWorking()) {
                action.keyReleased();
                break;
            }
        }
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }
}