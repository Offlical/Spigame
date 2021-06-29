package me.danielml.spigame.gameobjects;

import me.danielml.spigame.api.SpigameAPI;
import me.danielml.spigame.modifiers.Modifier;
import me.danielml.spigame.modifiers.collisions.CollisionBox;
import me.danielml.spigame.render.GameCanvas;
import me.danielml.spigame.render.ObjectRenderer;
import me.danielml.spigame.render.Texture;

import java.util.ArrayList;


public class GameObject {


    protected MapTransform location;
    private ObjectRenderer renderer;
    private ArrayList<GameObject> children;
    private ArrayList<Modifier> modifiers;
    private final int id;

    public GameObject(MapTransform transform) {
        this.location = transform;
        this.id = SpigameAPI.assignGameObjectID();
        this.children = new ArrayList<>();
        this.modifiers = new ArrayList<>();
    }

    public void update() {
        for(GameObject child : children)
            child.update();
        for(Modifier m : modifiers)
            m.update(this);
    }

    public void render(GameCanvas canvas) {
        renderer.render(canvas);
    }

    public void addModifier(Modifier modifier) {
        modifier.init(this);
        this.modifiers.add(modifier);
    }

    public void addChild(GameObject child) {
        this.children.add(child);
        child.update();
    }

    public void setRenderTexture(Texture texture) {
        renderer = new ObjectRenderer(this,texture);
    }

    public Modifier getModifier(Modifier o) {
        for(Modifier modifier : modifiers) {
            if(modifier.getClass().equals(o.getClass()))
                return modifier;
        }
        return null;
    }

    public int getId() {
        return id;
    }


    public MapTransform getLocation() {
        return location;
    }

    public ArrayList<GameObject> getChildrenObjects() {
        return children;
    }

    public ArrayList<Modifier> getModifiers() {
        return modifiers;
    }

    public Texture getRenderTexture() { return renderer == null ? null : renderer.getTexture(); }

    public ObjectRenderer getRenderer() {
        return renderer;
    }
}
