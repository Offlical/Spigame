package me.danielml.spigame;

import me.danielml.spigame.api.SpigameAPI;
import me.danielml.spigame.gameobjects.GameObject;
import lombok.Getter;
import lombok.Setter;
import me.danielml.spigame.render.GameCanvas;
import me.danielml.spigame.render.GameRenderer;
import me.danielml.spigame.render.GameView;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapRenderer;
import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class GameInstance {

    private final List<GameObject> objects;
    private final Logger LOGGER = Logger.getLogger("Spigame");;

                      // y x
    private final GameCanvas canvas;

    public GameInstance(List<GameObject> objects) {
        this.objects = objects;
        this.canvas = new GameCanvas();
        render();
    }

    public GameInstance() {
        this.objects = new ArrayList<>();
        this.canvas = new GameCanvas();
    }

    public void render() {
        objects.forEach(object -> object.render(canvas));
    }

    public void remove(GameObject object) {
        objects.remove(object);
    }

    public ItemStack getAsItem() {
        ItemStack i = new ItemStack(Material.FILLED_MAP,1); //
        MapMeta meta = (MapMeta) i.getItemMeta();

        meta.setMapId(SpigameAPI.MAP_ID);
        meta.setMapView(new GameView(SpigameAPI.MAP_ID));
        meta.getMapView().getRenderers().clear();
        meta.getMapView().addRenderer(new GameRenderer(this));

        i.setItemMeta(meta);

        return i;
    }

    public byte[] getMapData() {
        return canvas.getAsMapData();
    }

    public void drawCanvas(MapCanvas mapCanvas) {
        byte[][] byteCanvas = canvas.getByteCanvas();
        for(int y = 0; y < 128; y++) {
            for(int x = 0; x < 128; x++) {
                mapCanvas.setPixel(y,x,byteCanvas[y][x]);
            }
        }
    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
