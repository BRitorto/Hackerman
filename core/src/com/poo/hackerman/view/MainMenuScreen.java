package com.poo.hackerman.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 * Created by Bianca on 28/05/2017.
 */
public class MainMenuScreen implements Screen {

    private HackerGame game;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture hackermanTitle;
    
    private int GAME_HEIGHT;
    private int GAME_WIDTH;

    private static final int TITLE_WIDTH = 526;
    private static final int TITLE_HEIGHT = 64;
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;

    private int CENTER_TITLE;
    private int CENTER_X;
    private int PLAY_Y;
    private int EXIT_Y;
    private int buttonX;
    private int playY;
    private int exitY;

    public MainMenuScreen(HackerGame game) {

        this.game = game;

        playButtonInactive = new Texture("playinactive.png");
        playButtonActive = new Texture("playactive.png");
        exitButtonActive = new Texture("exitactive.png");
        exitButtonInactive = new Texture("exitinactive.png");
        hackermanTitle = new Texture ("hackerman.png");

        GAME_HEIGHT = Gdx.graphics.getHeight();
        GAME_WIDTH = Gdx.graphics.getWidth();

        CENTER_TITLE = GAME_WIDTH/2 - TITLE_WIDTH/2;
        CENTER_X = GAME_WIDTH/2 - BUTTON_WIDTH/2;
        PLAY_Y = GAME_HEIGHT - 2*GAME_HEIGHT/4;
        EXIT_Y = GAME_HEIGHT - GAME_HEIGHT/4;
        buttonX = CENTER_X + BUTTON_WIDTH;

        playY = PLAY_Y - BUTTON_HEIGHT;
        exitY = EXIT_Y - BUTTON_HEIGHT;
    }

    @Override
    public void render(float delta) {
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
        game.getBatch().draw(hackermanTitle, CENTER_TITLE ,3*GAME_HEIGHT/4, TITLE_WIDTH, TITLE_HEIGHT);

        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > playY && Gdx.input.getY() < PLAY_Y ) {
            game.getBatch().draw(playButtonActive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                game.setState(HackerGame.STATE.INITIALIZE);
            }
        } else {
            game.getBatch().draw(playButtonInactive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > exitY && Gdx.input.getY() < EXIT_Y) {
            game.getBatch().draw(exitButtonActive, CENTER_X, GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setState(HackerGame.STATE.EXIT_YES);
            }
        } else {
            game.getBatch().draw(exitButtonInactive, CENTER_X, GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
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