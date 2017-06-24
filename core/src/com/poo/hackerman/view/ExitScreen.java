package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.gameWorld.GameMap;
import com.poo.hackerman.view.GameScreen;


/**
 * Created by Bianca on 29/05/2017.
 */
public class ExitScreen implements Screen {

    private HackerGame game;
    private int GAME_HEIGHT;
    private int GAME_WIDTH;
    //texture
    private Texture resumeButtonActive;
    private Texture resumeButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture exitGame;
    //text
    private static final int EXIT_TEXT_WIDTH = 392*2;
    private static final int EXIT_TEXT_HEIGHT = 43*2;
    //buttons
    private static final int BUTTON_WIDTH = 219*2;
    private static final int BUTTON_HEIGHT = 73*2;
    private int CENTER_X;
    private int CENTER_TITLE;


    private int buttonXHigh = CENTER_X + BUTTON_WIDTH;
    private int buttonXLow;
    private int buttonTopYLow;
    private int buttonTopYHigh;
    private int buttonBottomYLow;
    private int buttonBottomYHigh;


    public ExitScreen(HackerGame game) {
        this.game = game;
        resumeButtonActive = new Texture(Gdx.files.internal("resumeactive.png"));
        resumeButtonInactive = new Texture(Gdx.files.internal("resumeinactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitactive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitinactive.png"));
        exitGame = new Texture(Gdx.files.internal("exitgame.png"));

        GAME_HEIGHT = GameMap.HEIGHT;
        GAME_WIDTH = GameMap.WIDTH;

        CENTER_TITLE = GAME_WIDTH/2 - EXIT_TEXT_WIDTH/2;
        CENTER_X = GAME_WIDTH/2 - BUTTON_WIDTH/2;

        buttonXLow = Gdx.graphics.getWidth()/2 - BUTTON_WIDTH/4;
        buttonXHigh = (Gdx.graphics.getWidth()/2 - BUTTON_WIDTH/4) + BUTTON_WIDTH/2;

        buttonTopYLow = buttonTopYHigh + BUTTON_HEIGHT + 76;
        buttonTopYHigh = Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/2;

        buttonBottomYHigh = Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/4;
        buttonBottomYLow = buttonBottomYHigh - BUTTON_HEIGHT/2;


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
        game.getBatch().draw(exitGame, CENTER_TITLE, 3*GAME_HEIGHT/4, EXIT_TEXT_WIDTH, EXIT_TEXT_HEIGHT);
        //resume button
        if (Gdx.input.getX() < buttonXHigh && Gdx.input.getX() > buttonXLow
                && Gdx.input.getY() > buttonTopYLow && Gdx.input.getY() < buttonTopYHigh) {
            game.getBatch().draw(resumeButtonActive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setState(HackerGame.STATE.RESUME);
            }
        } else {
            game.getBatch().draw(resumeButtonInactive, CENTER_X, 2*GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        //exit button
        if (Gdx.input.getX() < buttonXHigh && Gdx.input.getX() > buttonXLow
                && Gdx.input.getY() > buttonBottomYLow && Gdx.input.getY() < buttonBottomYHigh) {
            game.getBatch().draw(exitButtonActive, CENTER_X, GAME_HEIGHT/4, BUTTON_WIDTH, BUTTON_HEIGHT );
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