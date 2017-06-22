package com.poo.hackerman.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by cderienzo on 6/20/2017.
 */
public class UIStaticEntity {

    private Texture texture;
    private Sprite sprite;

    public UIStaticEntity(Texture texture) {
        this.texture = texture;
        sprite = new Sprite(texture);
    }

    public void setPosition(int x, int y) {
        sprite.setX(x);
        sprite.setY(y);
    }

    public void draw(Batch batch) {
        batch.draw(texture,sprite.getX(),sprite.getY());
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
