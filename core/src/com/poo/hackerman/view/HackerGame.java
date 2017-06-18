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
    private WonScreen wonScreen;


    public HackerGame (UIManager UIManager, ModelManager modelManager) {
        this.UIManager = UIManager;
        this.modelManager = modelManager;
        this.entityManager = modelManager.getEntityManager();
    }

    @Override
    public void create() {
        System.out.println("create - HackerGame");
        batch = new SpriteBatch();
    }

    public void setMainMenuScreen(HackerGame game) {
        System.out.println("main");
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }
    public void setExitScreen(HackerGame game) {
        System.out.println("exit");
        exitScreen = new ExitScreen(this);
        setScreen(exitScreen);
    }
    public void setPausedScreen(HackerGame game) {
        System.out.println("paused");
        pausedScreen = new PausedScreen(this);
        setScreen(pausedScreen);
    }
    public void setGameOverScreen(HackerGame game) {
        System.out.println("gameover");
        gameOverScreen = new GameOverScreen(this);
        setScreen(gameOverScreen);
    }
    public void setWonScreen(HackerGame game) {
        System.out.println("won");
        wonScreen = new WonScreen(this);
        setScreen(wonScreen);
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

    public Screen getWonScreen() {return wonScreen;}
}
