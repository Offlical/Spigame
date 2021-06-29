package me.danielml.spigame.render;

import me.danielml.spigame.gameobjects.GameObject;
import me.danielml.spigame.gameobjects.MapTransform;


public class ObjectRenderer {

    private Texture texture;
    private GameObject object;

    public ObjectRenderer(GameObject o, Texture texture) {
        this.object = o;
        this.texture = texture;
    }

    public void render(GameCanvas canvas) {
        texture.drawRelativeToLoc(object.getLocation(),canvas);
    }

    public Texture getTexture() {
        return texture;
    }
}
