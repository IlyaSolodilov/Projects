package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DichFighter extends ApplicationAdapter {
    private SpriteBatch batch;
    private Fighter fighter;
    private BitmapFont font24;
    private Background background;
    private Enemy enemy;
    boolean gameOver;
    Texture restartTexture;
    Texture jonnyTexture;
    Texture robertTexture;
    boolean jonnywins;
    boolean robertwins;
    Texture jonnyWinTexture;
    Texture robertWinTexture;

    public List<Object> warriors;

    public List<Object> getWarriors() {
        return warriors;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void create() {
        warriors = new ArrayList<>();
        batch = new SpriteBatch();
        fighter = new Fighter(this);
        enemy = new Enemy(this);
        warriors.addAll(Arrays.asList(fighter, enemy));
        background = new Background();
        font24 = new BitmapFont(Gdx.files.internal("color.fnt"));
        gameOver = false;
        restartTexture = new Texture("restart.png");
        jonnyTexture = new Texture("jonny.png");
        robertTexture = new Texture("robert.png");
        jonnyWinTexture = new Texture("jonnywin.png");
        robertWinTexture = new Texture("robertwin.png");
        jonnywins = false;
        robertwins = false;
    }

    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch, font24);
        enemy.render(batch);
        fighter.render(batch);
        batch.draw(jonnyTexture,90,630);
        batch.draw(robertTexture,1050,630);
        if(!gameOver) {
            fighter.render(batch);
        }else {
            batch.draw(restartTexture,430,320);
        }
        if(!gameOver){
            enemy.render(batch);
        } else {
            batch.draw(restartTexture,430,320);
        }
        batch.end();
    }

    public void update(float dt) {
        if(fighter.isAlive()) {
            fighter.update(dt);
        }else{
            gameOver = true;
            jonnywins = true;
        }
        if(enemy.isAlive()) {
            enemy.update(dt);
        }else {
            gameOver = true;
            robertwins = true;
        }
        int dst = (int) Math.sqrt((fighter.getX() - enemy.getX()) * (fighter.getX() - enemy.getX()) + (fighter.getY() - enemy.getY()) * (fighter.getY() - enemy.getY()));
        if (dst < 100.0f) {
            enemy.update(dt);
            fighter.update(dt);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.T)){
            recreate();
        }
        }

	@Override
	public void dispose() {
		batch.dispose();
		fighter.dispose();
		enemy.dispose();
		background.dispose();
	}
	public void recreate(){
        fighter.recreate();
        enemy.recreate();
        background.recreate();
        gameOver = false;
    }
}