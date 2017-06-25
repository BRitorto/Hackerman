package com.poo.hackerman.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poo.hackerman.view.*;

/**
 * Created by Bianca on 07/06/2017.
 */
public class HackerGame extends Game {

    private SpriteBatch batch;

    public enum STATE {CREATED, INITIALIZE, EXIT, EXIT_YES, PAUSE, RESUME, NEW_LEVEL, GAME_OVER, WON, RETRY}
    private STATE state = STATE.CREATED;

    private ModelManager modelManager;

    private MainMenuScreen mainMenuScreen;
    private ExitScreen exitScreen;
    private PausedScreen pausedScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;
    private WonScreen wonScreen;
    private RetryScreen retryScreen;
    private NextLevelScreen nextLevelScreen;

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
        pausedScreen = new PausedScreen(this);
        exitScreen = new ExitScreen(this);
        gameOverScreen = new GameOverScreen(this);
        wonScreen = new WonScreen(this);
        retryScreen = new RetryScreen(this);
        nextLevelScreen = new NextLevelScreen(this);

        setScreen(mainMenuScreen);
    }

    /**
     * @param state sets the game's state according to this variable
     * According to the user input, it tells the Model what to to with the game
     *
     */

    public void setState (STATE state) {
        switch (state) {
            case INITIALIZE: {
                modelManager.initialize();
                gameScreen = new GameScreen(this);
                setScreen(gameScreen);
            } break;

            case EXIT: {
                modelManager.getGameModel().setPause();
                setScreen(exitScreen);
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

            case NEW_LEVEL: {
                modelManager.getGameModel().setPause();
                setScreen(nextLevelScreen);
            }
            break;

            case GAME_OVER: {
                modelManager.getGameModel().setPause();
                setScreen(gameOverScreen);
            } break;

            case WON: {
                setScreen(wonScreen);
                Gdx.app.exit();
            } break;

            case RETRY: {
                modelManager.getGameModel().setPause();
                setScreen(retryScreen);
            } break;
        }
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

    public SpriteBatch getBatch() {
        return batch;
    }

}
