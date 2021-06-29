package me.danielml.spigame;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.danielml.spigame.api.SpigameAPI;
import me.danielml.spigame.cmds.SpigameCmds;
import me.danielml.spigame.render.GameView;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Spigame extends JavaPlugin implements Listener {


    private HashMap<UUID, GameInstance> playerInstances;
    private GameView view;

    @Override
    public void onEnable() {
        view = new GameView(SpigameAPI.MAP_ID);
        playerInstances = new HashMap<>();

        this.getCommand("testspigame").setExecutor(new SpigameCmds(this));

        /*

        ProtocolLib integration

         */
//        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
//        manager.addPacketListener(new PacketAdapter(this,ListenerPriority.NORMAL,PacketType.Play.Server.MAP) {
//            @Override
//            public void onPacketReceiving(PacketEvent event) {
//                if(event.getPacketType().equals(PacketType.Play.Server.MAP)) {
//                    System.out.println("\n\n PACKET RECIEVE \n\n");
//                    System.out.println(event.getPacket().toString());
//                }
//            }
//
//            @Override
//            public void onPacketSending(PacketEvent event) {
//                PacketContainer container = event.getPacket();
//                if(playerInstances.containsKey(event.getPlayer().getUniqueId()))
//                {
//                    container.getByteArrays().writeSafely(0,playerInstances.get(event.getPlayer().getUniqueId()).getMapData());
//                }
//            }
//        });
        this.getServer().getPluginManager().registerEvents(this,this);
    }


    public void setPlayerInstance(UUID playerUUID, GameInstance instance) {
        playerInstances.put(playerUUID,instance);
    }

    /*

    Using swap item events just for testing

     */
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
//        GameObject object = new GameObject(2,2,"TE"),object2 = new GameObject(7,7, "bob") {
//            @Override
//            public byte[][] draw(byte[][] canvas) {
//                MapTransform location = this.location;
//                for(int x = 0; x < 3; x++) {
//                    for(int y = 0; y < 3; y++) {
//                        canvas[location.getY() + y][location.getX() + x] = MapPalette.BLUE;
//                    }
//                }
//                return canvas;
//            }
//        };
//
//        if(event.getPlayer().getName().equalsIgnoreCase("dogytlol"))
//            object = object2 = new GameObject(7,7, "bob") {
//            @Override
//            public byte[][] draw(byte[][] canvas) {
//                MapTransform location = this.location;
//                for(int x = 0; x < 3; x++) {
//                    for(int y = 0; y < 3; y++) {
//                        canvas[location.getY() + y][location.getX() + x] = MapPalette.RED;
//                    }
//                }
//                return canvas;
//            }
//        };
//
//
//        GameInstance instance = new GameInstance(Arrays.asList(object,object2), MapPalette.LIGHT_GRAY);
//        playerInstances.put(event.getPlayer().getUniqueId(),instance);
//        event.getPlayer().getInventory().addItem(i);
    }


}
