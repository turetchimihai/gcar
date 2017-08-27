package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Car {
    private Texture texture;
    private Vector2 position;
    private Road road;
    private final float brakeCoef = -0.1f;
    private final float speedCoef = 0.1f;
    private final float turnCoef = 1f;
    private ObstacleGenerator obstacle_1;
    private ObstacleGenerator obstacle_2;
    private ObstacleGenerator obstacle_3;
    private MyGdxGame myGdxGame;

    public Car(Road road, ObstacleGenerator obstacle_1, ObstacleGenerator obstacle_2, ObstacleGenerator obstacle_3, MyGdxGame myGdxGame) {
        texture = new Texture("carb.png");
        position = new Vector2(200, 0);
        this.road = road;
        this.obstacle_1 = obstacle_1;
        this.obstacle_2 = obstacle_2;
        this.obstacle_3 = obstacle_3;
        this.myGdxGame = myGdxGame;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() throws InterruptedException {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && road.getSpeed() > 0 && position.x > 100) {
            if (road.getSpeed() <= 5) {
                position.x -= 2.00 * turnCoef;
            } else if (road.getSpeed() <= 10) {
                position.x -= 2.25 * turnCoef;
            } else if (road.getSpeed() <= 15) {
                position.x -= 2.50 * turnCoef;
            } else if (road.getSpeed() <= 21) {
                position.x -= 2.75 * turnCoef;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && road.getSpeed() > 0 && position.x < 320) {
            if (road.getSpeed() <= 5) {
                position.x += 1.25 * turnCoef;
            } else if (road.getSpeed() <= 10) {
                position.x += 1.50 * turnCoef;
            } else if (road.getSpeed() <= 15) {
                position.x += 1.75 * turnCoef;
            } else if (road.getSpeed() <= 21) {
                position.x += 2.00 * turnCoef;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && road.getSpeed() < 20) {
            road.upSpeed(speedCoef);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Math.round(road.getSpeed()) > 0) {
            road.downSpeed(brakeCoef);
            if (road.getSpeed() < 1) {
                road.stopCar();
            }
        }

        if (accident(obstacle_1.getX(), obstacle_1.getY()) == true || accident(obstacle_2.getX(), obstacle_2.getY()) == true || accident(obstacle_3.getX(), obstacle_3.getY()) == true) {
            myGdxGame.reset();
        }
    }

    public boolean accident(float xObstacle, float yObstacle) {
        if (position.x - xObstacle < 66 && position.x - xObstacle > -50  && position.y - yObstacle < 90 && position.y - yObstacle > -160) {
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        position = new Vector2(200, 0);
        road.stopCar();
        road.reset();
    }
}
