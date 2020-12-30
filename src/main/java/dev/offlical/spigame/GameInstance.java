package dev.offlical.spigame;

import dev.offlical.spigame.api.SpigameAPI;
import dev.offlical.spigame.gameobjects.GameObject;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class GameInstance {

    @Getter
    @Setter
    private UUID boundedPlayer;

    @Getter private final List<GameObject> objects;

                      // y x
    @Getter private byte[][] canvas;

    // for now only specific colors, later will add image background support
    private byte backgroundColor;


    public GameInstance(List<GameObject> objects,byte backgroundColor) {
        this.objects = objects;
        this.canvas = new byte[128][128];
        objects.forEach(object -> {
            render(object);
            object.setParentInstance(this);
        });
        this.backgroundColor = backgroundColor;
        fillEmpty(canvas,backgroundColor);
    }

    public GameInstance() {
        this.objects = new ArrayList<>();
        this.canvas = new byte[128][128];
        this.backgroundColor = MapPalette.LIGHT_GRAY;
        fillEmpty(canvas,backgroundColor);
    }


    public void render(GameObject object) {
        if(!objects.contains(object)) {
            objects.add(object);
            object.setParentInstance(this);
        }
        this.canvas = object.draw(canvas);
    }


    public void remove(GameObject object) {
        objects.remove(object);
        this.canvas = drawBackground(canvas);
        objects.forEach(o -> {
            render(o);
            o.setParentInstance(this);
        });
    }


    /*

    Convert canvas into byte array for packets

     */
    public byte[] getMapData() {
        byte[] colors = new byte[16384];

        int pos = 0;

        for(int x = 0; x < 128; x++) {
            for(int y = 0; y < 128; y++) {

                // See https://minecraft.gamepedia.com/Map_item_format#map_.3C.23.3E.dat_format
                pos = x + y * 128;

              //  System.out.println("pos value for (" + x + "," + y + "): " + pos);

                // set the color of the pixel on the map to the correct position in the array
                Byte b = canvas[y][x];
                if(b == null)
                    canvas[y][x] = MapPalette.LIGHT_GRAY;

                colors[pos] = canvas[y][x];
            }
        }
        return colors;
    }

    public byte[][] fillEmpty(byte[][] canvas, byte color) {
        for(int x = 0; x < 128; x++) {
            for(int y = 0; y < 128; y++) {


                Byte b = canvas[y][x];
                if(b == null)
                    canvas[y][x] = MapPalette.LIGHT_GRAY;
            }
        }
        return canvas;
    }

    public byte[][] drawBackground(byte[][] canvas) {
        Spigame.getSpigameLogger().info("Drawing Background");
        for(int x = 0; x < 128; x++) {
            for(int y = 0; y < 128; y++) {
                canvas[y][x] = backgroundColor;
            }
        }
        return canvas;
    }
}
