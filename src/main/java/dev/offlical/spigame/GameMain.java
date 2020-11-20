package dev.offlical.spigame;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

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
        GameRenderer renderer = new GameRenderer();
        GameObject line = new GameObject(4,4,renderer);
        renderer.setO(line);
        view.addRenderer(renderer);

    }
}
