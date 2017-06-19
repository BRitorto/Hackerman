package com.poo.hackerman.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.view.*;

/**
 * Created by Bianca on 07/06/2017.
 */
public class HackerGame extends Game {

    private SpriteBatch batch;
    
    private ModelManager modelManager;
    private EntityManager entityManager;
    private Manager manager;

    private MainMenuScreen mainMenuScreen;
    private ExitScreen exitScreen;
    private PausedScreen pausedScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;
    private WonScreen wonScreen;


    public HackerGame () {
        this.manager = new Manager(this);
        this.modelManager = manager.getModelManager();
        this.entityManager = modelManager.getEntityManager();
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
        modelManager.initialize();

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

    public void setState (Manager.STATE state) {
        modelManager.getManager().stateManager(state);
    }

    public EntityManager getEntityManager() { return this.entityManager; }

    public SpriteBatch getBatch() {
        return batch;
    }

}
