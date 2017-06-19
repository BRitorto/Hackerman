package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.controller.Manager;

/**
 * Created by Bianca on 28/05/2017.
 */
public class MainMenuScreen implements Screen {

    private HackerGame game;
    private Texture loadGameButtonActive;
    private Texture loadGameButtonInactive;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture hackermanTitle;

    private static final int TITLE_WIDTH = 702;
    private static final int TITLE_HEIGHT = 86;
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;
    private static final int CENTER_X = 318;
    private static final int PLAY_Y = 375;
    //private static final int LOAD_Y = 475;
    private static final int EXIT_Y = 475;
    private static final int buttonX = CENTER_X + BUTTON_WIDTH;
    private static final int playY = PLAY_Y - BUTTON_HEIGHT;
    //private static final int loadY = LOAD_Y - BUTTON_HEIGHT;
    private static final int exitY = EXIT_Y - BUTTON_HEIGHT;

    public MainMenuScreen(HackerGame game) {

        this.game = game;

        playButtonInactive = new Texture(Gdx.files.internal("playinactive.png"));
        playButtonActive = new Texture("playactive.png");
        //loadGameButtonActive = new Texture("core/assets/loadgameactive.png");
        //loadGameButtonInactive = new Texture("core/assets/loadgameinactive.png");
        exitButtonActive = new Texture("exitactive.png");
        exitButtonInactive = new Texture("exitinactive.png");
        hackermanTitle = new Texture ("hackerman.png");
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
        game.getBatch().draw(hackermanTitle, 76, 450, TITLE_WIDTH, TITLE_HEIGHT);

        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > playY && Gdx.input.getY() < PLAY_Y) {
            game.getBatch().draw(playButtonActive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                game.setState(Manager.STATE.INITIALIZE);
            }
        } else {
            game.getBatch().draw(playButtonInactive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        /*if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > loadY && Gdx.input.getY() < LOAD_Y) {
            game.getBatch().draw(loadGameButtonActive, CENTER_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        } else {
            game.getBatch().draw(loadGameButtonInactive, CENTER_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        }*/
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > exitY && Gdx.input.getY() < EXIT_Y) {
            game.getBatch().draw(exitButtonActive, CENTER_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setState(Manager.STATE.EXIT_YES);
            }
        } else {
            game.getBatch().draw(exitButtonInactive, CENTER_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
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