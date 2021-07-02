package me.danielml.spigame.render.texture;

import me.danielml.spigame.gameobjects.MapTransform;
import me.danielml.spigame.render.GameCanvas;
import org.bukkit.map.MapPalette;

public class Texture {

    private byte[][] matrix;

    public static final Texture SQUARE = new Texture(new byte[][]{{MapPalette.RED, MapPalette.RED,MapPalette.RED},{MapPalette.RED, MapPalette.RED,MapPalette.RED},{MapPalette.RED, MapPalette.RED,MapPalette.RED}});

    public Texture(byte[][] matrix) {
        this.matrix = matrix;
    }

    public void drawRelativeToLoc(MapTransform transform, GameCanvas canvas) {
        for(int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[0].length; x++) {
                canvas.draw(transform.getX() + x, transform.getY() + y,matrix[y][x]);
            }
        }

    }

    public byte[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(byte[][] matrix) {
        this.matrix = matrix;
    }
}
