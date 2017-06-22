package com.poo.hackerman.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.view.*;

/**
 * Created by Bianca on 07/06/2017.
 */
public class HackerGame extends Game {

    private SpriteBatch batch;

    public enum STATE {CREATED, INITIALIZE, EXIT, EXIT_YES, PAUSE, RESUME, GAME_OVER, WON}
    private STATE state = STATE.CREATED;

    private ModelManager modelManager;
    private EntityManager entityManager;

    private MainMenuScreen mainMenuScreen;
    private ExitScreen exitScreen;
    private PausedScreen pausedScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;
    private WonScreen wonScreen;

    /**
     *
     * creates a new instance that extends from an ApplicationListener
     * HackerGame creates a new ModelManager which is going to initialize the model
     * this class manages the Game's screens according to the state and initializes and saves the batch
     */
    public HackerGame () {
        this.modelManager = new ModelManager(this);
    }

    @Override
    public void create() {

        batch = new SpriteBatch();
        mainMenuScreen = new MainMenuScreen(this);
        exitScreen = new ExitScreen(this);
        pausedScreen = new PausedScreen(this);
        gameOverScreen = new GameOverScreen(this);
        wonScreen = new WonScreen(this);

        setScreen(mainMenuScreen);
    }

    /**
     *
     * @param state
     * Recieves a state and sets the game's state depending on the variable recieved
     * According to the user input, it tells the Model what to to with the game
     *
     */

    public void setState (STATE state) {
        switch (state) {

            case INITIALIZE: {
                modelManager.initialize();
                createGameScreen(this);
                setScreen(gameScreen);
            } break;

            case EXIT: {
                modelManager.getGameModel().setPause();
                setScreen(getExitScreen());
            } break;

            case EXIT_YES: {
                Gdx.app.exit();
            } break;

            case PAUSE: {
                modelManager.getGameModel().setPause();
                setScreen(pausedScreen);
            } break;

            case RESUME: {
                modelManager.getGameModel().resume();
                setScreen(gameScreen);
            } break;

            case GAME_OVER: {
                modelManager.getGameModel().setPause();
                setScreen(gameOverScreen);
            } break;

            case WON: {
                setScreen(wonScreen);
                Gdx.app.exit();
            } break;
        }
    }

    public void createGameScreen(HackerGame game) {
        gameScreen = new GameScreen(this);
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    public GameOverScreen getGameOverScreen() {
        return this.gameOverScreen;
    }

    public ExitScreen getExitScreen() {
        return this.exitScreen;
    }

    public PausedScreen getPausedScreen() {
        return this.pausedScreen;
    }

    public ModelManager getModelManager() {return this.modelManager;}

    public Screen getWonScreen() {return wonScreen;}

    public EntityManager getEntityManager() { return this.entityManager; }

    public SpriteBatch getBatch() {
        return batch;
    }

    //no lo pushe

}
