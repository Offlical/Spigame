package me.danielml.spigame.player;

import me.danielml.spigame.GameInstance;

import java.util.UUID;

public class PlayerGame {

    private UUID playerUUID;
    private GameInstance playerInstance;

    public PlayerGame(UUID playerUUID, GameInstance playerInstance) {
        this.playerUUID = playerUUID;
        this.playerInstance = playerInstance;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public GameInstance getPlayerInstance() {
        return playerInstance;
    }
}
