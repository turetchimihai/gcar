package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ObstacleGenerator {
    void changeTexture(int texture);
    void update();
    void render(SpriteBatch batch);
    void reset();
    float getY();
    float getX();
}
