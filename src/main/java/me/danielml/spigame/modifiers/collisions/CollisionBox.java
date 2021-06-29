package me.danielml.spigame.modifiers.collisions;

import me.danielml.spigame.gameobjects.GameObject;
import me.danielml.spigame.gameobjects.MapTransform;
import lombok.Getter;
import lombok.Setter;
import me.danielml.spigame.modifiers.Modifier;

import java.io.StringReader;

@Getter
@Setter
public class CollisionBox implements Modifier {


    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    private int width;
    private int height;

    private MapTransform transform;
    private GameObject parent;

    public CollisionBox(MapTransform corner1, MapTransform corner2) {

        // max and min x
        if(corner1.getX() < corner2.getX()) {
            maxX = corner2.getX();
            minX = corner1.getX();
        }
        if(corner2.getX() < corner1.getX()) {
            maxX = corner1.getX();
            minX = corner2.getX();
        }

        // max and min y
        if(corner1.getY() < corner2.getY()) {
            maxY = corner2.getY();
            minY = corner1.getY();
        }
        if(corner2.getY() < corner1.getY()) {
            maxY = corner1.getY();
            minY = corner2.getY();
        }


    }

    @Override
    public void init(GameObject o) {
        updateRelativeToLocation(o.getLocation());
        this.parent = o;
    }

    @Override
    public void update(GameObject o) {
        updateRelativeToLocation(transform);
    }

    @Override
    public MapTransform getTransform() {
        // Returns the center of the collision box
        return getCenter();
    }

    @Override
    public GameObject getParentObject() {
        return parent;
    }


    public MapTransform getCenter() {
        return new MapTransform((maxX -minX)/2,(maxY-minY)/2);
    }

    /*

        Create a collision box in a square shape, with a certain size, ex: 3x3, 4x4, 5x5

         */
    public CollisionBox(int squareSize) {

        if(squareSize < 0) {
            try {
                throw new Exception("Cannot create collission box of negative size!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.maxY = squareSize;
        this.maxX = squareSize;

        this.minY = -squareSize;
        this.minX = -squareSize;

        this.width = squareSize;
        this.height = squareSize;
    }
    /*

    Create a collision box with a certain width and height, ex: 3x5, 2x4, 2x3

     */
    public CollisionBox(int width, int height) {

        this.width = width;
        this.height = height;

        this.maxY = height;
        this.maxX = width;

        this.minY = -height;
        this.minX = -width;
    }

    /*

    Updates the collission box relative to a point being its center

     */
    public void updateRelativeToLocation(MapTransform location) {

        this.maxX = location.getX() + width;
        this.maxY = location.getY() + height;

        this.minX = location.getX() - width;
        this.minY = location.getY() - height;

    }

    public boolean isColliding(CollisionBox box) {

        MapTransform maxLocation = new MapTransform(box.getMaxX(),box.getMaxY());
        MapTransform minLocation = new MapTransform(box.getMinX(),box.getMinY());

        return this.didCollide(minLocation) || this.didCollide(maxLocation);
    }

    public boolean didCollide(MapTransform location) {
        /*
        (1,1) min x, min y
        * * * * * *
        *         * (5,2)
        *         *
        *         *
        * * * * * *
                    (5,5) max x, max y


        a point that would collide with the rectangle would need to have coordinates that aren't bigger than the maxX and that aren't smaller than minX, same with the Y coordinate

         */

        return location.getX() <= maxX && location.getX() >= minX && location.getY() <= maxY && location.getY() >= minY;
    }

}
