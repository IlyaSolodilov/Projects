package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Fighter {
    private final DichFighter game;
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
    private double dmg;
    private int dmgrange;
    private Texture jonnyTexture;
    private Texture jonnyWinTexture;

    public boolean isAlive() {
        return hp > 0;
    }

    public Fighter(DichFighter game) {
        this.texture = new Texture("sword_attackm1.png");
        this.textureHp = new Texture("hpbar.png");
        this.jonnyTexture = new Texture("jonny.png");
        this.jonnyWinTexture = new Texture("jonnywin.png");
        this.x = 900;
        this.y = 100;
        this.angle = 0.0f;
        this.speed = 240;
        this.hpMax = 10.0f;
        this.hp = 10.0f;
        this.game = game;
        this.dmgrange = 100;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void dmg() {
        hp -= dmg;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
    public void setHpMax(float hpMax) {
        this.hpMax = hpMax;
    }
    public void update(float dt) {
        damageEffectTimer -= 1;
        if (damageEffectTimer < 0.0f) {
            damageEffectTimer = 0.0f;
        }
        float dst = (float) Math.sqrt((game.getEnemy().getX() - this.x) * (game.getEnemy().getX() - this.x) + (game.getEnemy().getY() - this.y) * (game.getEnemy().getY() - this.y));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * MathUtils.cosDeg(angle) * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * MathUtils.cosDeg(angle) * dt * 2.0f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            hp -= dmg;
            if(dst > dmgrange){
                dmg = 0;
            } else {
                dmg = 0.5;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            dmg = 0.2;
        }
    }

  public void render (SpriteBatch batch){
                if (damageEffectTimer > 0) {
                    batch.setColor(1, 1 - damageEffectTimer, 1 - damageEffectTimer, 1);
                }
                batch.draw(texture, x - 20, y - 20, 65, 104, 130, 209, 1, 1, angle, 0, 0, 130, 209, false, false);
                batch.setColor(1, 1, 1, 1);
                batch.setColor(0.1f, 0, 0, 1);
                batch.draw(textureHp, x - 20, y + 125, 30, 107, 160, 120, 1, 1, angle, 0, 0, 160, 120, false, false);
                batch.setColor(0.7f, 1, 0, 1);
                batch.draw(textureHp, x - 20, y + 125, 0, 0, (int) ((float) hp / hpMax * 160), 120);
                batch.setColor(1, 1, 1, 1);
                if (hp < 0){
                    batch.draw(jonnyWinTexture,300,300);
                }
            }
            public void recreate () {
                x = 900;
                y = 100;
                angle = 0.0f;
                hp = hpMax;
            }
            public void dispose () {
                texture.dispose();
                textureHp.dispose();
            }
        }







