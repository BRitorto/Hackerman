package com.poo.hackerman.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;

/**
 * Created by Bianca on 29/05/2017.
 */
public class PausedScreen implements Screen {

    private HackerGame game;
    //texture
    private Texture resumeButtonActive;
    private Texture resumeButtonInactive;
    private Texture gamePaused;
    //texts
    private static final int PAUSED_TEXT_WIDTH = 352;
    private static final int PAUSED_TEXT_HEIGHT = 43;
    //buttons
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;
    private static final int CENTER_X = 318;
    private static final int TOP_Y = 375;
    private static final int buttonX = CENTER_X + BUTTON_WIDTH;
    private static final int topY = TOP_Y - BUTTON_HEIGHT;

    public PausedScreen(HackerGame game){

        this.game = game;
        resumeButtonInactive = new Texture(Gdx.files.internal("resumeinactive.png"));
        resumeButtonActive = new Texture(Gdx.files.internal("resumeactive.png"));
        gamePaused = new Texture(Gdx.files.internal("gamepaused.png"));
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
        game.getBatch().draw(gamePaused, 76, 450, PAUSED_TEXT_WIDTH, PAUSED_TEXT_HEIGHT );

        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > topY && Gdx.input.getY() < TOP_Y) {
            game.getBatch().draw(resumeButtonActive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched()) {
                dispose();
                game.setState(HackerGame.STATE.RESUME);
            }
        } else {
            game.getBatch().draw(resumeButtonInactive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
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