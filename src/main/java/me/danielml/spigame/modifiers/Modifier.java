package me.danielml.spigame.modifiers;

import me.danielml.spigame.gameobjects.GameObject;
import me.danielml.spigame.gameobjects.MapTransform;

public interface Modifier {


    void init(GameObject o);
    void update(GameObject o);
    MapTransform getTransform();
    GameObject getParentObject();

}
