package me.danielml.spigame.api;

import com.comphenix.protocol.ProtocolManager;
import me.danielml.spigame.GameInstance;
import net.minecraft.server.v1_16_R2.MinecraftServer;
import net.minecraft.server.v1_16_R2.WorldMap;
import net.minecraft.server.v1_16_R2.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapView;
import org.bukkit.map.MapView;

import java.util.HashMap;
import java.util.UUID;

public class SpigameAPI {

    public static int MAP_ID = 4;
    private static int gameObjectID = 0;

    private static HashMap<UUID, GameInstance> playerInstances;

    public void initListeners(ProtocolManager manager) {
        playerInstances = new HashMap<>();
    }

    public static WorldMap getWorldMap(int mapId) {
        WorldServer worldServer = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();


        String id = worldServer.a("map_" + mapId).getId();
        WorldMap worldMap = new WorldMap("map_" + id);
        worldServer.a(id);

        WorldServer server = MinecraftServer.getServer().getWorlds().iterator().next();


        return worldMap;
    }

    public static int assignGameObjectID() {
        gameObjectID++;
        return gameObjectID;
    }

    public static void setMapId(int mapId) {
        MAP_ID = mapId;
    }
}
