package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Road {
    private Roads[] roads;
    private float speed;

    class Roads {
        Texture texture;
        Vector2 position;

        public Roads(int x, int y) {
            texture = new Texture("road.png");
            position = new Vector2(x, y);
        }
    }

    public Road() {
        roads = new Roads[2];
        roads[0] = new Roads(0, 0);
        roads[1] = new Roads(0, 512);
        speed = 0;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < roads.length ; i++) {
            batch.draw(roads[i].texture, roads[i].position.x, roads[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < roads.length; i++) {
            roads[i].position.y -= speed;
        }

        if (roads[0].position.y < -512) {
            roads[0].position.y = 0;
            roads[1].position.y = 512;
        }
    }

    public void upSpeed(float speedCoef) {
        speed += speedCoef;
    }

    public void downSpeed(float brakeCoef) {
        speed += brakeCoef;
    }

    public void stopCar() {
        speed = 0;
    }

    public float getSpeed() {
        return speed;
    }

    public void reset() {
        roads[0].position.y = 0;
        roads[1].position.y = 512;
    }

}
