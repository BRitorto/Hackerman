package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.controller.Manager;

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
    private static final int GAME_OVER_TEXT_WIDTH = 351;
    private static final int GAME_OVER_TEXT_HEIGHT = 43;
    //buttons
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;
    private static final int CENTER_X = 318;
    private static final int TOP_Y = 375;
    private static final int buttonX = CENTER_X + BUTTON_WIDTH;
    private static final int topY = TOP_Y - BUTTON_HEIGHT;

    public GameOverScreen(HackerGame game) {
        this.game = game;
        gameOver = new Texture(Gdx.files.internal("gameover.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitactive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitinactive.png"));
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
        game.getBatch().draw(gameOver, 231, 450, GAME_OVER_TEXT_WIDTH, GAME_OVER_TEXT_HEIGHT );
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > topY && Gdx.input.getY() < TOP_Y) {
            game.getBatch().draw(exitButtonActive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched()) {
                game.setState(Manager.STATE.EXIT_YES);
                game.setScreen((Screen)game.getGameScreen());
            }
        } else {
            game.getBatch().draw(exitButtonInactive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
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