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
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this,ListenerPriority.NORMAL,PacketType.Play.Server.MAP) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer container = event.getPacket();
                if(playerInstances.containsKey(event.getPlayer().getUniqueId()))
                {
                    container.getByteArrays().writeSafely(0,playerInstances.get(event.getPlayer().getUniqueId()).getMapData());
                }
            }
        });
        this.getServer().getPluginManager().registerEvents(this,this);
    }


    public void setPlayerInstance(UUID playerUUID, GameInstance instance) {
        playerInstances.put(playerUUID,instance);
    }



}
