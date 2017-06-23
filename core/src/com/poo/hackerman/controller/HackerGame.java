package com.poo.hackerman.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.view.*;

/**
 * Created by Bianca on 07/06/2017.
 */
public class HackerGame extends Game {

    private SpriteBatch batch;

    public enum STATE {CREATED, INITIALIZE, EXIT, EXIT_YES, PAUSE, RESUME, NEW_LEVEL, GAME_OVER, WON}
    private STATE state = STATE.CREATED;

    private ModelManager modelManager;

    private MainMenuScreen mainMenuScreen;
    private ExitScreen exitScreen;
    private PausedScreen pausedScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;
    private WonScreen wonScreen;

    private Viewport viewport;
    private Camera camera;


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
                gameScreen = new GameScreen(this);
                setScreen(gameScreen);
            } break;

            case EXIT: {
                exitScreen = new ExitScreen(this);
                modelManager.getGameModel().setPause();
                setScreen(exitScreen);
            } break;

            case EXIT_YES: {
                Gdx.app.exit();
            } break;

            case PAUSE: {
                pausedScreen = new PausedScreen(this);
                modelManager.getGameModel().setPause();
                setScreen(pausedScreen);
            } break;

            case RESUME: {
                modelManager.getGameModel().resume();
                setScreen(gameScreen);
            } break;

            case NEW_LEVEL: {
            }
            break;

            case GAME_OVER: {
                gameOverScreen = new GameOverScreen(this);
                modelManager.getGameModel().setPause();
                setScreen(gameOverScreen);
            } break;

            case WON: {
                wonScreen = new WonScreen(this);
                setScreen(wonScreen);
                Gdx.app.exit();
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

    //no lo pushe

}
