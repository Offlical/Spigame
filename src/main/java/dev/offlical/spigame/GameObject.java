package dev.offlical.spigame;

import org.bukkit.map.MapPalette;

public class GameObject {


    public int x;
    public int y;
    public GameRenderer render;


    public GameObject(int x, int y, GameRenderer render) {
        this.x = x;
        this.y = y;
        this.render = render;
    }


    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        if(x > 128) this.x = 4;
        draw();
    }


    public void draw() {
        for(int i = 0; i < 3; i ++) {
            if(i == 0) {
                render.getCanvas().setPixel((x - 1), (y - 1),MapPalette.RED);
                render.getCanvas().setPixel((x - 1),y,MapPalette.RED);
                render.getCanvas().setPixel(x,(y - 1),MapPalette.RED);
            }
            if(i == 1) {
                render.getCanvas().setPixel((x), (y),MapPalette.RED);
            }
            if(i == 2) {
                render.getCanvas().setPixel((x + 1), (y + 1),MapPalette.RED);
                render.getCanvas().setPixel(x, (y + 1),MapPalette.RED);
                render.getCanvas().setPixel((x + 1), y,MapPalette.RED);
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GameRenderer getRender() {
        return render;
    }

    public void setRender(GameRenderer render) {
        this.render = render;
    }
}
