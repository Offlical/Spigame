package me.danielml.spigame.modifiers.collisions;

import me.danielml.spigame.gameobjects.GameObject;
import me.danielml.spigame.gameobjects.MapTransform;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class CollisionEvent extends Event {

    private final HandlerList list = new HandlerList();

    private GameObject objectColliding;
    private GameObject objectCollidedWith;

    private CollisionBox collidedBox;
    private CollisionBox collidingBox;

    private MapTransform collisionPoint;



    @Override
    public HandlerList getHandlers() {
        return list;
    }
}
