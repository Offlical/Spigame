package dev.offlical.spigame.collisions;

import dev.offlical.spigame.gameobjects.GameObject;
import dev.offlical.spigame.gameobjects.MapLocation;
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

    private MapLocation collisionPoint;



    @Override
    public HandlerList getHandlers() {
        return list;
    }
}
