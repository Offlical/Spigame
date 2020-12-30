package dev.offlical.spigame.api;

import dev.offlical.spigame.GameInstance;
import lombok.Getter;
import net.minecraft.server.v1_16_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapView;
import org.bukkit.entity.Player;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.function.Consumer;

@Getter
public class SpigameAPI {

    public static int MAP_ID = 1;
    public static int gameRenderersID = 0;
    public static int gameObjectID = 0;



    public static WorldMap getWorldMap(int mapId) {
        WorldServer worldServer = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();

        String id = worldServer.a("map_" + mapId).getId();


        WorldMap worldMap = new WorldMap("map_" + id);
        worldServer.a(worldMap);

        return worldMap;
    }

    public static org.bukkit.inventory.ItemStack convertToItemStack(GameInstance renderer) {
        ItemStack itemStack = new ItemStack(Items.FILLED_MAP);

        WorldServer worldServer = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();

        String id = worldServer.a("map_1").getId();


        WorldMap worldMap = new WorldMap("map_" + id);
        worldMap.mapView.getRenderers().forEach(mapRenderer -> {
            worldMap.mapView.getRenderers().remove(mapRenderer);
        });
        worldServer.a(worldMap);






        itemStack.getOrCreateTag().setInt("map", 1);

        return CraftItemStack.asBukkitCopy(itemStack);
    }

    public static MapView createMap(World world, int id) {
        String name = "map_" + id;
        CraftMapView map = MinecraftServer.getServer().getWorlds().iterator().next().getServer().getMap(id);

        return map;
    }

    public static void setMapId(int mapId) {
        MAP_ID = mapId;
    }
}
