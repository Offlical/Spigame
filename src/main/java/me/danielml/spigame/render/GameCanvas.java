package me.danielml.spigame.render;


public class GameCanvas {

                       //y,x
    private final byte[][] canvas;

    public GameCanvas() {
        this.canvas = new byte[128][128];
    }

    public void draw(int x, int y, byte color) {
        this.canvas[y][x] = color;
    }


    public byte[][] getByteCanvas() {
        return canvas;
    }

    public byte[] getAsMapData() {
        byte[] colors = new byte[16384];

        int pos = 0;

        for(int x = 0; x < 128; x++) {
            for(int y = 0; y < 128; y++) {

                // See https://minecraft.gamepedia.com/Map_item_format#map_.3C.23.3E.dat_format
                pos = x + y * 128;


                // set the color of the pixel on the map to the correct position in the array
                colors[pos] = canvas[y][x];
            }
        }
        return colors;
    }
}
