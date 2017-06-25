package com.poo.hackerman.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 * Created by Sebastian on 29/05/2017.
 */
public class RetryScreen implements Screen {

    private HackerGame game;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;

    //texture
    private Texture resumeButtonActive;
    private Texture resumeButtonInactive;
    private Texture gamePaused;
    //texts
    private static final int RETRY_TEXT_WIDTH = 459*2;
    private static final int RETRY_TEXT_HEIGHT = 46*2;
    //buttons
    private int CENTER_TITLE;
    private static final int BUTTON_WIDTH = 219*2;
    private static final int BUTTON_HEIGHT = 73*2;
    private int CENTER_X;
    private int buttonXHigh;
    private int buttonXLow;
    private int buttonYLow;
    private int buttonYHigh;

    public RetryScreen(HackerGame game){

        this.game = game;
        resumeButtonInactive = new Texture(Gdx.files.internal("retryinactive.png"));
        resumeButtonActive = new Texture(Gdx.files.internal("retryactive.png"));
        gamePaused = new Texture(Gdx.files.internal("retrylevel.png"));

        GAME_HEIGHT = GameMap.HEIGHT;
        GAME_WIDTH = GameMap.WIDTH;

        CENTER_TITLE = GAME_WIDTH/2 - RETRY_TEXT_WIDTH/2;
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
        game.getBatch().draw(gamePaused, CENTER_TITLE ,3*GAME_HEIGHT/4, RETRY_TEXT_WIDTH, RETRY_TEXT_HEIGHT);

        if (Gdx.input.getX() < buttonXHigh && Gdx.input.getX() > buttonXLow
                && Gdx.input.getY() > buttonYLow && Gdx.input.getY() < buttonYHigh) {
            game.getBatch().draw(resumeButtonActive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                dispose();
                game.setState(HackerGame.STATE.RESUME);
            }
        } else {
            game.getBatch().draw(resumeButtonInactive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
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