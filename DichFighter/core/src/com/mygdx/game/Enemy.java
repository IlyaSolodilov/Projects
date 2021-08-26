package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    private Texture texture;
    private Texture textureHp;
    private float x;
    private float y;
    private float angle;
    private float speed;
    private float scale;
    private float hp;
    private float hpMax;
    float damageEffectTimer;
    public double dmg;
    private int dmgrange;
    private Texture robertTexture;
    private Texture robertWinTexture;

    public boolean isAlive() {
        return hp > 0;
    }

    private DichFighter game;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Enemy(DichFighter game) {
        this.texture = new Texture("warrior.png");
        this.textureHp = new Texture("hpbar.png");
        this.robertTexture = new Texture("robert.png");
        this.robertWinTexture = new Texture("robertwin.png");
        this.x = 200;
        this.y = 90;
        this.angle = 0.0f;
        this.speed = 240;
        this.hpMax = 10.0f;
        this.hp = 10.0f;
        this.game = game;
        this.dmgrange = 100;
    }
    public void dmg(){
        hp -= dmg;
    }

    public void update(float dt){
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f) {
            damageEffectTimer = 0.0f;
        }
        float dst = (float) Math.sqrt((game.getFighter().getX() - this.x) * (game.getFighter().getX() - this.x) + (game.getFighter().getY() - this.y) * (game.getFighter().getY() - this.y));
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += speed * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= speed * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += speed * MathUtils.cosDeg(angle) * dt;
                if (x > Gdx.graphics.getWidth() - 40 * scale) {
                    x = Gdx.graphics.getWidth() - 40 * scale;
                }
                if (x < 0.0f + 40 * scale) {
                    x = +40.0f * scale;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= speed * MathUtils.cosDeg(angle) * dt * 2.0f;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
                hp -= dmg;
                if (dst > dmgrange) {
                    dmg = 0;
                } else {
                    dmg = 0.5;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.G)){
                dmg = 0.2;
            }
        }

    public void render(SpriteBatch batch) {
        if (damageEffectTimer > 0) {
            batch.setColor(1, 1 - damageEffectTimer, 1 - damageEffectTimer, 1);
        }
        batch.draw(texture, x - 20, y - 20, 65, 104, 130, 209, 1, 1, angle, 0, 0, 130, 209, false, false);
        batch.setColor(1, 1, 1, 1);
        batch.setColor(0.1f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureHp, x - 25, y + 125, 30, 107, 160, 120, 1, 1, angle, 0, 0, 160, 120, false, false);
        batch.setColor(0.7f, 1.0f, 0.0f, 1.0f);
        batch.draw(textureHp, x - 25, y + 125, 0, 0, (int) ((float) hp / hpMax * 160), 120);
        batch.setColor(1, 1, 1, 1);
        if (hp < 0){
            batch.draw(robertWinTexture,300,300);
        }
    }
    public void recreate() {
        x = 200;
        y = 90;
        angle = 0.0f;
        hp = hpMax;
    }
    public void dispose() {
        texture.dispose();
    }
}
