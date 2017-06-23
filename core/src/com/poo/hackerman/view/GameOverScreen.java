package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 * Created by Bianca on 29/05/2017.
 */
public class GameOverScreen implements Screen {

    private HackerGame game;
    //texture
    private Texture gameOver;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    //text
    private static final int GAME_OVER_TEXT_WIDTH = 351*2;
    private static final int GAME_OVER_TEXT_HEIGHT = 43*2;
    //buttons
    private int CENTER_TITLE;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;
    private static final int BUTTON_WIDTH = 219*2;
    private static final int BUTTON_HEIGHT = 73*2;
    private int CENTER_X;
    private int buttonXHigh;
    private int buttonXLow;
    private int buttonYLow;
    private int buttonYHigh;

    public GameOverScreen(HackerGame game) {
        this.game = game;
        gameOver = new Texture(Gdx.files.internal("gameover.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitactive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitinactive.png"));

        GAME_HEIGHT = GameMap.HEIGHT;
        GAME_WIDTH = GameMap.WIDTH;

        CENTER_TITLE = GAME_WIDTH/2 - GAME_OVER_TEXT_WIDTH/2;
        CENTER_X = GAME_WIDTH/2 - BUTTON_WIDTH/2;

        buttonXLow = Gdx.graphics.getWidth()/2 - BUTTON_WIDTH/4;
        buttonXHigh = (Gdx.graphics.getWidth()/2 - BUTTON_WIDTH/4) + BUTTON_WIDTH/2;

        buttonYLow = buttonYHigh + BUTTON_HEIGHT;
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
        game.getBatch().draw(gameOver, CENTER_TITLE, 3*GAME_HEIGHT/4, GAME_OVER_TEXT_WIDTH, GAME_OVER_TEXT_HEIGHT );
        if (Gdx.input.getX() < buttonXHigh && Gdx.input.getX() > buttonXLow
                && Gdx.input.getY() > buttonYLow && Gdx.input.getY() < buttonYHigh) {
            game.getBatch().draw(exitButtonActive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched()) {
                game.setState(HackerGame.STATE.EXIT_YES);
                game.setScreen((Screen)game.getGameScreen());
            }
        } else {
            game.getBatch().draw(exitButtonInactive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
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