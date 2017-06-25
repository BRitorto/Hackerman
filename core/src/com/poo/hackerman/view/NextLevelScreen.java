package com.poo.hackerman.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 * Created by Bianca on 24/06/2017.
 */
public class NextLevelScreen implements Screen {

    private HackerGame game;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;
    //texture
    private Texture nextLevelButtonActive;
    private Texture nextLevelButtonInactive;
    //buttons
    private static final int BUTTON_WIDTH = 219*2;
    private static final int BUTTON_HEIGHT = 73*2;
    private int CENTER_X;
    private int buttonXHigh;
    private int buttonXLow;
    private int buttonYLow;
    private int buttonYHigh;

    public NextLevelScreen(HackerGame game) {
        this.game = game;
        nextLevelButtonActive = new Texture(Gdx.files.internal("nextlevelactive.png"));
        nextLevelButtonInactive = new Texture(Gdx.files.internal("nextlevelinactive.png"));

        GAME_HEIGHT = GameMap.HEIGHT;
        GAME_WIDTH = GameMap.WIDTH;

        CENTER_X = GAME_WIDTH/2 - BUTTON_WIDTH/2;

        buttonXLow = Gdx.graphics.getWidth()/2 - BUTTON_WIDTH/4;
        buttonXHigh = (Gdx.graphics.getWidth()/2 - BUTTON_WIDTH/4) + BUTTON_WIDTH/2;

        buttonYLow = buttonYHigh + BUTTON_HEIGHT + 76;
        buttonYHigh = Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/2;
    }

    @Override
    public void render(float delta) {
        this.pause();
        clearScreen();

        game.getBatch().begin();
        draw();
        game.getBatch().end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void draw() {
        if (Gdx.input.getX() < buttonXHigh && Gdx.input.getX() > buttonXLow
                && Gdx.input.getY() > buttonYLow && Gdx.input.getY() < buttonYHigh) {
            game.getBatch().draw(nextLevelButtonActive, CENTER_X, GAME_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                dispose();
                game.setState(HackerGame.STATE.RESUME);
            }
        } else {
            game.getBatch().draw(nextLevelButtonInactive, CENTER_X, GAME_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}