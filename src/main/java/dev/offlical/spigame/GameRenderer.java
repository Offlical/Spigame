package dev.offlical.spigame;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.List;

public class GameRenderer extends MapRenderer {

    private GameObject o;
    private MapCanvas canvas;
    private List<GameObject> objects;

    public GameRenderer(List<GameObject> objects) {
        this.objects = objects;
    }

    public GameRenderer(GameObject o) {
        this.o = o;
    }

    public GameRenderer() {

    }

    @Override
    public void render(MapView view, MapCanvas canvas, Player player) {
        this.canvas = canvas;
        for(int x = 0; x < 128; x++) {
            for(int y = 0; y < 128; y++) {
                canvas.setPixel(x, y, MapPalette.LIGHT_GRAY);
            }
        }
    }

    public MapCanvas getCanvas() {
        return canvas;
    }

    public GameObject getO() {
        return o;
    }

    public void setO(GameObject o) {
        this.o = o;
    }
}
