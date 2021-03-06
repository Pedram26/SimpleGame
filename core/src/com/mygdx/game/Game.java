package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
		Lightning bolt;
		long startTime;
		BitmapFont font;
		BitmapFont fontZeus;
		BitmapFont fontKratos;
		String zeusName;
		String kratosName;
		Texture texture;
		long currentTime;
		long secondsPassed;
		long timerValue;
		BitmapFont fontVictoryZ;
		BitmapFont fontVictoryK;
		String zeusVictory;
		String kratosVictory;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("GodOfWar.png");
		rect = new Rectangle(650, 50, 120, 180);
		speed = new Vector2(9, 9);
		zeus = new Zeus();
		bullet = new Bullet();
		bullet.bullet = new Rectangle(570,120,90, 50);
		blade = new Blade();
		healthBar = new HealthBar();
		healthBar2 = new HealthBar();
		redBar = new Redbar();
		redBar2 = new Redbar();
		bolt = new Lightning();
		bolt.bolt = new Rectangle(125, 150, rect.getWidth(), 60);
		startTime = System.currentTimeMillis();
		font = new BitmapFont(false);
		fontZeus = new BitmapFont(false);
		fontKratos = new BitmapFont(false);
		zeusName = ("Zeus");
		kratosName = ("Kratos");
		fontVictoryZ = new BitmapFont(false);
		fontVictoryK = new BitmapFont(false);
		zeusVictory = ("ZEUS WINS");
		kratosVictory = ("KRATOS WINS");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//texture = new Texture(Gdx.files.internal("olympus3.jpg"));

		System.out.println(redBar.redBar.width);
		System.out.println(redBar2.redBar2.width);


//		bullet.bullet.setX(rect.getX());
//		bullet.bullet.setY(rect.getY());

		if(bullet.bullet.getX() <= zeus.zeus.getX() + zeus.zeus.getWidth() && redBar.redBar.width < 154 && timerValue > 0){
			redBar.redBar.width += .25;
		}

		if(bolt.bolt.getX() + bolt.bolt.getWidth() >= rect.getX() && redBar2.redBar2.width > -154 && timerValue > 0){
			redBar2.redBar2.width -= .25;
		}

		if(bullet.bullet.getX() + bullet.bullet.getWidth() < Gdx.graphics.getWidth()) {
			if (Gdx.input.isKeyPressed(Input.Keys.P)) {
				bullet.bullet.setX(bullet.speed.x += 13);
			}
		}

		if(bullet.bullet.getX() > 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.I)) {
				bullet.bullet.setX(bullet.speed.x -= 13);
			}
		}


		if(bolt.bolt.getX() + bolt.bolt.getWidth() < Gdx.graphics.getWidth() ){
			if(Gdx.input.isKeyPressed(Input.Keys.E)){
				bolt.bolt.setX(bolt.speed.x += 13);
			}
		}

		if(bolt.bolt.getX() > 0){
			if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				bolt.bolt.setX(bolt.speed.x -= 13);
			}
		}

		currentTime = System.currentTimeMillis();
		secondsPassed = (currentTime - startTime) / 1000;
		timerValue = 5 - secondsPassed;

		if(timerValue <=0){
			timerValue = 0;
		}

		if(redBar.redBar.width >= 154){
			timerValue = 0;
		}

		if(redBar2.redBar2.width <= -154){
			timerValue = 0;
		}

		if(timerValue <= 0){
			speed.x = 0;
			speed.y = 0;
			zeus.speed.x = 0;
			zeus.zeus.y = 0;
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
		//batch.draw(texture, 0, 0);
		bullet.render(batch);
		System.out.println("x1" + zeus.zeus.getX() + "y2" + zeus.zeus.getY());
		zeus.render(batch);
		blade.render(batch);
		healthBar2.render(batch);
		healthBar.render(batch);
		redBar.render(batch);
		redBar2.render(batch);
		bolt.render(batch);
		font.draw(batch, Long.toString(timerValue), 388, 467);
		fontZeus.draw(batch, zeusName, 130, 380);
		fontKratos.draw(batch, kratosName, 635, 380);
		System.out.println("X:" + bullet.bullet.getX() + "Y:" + bullet.bullet.getY());
		batch.draw(img, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());


		if(redBar.redBar.width >= 154){
			fontVictoryK.draw(batch, kratosVictory, 350, 310);
		}

		if(redBar2.redBar2.width <= -154){
			fontVictoryZ.draw(batch, zeusVictory, 350, 310);
		}


		batch.end();
	}
}