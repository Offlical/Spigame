package dev.offlical.spigame.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapLocation {

    public int x;
    public int y;

    public MapLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
