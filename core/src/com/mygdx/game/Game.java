package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Rectangle rect;
	Vector2 speed;
	boolean changeOfSpeed;
	Zeus zeus;
	Blade blade;
	Bullet bullet;
	HealthBar healthBar;
	HealthBar healthBar2;
	Redbar redBar;
	Redbar redBar2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("GodOfWar.png");
		rect = new Rectangle(650, 50, 120, 180);
		speed = new Vector2(9, 9);
		zeus = new Zeus();
		bullet = new Bullet();
		bullet.bullet = new Rectangle(500,50,zeus.zeus.getWidth(), 60);
		blade = new Blade();
		healthBar = new HealthBar();
		healthBar2 = new HealthBar();
		redBar = new Redbar();
		redBar2 = new Redbar();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		bullet.bullet.setX(rect.getX());
//		bullet.bullet.setY(rect.getY());

		if(bullet.bullet.getX() <= zeus.zeus.getX() + zeus.zeus.getWidth()){
			redBar.redBar.width += 0.5;
		}
	if(bullet.bullet.getX() > 0) {
		if (Gdx.input.isKeyPressed(Input.Keys.I)) {
			bullet.bullet.setX(bullet.speed.x -= 13);
		}
	}
	if(bullet.bullet.getX() + bullet.bullet.getWidth() < Gdx.graphics.getWidth()) {
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			bullet.bullet.setX(bullet.speed.x += 13);
		}
	}

/*
		if(rect.getX() <= zeus.zeus.getX()){
			speed.x = 0;
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				speed.x = 9;
				rect.setX(rect.getX() + speed.x);
				//speed.x++;
			}
		}
*/
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			if(speed.x == 9 && !changeOfSpeed){
				speed.x *= 3;
				speed.y *= 3;
				changeOfSpeed = true;
			}else if (speed.x != 9 && changeOfSpeed){
				speed.x = 9;
				speed.y = 9;
				changeOfSpeed = false;
			}
		}

		if (rect.getY() + rect.getHeight() < Gdx.graphics.getHeight()) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				rect.setY(rect.getY() + speed.y);
			}
		}

		if (rect.getY() > 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				rect.setY(rect.getY() - speed.y);
			}
		}

		if (rect.getX() + rect.getWidth() < Gdx.graphics.getWidth()){
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				rect.setX(rect.getX() + speed.x);
			}
		}

		if(rect.getX() > 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				rect.setX(rect.getX() - speed.x);
			}
		}

		batch.begin();
		bullet.render(batch);
		zeus.render(batch);
		blade.render(batch);
		healthBar2.render(batch);
		healthBar.render(batch);
		redBar.render(batch);
		redBar2.render(batch);
		System.out.println("X:" + bullet.bullet.getX() + "Y:" + bullet.bullet.getY());
		batch.draw(img, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		batch.end();
	}
}