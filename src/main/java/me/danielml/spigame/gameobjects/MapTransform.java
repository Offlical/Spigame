package me.danielml.spigame.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapTransform {

    public int x;
    public int y;

    public MapTransform(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
