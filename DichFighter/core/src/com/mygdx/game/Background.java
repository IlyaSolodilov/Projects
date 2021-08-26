package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture texture;
    private float x;
    private float y;

    public Background() {
        this.texture = new Texture("backroundmap.jpg");
    }

    public void render(SpriteBatch batch, BitmapFont font24) {
        batch.draw(texture, x, y, 640, 355, 1280, 720, 1, 1, 0, 0, 0, 1280, 720, false, false);
    }

    public void recreate(){
        x = 1;
        y = 1;
    }
    public void dispose (){
        texture.dispose();
    }
}
