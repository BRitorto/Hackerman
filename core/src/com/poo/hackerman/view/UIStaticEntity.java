package com.poo.hackerman.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.CameraGuard;

/**
 * Created by cderienzo on 6/20/2017.
 */
public class UIStaticEntity {

    private Texture texture;
    private Sprite sprite;
    private CameraGuard cameraGuard;

    public UIStaticEntity(Texture texture) {
        this.texture = texture;
        sprite = new Sprite(texture);
    }
    public UIStaticEntity(Texture texture, CameraGuard cameraGuard) {
        this.texture = texture;
        sprite = new Sprite(texture);
        this.cameraGuard = cameraGuard;
    }


    public void setPosition(int x, int y) {
        sprite.setX(x-texture.getWidth()/2);
        sprite.setY(y-texture.getHeight()/2);
    }

    public void draw(Batch batch) {
        batch.draw(texture,sprite.getX(),sprite.getY());
    }
    public void rotate(Direction direction){
        if(direction.getCode() == Direction.LEFT){
            sprite.rotate90(true);
        }
        else if(direction.getCode() == Direction.RIGHT) {
            sprite.rotate90(false);
        }
        else if(direction.getCode() == Direction.UP) {
            sprite.flip(false,true);
        }

    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Direction getDirection() {
        return cameraGuard.getDirection();
    }
}
