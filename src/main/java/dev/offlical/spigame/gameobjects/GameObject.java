package dev.offlical.spigame.gameobjects;

import dev.offlical.spigame.collisions.CollisionBox;
import dev.offlical.spigame.render.GameRenderer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.map.MapPalette;


@Getter
@Setter
public class GameObject {


    public MapLocation location;
    public GameRenderer render;
    private CollisionBox collisionBox;
    private String name;

    public GameObject(int x, int y, GameRenderer render,CollisionBox collisionBox) {

        this.location = new MapLocation(x,y);
        this.render = render;
        this.collisionBox = collisionBox;
    }

    public GameObject(int x, int y,String name) {
        this.location = new MapLocation(x,y);
        this.name = name;
    }


    public void move(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
        if(x > 128) this.location.setX(1);
        if(y > 128) this.location.setY(1);
        render.getObjects().forEach(object -> {
            if(object.collidedWithObject(this) && object != this) onCollision(object);
            if(object.getCollisionBox().isColliding(this.collisionBox) && object != this) {
                System.out.println("box collision");
                onCollision(object);
            }
        });
        updateCollisionBox();
        draw();
    }


    public void draw() {
        MapLocation location = this.location;
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                render.getCanvas().setPixel((location.getX() + x),location.getY() + y,MapPalette.RED);
            }
        }
    }

    public boolean collidedWithObject(GameObject object) {
        return collisionBox.didCollide(object.getLocation());
    }

    public void onCollision(GameObject object) {
        Bukkit.getLogger().info("OBJECT (" + this.getName() + ") Collided with object: " + object.getName() + " at (x: " + this.getX() + " y: " + this.getY() + ")");
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

    }

    public GameRenderer getRender() {
        return render;
    }

    public void setRender(GameRenderer render) {
        this.render = render;
    }
}
