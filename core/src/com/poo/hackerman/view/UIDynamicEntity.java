package com.poo.hackerman.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.dynamicEntity.DynamicEntity;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 * Created by cderienzo on 6/7/2017.
 */
public class UIDynamicEntity {

    private static final int TILE_WIDTH = GameMap.CELL_SIZE;
    private static final int TILE_HEIGHT = GameMap.CELL_SIZE;

    private final Animation animationUp,animationDown,animationLeft,animationRight;
    private static final float FRAME_DURATION = 0.25F;
    private float animationTimer = 0;
    private DynamicEntity dynamicEntity;

    /**
     * Creates a player and assigns a texture to it.
     * Creates an animation by splitting the given sprite sheet into an array of TILE_WIDTHxTILE_HEIGHT dimension.
     * @param playerTexture
     * @param dynamicEntity
     */

    public UIDynamicEntity(Texture playerTexture, DynamicEntity dynamicEntity) {
        TextureRegion[][] playerTextures = TextureRegion.split(playerTexture,TILE_WIDTH,TILE_HEIGHT);
        this.dynamicEntity = dynamicEntity;
        animationUp = new Animation(FRAME_DURATION,playerTextures[0][0], playerTextures[0][1], playerTextures[0][2],playerTextures[0][3],playerTextures[0][4],playerTextures[0][5],playerTextures[0][6],playerTextures[0][7],playerTextures[0][8]);
        animationDown = new Animation(FRAME_DURATION,playerTextures[2][0], playerTextures[2][1], playerTextures[2][2],playerTextures[2][3],playerTextures[2][4],playerTextures[2][5],playerTextures[2][6],playerTextures[2][7],playerTextures[2][8]);
        animationLeft = new Animation(FRAME_DURATION,playerTextures[1][0], playerTextures[1][1], playerTextures[1][2],playerTextures[1][3],playerTextures[1][4],playerTextures[1][5],playerTextures[1][6],playerTextures[1][7],playerTextures[1][8]);
        animationRight = new Animation(FRAME_DURATION,playerTextures[3][0], playerTextures[3][1], playerTextures[3][2],playerTextures[3][3],playerTextures[3][4],playerTextures[3][5],playerTextures[3][6],playerTextures[3][7],playerTextures[3][8]);

        animationUp.setPlayMode(Animation.PlayMode.LOOP);
        animationDown.setPlayMode(Animation.PlayMode.LOOP);
        animationLeft.setPlayMode(Animation.PlayMode.LOOP);
        animationRight.setPlayMode(Animation.PlayMode.LOOP);

    }

    /**
     * Only call if player moved, receives the character
     * @param delta
     */
    public void update(float delta) {
        animationTimer += delta;
    }

    public float getX() {
        return dynamicEntity.getPosition().getX();
    }

    public float getY() {
        return dynamicEntity.getPosition().getY();
    }

    public void draw(SpriteBatch batch, Music music) {
        Animation animation;

        if (dynamicEntity.getState() == DynamicEntity.MOVING || dynamicEntity.getState() == DynamicEntity.ROTATING) {
            update(0.1f);
        }
        int orientation = dynamicEntity.getDirection().getCode();
        switch (orientation) {
            case Direction.UP:
                animation = animationUp;
                break;
            case Direction.DOWN:
                animation = animationDown;
                break;
            case Direction.LEFT:
                animation = animationLeft;
                break;
            case Direction.RIGHT:
                animation = animationRight;
                break;
            case Direction.DOWN_LEFT:
                animation = animationLeft;
                break;
            case Direction.DOWN_RIGHT:
                animation = animationRight;
                break;
            case Direction.UP_LEFT:
                animation = animationLeft;
                break;
            case Direction.UP_RIGHT:
                animation = animationRight;
                break;
            default:
                animation = animationUp;
        }
        if(dynamicEntity instanceof PlayerCharacter && dynamicEntity.getState()==DynamicEntity.MOVING) {
            music.play();
        }
        TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(animationTimer);
        batch.draw(currentFrame,dynamicEntity.getPosition().getX()-currentFrame.getRegionWidth()/2,dynamicEntity.getPosition().getY());
    }
    public DynamicEntity getDynamicEntity() {
        return dynamicEntity;
    }
    public Direction getDirection() {
        return dynamicEntity.getDirection();
    }
}