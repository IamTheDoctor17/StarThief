package com.thedoctor.game.save;

import com.thedoctor.Main;
import com.thedoctor.Util;
import com.thedoctor.game.hud.Inventory;
import com.thedoctor.game.objects.Object;
import com.thedoctor.game.objects.entity.Entity;
import com.thedoctor.game.objects.entity.Player;
import com.thedoctor.game.objects.item.ItemStack;
import com.thedoctor.game.planet.Level;
import com.thedoctor.game.planet.Planet;
import com.thedoctor.gamestates.StateNewGame;
import com.thedoctor.gamestates.StatePlanet;
import com.thedoctor.gamestates.StateShip;
import com.thedoctor.loading.Registry;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import static com.thedoctor.loading.Registry.player;

public class GameSave {

    String saveName;

    File file;
    Document doc;

    Element meta;
    Element game;


    public GameSave(String saveName) {
        this.saveName = (Main.isTest ? Util.RESOURCES_PATH_TEST + "saves/" : Util.RESOURCES_PATH + "saves/") + saveName;
        file = new File(this.saveName);
    }

    public void loadSave(StateBasedGame stateBasedGame) throws SlickException {

        Set<String> keys = Registry.getPlanets().keySet();
        for (String key : keys) {
            Registry.getPlanet(key).setPlayed(false);
        }

        if (file.exists()) {


            try {
                SAXReader reader = new SAXReader();
                doc = reader.read(file);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            Element root = doc.getRootElement();

            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element e = (Element) it.next();
                switch (e.getName()) {
                    case "game": {
                        this.game = e;
                        break;
                    }
                    case "meta": {
                        this.meta = e;
                        break;
                    }
                }
            }

            for (Iterator it = meta.elementIterator(); it.hasNext(); ) {
                Element e = (Element) it.next();

                switch (e.getName()) {
                    case "state": {
                        switch (Integer.parseInt(e.getText())) {
                            case StatePlanet.ID: {
                                stateBasedGame.addState(new StatePlanet());
                                stateBasedGame.enterState(StatePlanet.ID);
                                Registry.currentState = StatePlanet.ID;
                                break;
                            }
                            case StateShip.ID: {
                                stateBasedGame.addState(new StateShip());
                                stateBasedGame.enterState(StateShip.ID);
                                Registry.currentState = StateShip.ID;
                                break;
                            }
                        }
                    }
                }
            }

            for (Iterator it = game.elementIterator(); it.hasNext(); ) {
                Element e = (Element) it.next();
                switch (e.getName()) {
                    case "player": {
                        for (Iterator i = e.elementIterator(); i.hasNext(); ) {
                            Element f = (Element) i.next();
                            switch (f.getName()) {
                                case "posx": {
                                    player.setPosX(Float.parseFloat(f.getText()));
                                    break;
                                }
                                case "posy": {
                                    player.setPosY(Float.parseFloat(f.getText()));
                                    break;
                                }
                                case "width": {
                                    player.setWidth(Integer.parseInt(f.getText()));
                                    break;
                                }
                                case "height": {
                                    player.setHeight(Integer.parseInt(f.getText()));
                                    break;
                                }
                                case "inventory": {
                                    int x = 0;
                                    for (Iterator iiter = f.elementIterator(); iiter.hasNext(); ) {
                                        Element k = (Element) iiter.next();
                                        int y = 0;
                                        for (Iterator caseIter = k.elementIterator(); caseIter.hasNext(); ) {
                                            Element c = (Element) caseIter.next();
                                            if (!c.getText().equals("null")) {
                                                int id = 0;
                                                int count = 0;
                                                for (Iterator contentIter = c.elementIterator(); contentIter.hasNext(); ) {
                                                    Element content = (Element) contentIter.next();
                                                    switch (content.getName()) {
                                                        case "id": {
                                                            id = Integer.parseInt(content.getText());
                                                            break;
                                                        }
                                                        case "count": {
                                                            count = Integer.parseInt(content.getText());
                                                            break;
                                                        }
                                                    }
                                                }
                                                player.inventory.addItemStack(id, count, y, x);
                                            }
                                            y++;
                                        }
                                        x++;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "planets": {
                        for (Iterator planetsIter = e.elementIterator(); planetsIter.hasNext(); ) {
                            Element planet = (Element) planetsIter.next();

                            Registry.addPlanet(new Planet(planet));
                            if (planet.attributeValue("isCurrent").equals("true"))
                                Registry.currentPlanet = planet.attributeValue("name");

                            for (Iterator levelsIter = planet.elementIterator(); levelsIter.hasNext(); ) {
                                Element level = (Element) levelsIter.next();

                                for (Iterator objectsIter = level.elementIterator(); objectsIter.hasNext(); ) {
                                    Element object = (Element) objectsIter.next();

                                    for (Iterator typesIter = object.elementIterator(); typesIter.hasNext(); ) {
                                        Element type = (Element) typesIter.next();

                                        try {
                                            Entity entity = (Entity) Registry.getEntityByID(type.attributeValue("id")).getConstructor(Element.class, Level.class, Planet.class).newInstance(type, Registry.getPlanet(planet.attributeValue("name")).getLevel(Character.getNumericValue(level.getName().charAt(5))), Registry.getPlanet(planet.attributeValue("name")));
                                            Registry.getPlanet(planet.attributeValue("name")).getLevel(Character.getNumericValue(level.getName().charAt(5))).addEntity(entity);
                                        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else {
            System.out.println("no save found");
            Registry.currentState = StateNewGame.ID;
            stateBasedGame.addState(new StateNewGame());
            stateBasedGame.enterState(StateNewGame.ID);
        }
    }

    public void save(StateBasedGame stateBasedGame) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("savegame");

        Element meta = root.addElement("meta");

        meta.addElement("state").setText(String.valueOf(stateBasedGame.getCurrentStateID()));
        meta.addElement("currentplannet").setText(Registry.currentPlanet);

        Element game = root.addElement("game");

        /*
            PLAYER
         */
        Element player = game.addElement("player");

        Element posX = player.addElement("posx").addText(String.valueOf(Registry.player.getX()));
        Element posY = player.addElement("posy").addText(String.valueOf(Registry.player.getY()));
        Element width = player.addElement("width").addText(String.valueOf(Registry.player.getWidth()));
        Element height = player.addElement("height").addText(String.valueOf(Registry.player.getHeight()));

        player = setInventory(player, Registry.player.inventory);

        /*
            PLANETS
         */
        Element planets = game.addElement("planets");

        Set<String> keys = Registry.getPlanets().keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            Planet p = Registry.getPlanet(it.next());
            if (p.hasBeenPlayed()) {
                Element planet = planets.addElement("planet_" + p.getName())
                        .addAttribute("name", p.getName())
                        .addAttribute("level", String.valueOf(p.getIndex()))
                        .addAttribute("isCurrent", (p.getName().equals(Registry.currentPlanet) ? "true" : "false"));

                for (int i = 0; i < p.getLevelsCount(); i++) {
                    Element level = planet.addElement("level" + i)
                            .addAttribute("mapurl", p.getLevel(i).getMapPath())
                            .addAttribute("playerStartX", String.valueOf(p.getLevel(i).playerStartX))
                            .addAttribute("playerStartY", String.valueOf(p.getLevel(i).playerStartY));
                    Element objects = level.addElement("entities");

                    for (Object o : p.getLevel(i).entities) {
                        if (!(o instanceof Player)) {
                            Element object = objects.addElement("entity")
                                    .addAttribute("id", o.getName());

                            Element data = object.addElement("data");

                            Element posXo = data.addElement("posx").addText(String.valueOf(o.getX()));
                            Element posYo = data.addElement("posy").addText(String.valueOf(o.getY()));
                            Element widtho = data.addElement("width").addText(String.valueOf(o.getWidth()));
                            Element heighto = data.addElement("height").addText(String.valueOf(o.getHeight()));
                        }
                    }
                }
            }
        }


        try {
            FileWriter out = new FileWriter(saveName);
            doc.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Element setInventory(Element player, Inventory inventory) {

        Element iv = player.addElement("inventory");
        iv.addAttribute("width", String.valueOf(inventory.getWidth()));
        iv.addAttribute("height", String.valueOf(inventory.getHeight()));
        for (int i = 0; i < inventory.getHeight(); i++) {
            Element line = iv.addElement("line" + i);
            for (int j = 0; j < inventory.getWidth(); j++) {
                Element e = line.addElement("case" + j);
                ItemStack itemStack = inventory.getItemStack(j, i);
                if (itemStack != null) {
                    e.addElement("id").setText(String.valueOf(itemStack.getId()));
                    e.addElement("count").setText(String.valueOf(itemStack.getSize()));
                } else e.setText("null");
            }
        }
        return iv;
    }
}
