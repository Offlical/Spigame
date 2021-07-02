package me.danielml.spigame.render.texture;

import me.danielml.spigame.gameobjects.GameObject;
import me.danielml.spigame.render.GameCanvas;
import me.danielml.spigame.render.texture.Texture;


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
