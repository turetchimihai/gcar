package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Obstacle_3 implements ObstacleGenerator {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private Road road;

    public Obstacle_3(Road road) {
        texture = new Texture("obstacle_6.png");
        speed = 1;
        position = new Vector2(268,(int) (Math.random() * ((2048 - 1024) + 1) + 1024));
        this.road = road;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void changeTexture(int texture) {
        switch (texture) {
            case 0 : this.texture = new Texture("obstacle_6.png"); break;
            case 1 : this.texture = new Texture("obstacle_5.png"); break;
        }
    }

    public void update() {
        if (road.getSpeed() >= 0) {
            position.y -= road.getSpeed() + 1;
        }

        if (position.y < -512) {
            position.y = (int) (Math.random() * ((8000 - 1024) + 1) + 1024);
            changeTexture((int) Math.floor(Math.random() * 2));
        }
    }

    public void reset() {
        position = new Vector2(268, (int) (Math.random() * ((8000 - 1024) + 1) + 1024));
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }
}
