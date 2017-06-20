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
public class ExitScreen implements Screen {

    private HackerGame game;
    //texture
    private Texture resumeButtonActive;
    private Texture resumeButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture exitGame;
    //text
    private static final int EXIT_TEXT_WIDTH = 392;
    private static final int EXIT_TEXT_HEIGHT = 43;
    //buttons
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;
    private static final int CENTER_X = 259;
    private static final int TOP_Y = 287;
    private static final int BOTTOM_Y = 387;
    private static final int buttonX = CENTER_X + BUTTON_WIDTH;
    private static final int topY = TOP_Y - BUTTON_HEIGHT;
    private static final int bottomY = BOTTOM_Y - BUTTON_HEIGHT;


    public ExitScreen(HackerGame game) {
        this.game = game;
        resumeButtonActive = new Texture(Gdx.files.internal("resumeactive.png"));
        resumeButtonInactive = new Texture(Gdx.files.internal("resumeinactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitactive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitinactive.png"));
        exitGame = new Texture(Gdx.files.internal("exitgame.png"));
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
        game.getBatch().draw(exitGame, 172, 400, EXIT_TEXT_WIDTH, EXIT_TEXT_HEIGHT);
        //resume button
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > topY && Gdx.input.getY() < TOP_Y) {
            game.getBatch().draw(resumeButtonActive, CENTER_X, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setState(HackerGame.STATE.RESUME);
            }
        } else {
            game.getBatch().draw(resumeButtonInactive, CENTER_X, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        //exit button
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > bottomY && Gdx.input.getY() < BOTTOM_Y) {
            game.getBatch().draw(exitButtonActive, CENTER_X, 150, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setState(HackerGame.STATE.EXIT_YES);
            }
        } else {
            game.getBatch().draw(exitButtonInactive, CENTER_X, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
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