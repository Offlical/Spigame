package dev.offlical.spigame;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import dev.offlical.spigame.api.SpigameAPI;
import dev.offlical.spigame.gameobjects.GameObject;
import dev.offlical.spigame.gameobjects.MapLocation;
import dev.offlical.spigame.render.GameView;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapRenderer;
import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapView;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapPalette;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

@Getter
public class Spigame extends JavaPlugin implements Listener {


    private static HashMap<UUID, GameInstance> playerInstances;
    private GameView view;

    private static final Logger spigameLogger = Logger.getLogger("Spigame");;


    @Override
    public void onEnable() {
        view = new GameView(SpigameAPI.MAP_ID);
        playerInstances = new HashMap<>();



        /*

        ProtocolLib integration

         */
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this,ListenerPriority.NORMAL,PacketType.Play.Server.MAP) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if(event.getPacketType().equals(PacketType.Play.Server.MAP)) {
                    System.out.println("\n\n PACKET RECIEVE \n\n");
                    System.out.println(event.getPacket().toString());
                }
            }

            @Override
            public void onPacketSending(PacketEvent event) {
                if(event.getPlayer().getUniqueId().equals(UUID.fromString("1254cca9-8eec-4a72-8e88-f7ae11e2f037"))) {
                    PacketContainer container = event.getPacket();
                    if(playerInstances.containsKey(event.getPlayer().getUniqueId()))
                            container.getByteArrays().writeSafely(0,playerInstances.get(event.getPlayer().getUniqueId()).getMapData());
                }
            }
        });
        this.getServer().getPluginManager().registerEvents(this,this);
    }



    /*

    Using swap item events just for testing

     */
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        GameObject object = new GameObject(2,2,"TE"),object2 = new GameObject(7,7, "bob") {
            @Override
            public byte[][] draw(byte[][] canvas) {
                MapLocation location = this.location;
                for(int x = 0; x < 3; x++) {
                    for(int y = 0; y < 3; y++) {
                        canvas[location.getY() + y][location.getX() + x] = MapPalette.BLUE;
                    }
                }
                return canvas;
            }
        };

        ItemStack i = new ItemStack(Material.FILLED_MAP,1); //
        MapMeta meta = (MapMeta) i.getItemMeta();

        meta.setMapId(SpigameAPI.MAP_ID);
        meta.setMapView(new GameView(SpigameAPI.MAP_ID));
        meta.getMapView().addRenderer(new CraftMapRenderer(new CraftMapView(SpigameAPI.getWorldMap(1)),SpigameAPI.getWorldMap(1)));


        i.setItemMeta(meta);

        GameInstance instance = new GameInstance(Arrays.asList(object,object2), MapPalette.LIGHT_GRAY);
        playerInstances.put(event.getPlayer().getUniqueId(),instance);
        event.getPlayer().getInventory().addItem(i);
    }


    public static HashMap<UUID, GameInstance> getPlayerInstances() {
        return playerInstances;
    }

    public static Logger getSpigameLogger() {
        return spigameLogger;
    }
}
