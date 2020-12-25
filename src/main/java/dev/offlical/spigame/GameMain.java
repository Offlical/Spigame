package dev.offlical.spigame;


import dev.offlical.spigame.collisions.CollisionBox;
import dev.offlical.spigame.gameobjects.GameObject;
import dev.offlical.spigame.gameobjects.MapLocation;
import dev.offlical.spigame.render.GameRenderer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class GameMain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this,this);
    }


    @EventHandler
    public void onMap(MapInitializeEvent event) {

        MapView view = event.getMap();
        view.getRenderers().clear();
        view.setTrackingPosition(false);
        view.setUnlimitedTracking(false);


        GameObject object = new GameObject(2,2,"object1");
        // x: 1,3 y: 1,3
        // min: 1,1 max: 3,3

        GameObject object2 = new GameObject(20,20,"object2") {
            @Override
            public void draw() {
                for(int x = 0; x < 3; x++) {
                    for(int y = 0; y < 3; y++) {
                        render.getCanvas().setPixel((location.getX() + x),location.getY() + y, MapPalette.BLUE);
                    }
                }
            }
        };


        CollisionBox box = new CollisionBox(1);
        object.setCollisionBox(box);

        box = new CollisionBox(1,1);
        object2.setCollisionBox(box);


        GameRenderer renderer = new GameRenderer(Arrays.asList(object, object2));
        object.setRender(renderer);
        object2.setRender(renderer);

        view.addRenderer(renderer);



        Bukkit.getScheduler().runTaskTimer(this, bukkitTask -> {
            object2.move(object2.getX() - 1, object2.getY() - 1);
        },5,20);

    }
}
