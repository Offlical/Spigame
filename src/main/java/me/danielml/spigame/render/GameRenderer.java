package me.danielml.spigame.render;

import me.danielml.spigame.GameInstance;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class GameRenderer extends MapRenderer {

    private GameInstance instance;

    public GameRenderer(GameInstance instance) {
        this.instance = instance;
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        instance.render();
        instance.drawCanvas(mapCanvas);
    }
}
