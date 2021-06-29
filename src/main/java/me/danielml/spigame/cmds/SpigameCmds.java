package me.danielml.spigame.cmds;

import me.danielml.spigame.GameInstance;
import me.danielml.spigame.Spigame;
import me.danielml.spigame.gameobjects.GameObject;
import me.danielml.spigame.gameobjects.MapTransform;
import me.danielml.spigame.render.Texture;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SpigameCmds implements CommandExecutor {

    private Spigame plugin;

    public SpigameCmds(Spigame plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player && cmd.getName().equals("testspigame")) {

            Player p = (Player) sender;

            GameObject object = new GameObject(new MapTransform(64,64));
            object.setRenderTexture(Texture.SQUARE);

            GameInstance instance = new GameInstance(Arrays.asList(object));
            plugin.setPlayerInstance(p.getUniqueId(),instance);

            p.getInventory().addItem(instance.getAsItem());
        }

        return true;
    }
}
