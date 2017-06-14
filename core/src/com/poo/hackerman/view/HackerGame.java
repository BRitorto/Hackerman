package com.poo.hackerman.view;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poo.hackerman.controller.ModelManager;
import com.poo.hackerman.model.Managers.EntityManager;

/**
 * Created by Bianca on 07/06/2017.
 */
public class HackerGame extends Game {

    SpriteBatch batch;
    private UIManager UIManager;
    private ModelManager modelManager;
    private EntityManager entityManager;

    private MainMenuScreen mainMenuScreen;
    private ExitScreen exitScreen;
    private PausedScreen pausedScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;


    public HackerGame (UIManager UIManager, ModelManager modelManager) {
        this.UIManager = UIManager;
        this.modelManager = modelManager;
        this.entityManager = modelManager.getEntityManager();
        mainMenuScreen = new MainMenuScreen(this);
        exitScreen = new ExitScreen(this);
        pausedScreen = new PausedScreen(this);
        gameOverScreen = new GameOverScreen(this);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
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

    public UIManager getUIManager() {return this.UIManager;}

    public ModelManager getModelManager() {return this.modelManager;}


    public Screen getWonScreen() {
        return null;//wonScreen;
    }
}
