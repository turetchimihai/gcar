package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Road road;
	Car car;
	ObstacleGenerator obstacle_3;
	ObstacleGenerator obstacle_2;
	ObstacleGenerator obstacle_1;
	int speed;

	@Override
	public void create () {
		batch = new SpriteBatch();
		road = new Road();
		obstacle_3 = new Obstacle_3(road);
		obstacle_2 = new Obstacle_2(road);
		obstacle_1 = new Obstacle_1(road);
		car = new Car(road, obstacle_1, obstacle_2, obstacle_3, this);
	}

	@Override
	public void render () {
		try {
			update();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		road.render(batch);
		car.render(batch);
		obstacle_3.render(batch);
		obstacle_2.render(batch);
		obstacle_1.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void update() throws InterruptedException {
		car.update();
		road.update();
		obstacle_3.update();
		obstacle_2.update();
		obstacle_1.update();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			reset();
		}
	}

	public void upSpeed() {
		speed ++;
		System.out.println("Speed : " + speed);
	}

	public void reset() {
		car.reset();
		obstacle_1.reset();
		obstacle_2.reset();
		obstacle_3.reset();
	}
}
