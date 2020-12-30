package dev.offlical.spigame.gameobjects;

import dev.offlical.spigame.GameInstance;
import dev.offlical.spigame.Spigame;
import dev.offlical.spigame.api.SpigameAPI;
import dev.offlical.spigame.collisions.CollisionBox;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.map.MapPalette;

import java.util.function.Consumer;


public class GameObject {


    @Getter @Setter   public MapLocation location;
    @Getter @Setter  private CollisionBox collisionBox;
    @Getter @Setter private String name;

    @Setter private GameInstance parentInstance;

    @Setter @Getter private int id;

    public GameObject(int x, int y,String name) {
        this.location = new MapLocation(x,y);
        this.name = name;
        this.collisionBox = new CollisionBox(1);

        this.id = SpigameAPI.gameObjectID;
        SpigameAPI.gameObjectID++;
    }


    /*

    Move object to a certain coordinate on the canvas

     */
    public void move(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
        if(x > 128) this.location.setX(1);
        if(y > 128) this.location.setY(1);
        updateCollisionBox();
        parentInstance.getObjects().forEach(object -> {
            if(object != this && this.collidedWithObject(object)) {
                this.onCollision(object);
            }
        });
        parentInstance.render(this);
    }

    public byte[][] draw(byte[][] canvas) {
        MapLocation location = this.location;
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                canvas[location.getY() + y][location.getX() + x] = MapPalette.BROWN;
            }
        }
        Spigame.getSpigameLogger().info("[RENDER] " + this.getName() + " rendered at " + this.getX() + "," + this.getY());
        return canvas;
    }


    public boolean collidedWithObject(GameObject object) {
        return collisionBox.didCollide(object.getLocation());
    }

    public void onCollision(GameObject object) {
        Spigame.getSpigameLogger().info("OBJECT (" + this.getName() + " ID: " + this.getId() + ") Collided with object: " + object.getName() + " at (x: " + this.getX() + " y: " + this.getY() + ")");
    }

    public int getX() {
        return this.location.getX();
    }


    public int getY() {
        return this.location.getY();
    }

    public void setCollisionBox(CollisionBox collisionBox) {
        this.collisionBox = collisionBox;
        updateCollisionBox();
    }

    public void updateCollisionBox() {

        // updates max and min coordinates to fit the new location
        this.collisionBox.setMaxX(getX() + collisionBox.getWidth());
        this.collisionBox.setMinX(getX() - collisionBox.getWidth());

        this.collisionBox.setMaxY(getY() + collisionBox.getHeight());
        this.collisionBox.setMinY(getY() - collisionBox.getHeight());
        Spigame.getSpigameLogger().info("[COLLISSION] Updated Collision box - " + this.getName() + " ID: " + this.getId());
    }



}
