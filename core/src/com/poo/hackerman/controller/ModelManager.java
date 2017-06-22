package com.poo.hackerman.controller;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.Managers.GameModel;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by franciscosanguineti on 4/6/17.
 */
public class ModelManager implements Runnable{

    private GameModel gameModel;
    private EntityManager entityManager;
    private HackerGame game;
    private Thread thread;

    /**
     *
     * Creates a new GameModel which in turn creates a grid and reads a Level from
     * the level files
     */

    public ModelManager(HackerGame game) {
        gameModel = new GameModel();
        this.game = game;

    }

    /**
     * Is in charge of initializing the thread in which the game will run on
     * It assigns the EntityManager so that it can be accessed by the view
     *
     */

    public void initialize() {
        gameModel.nextLevel();
        thread = new Thread(this, "Model manager thread");
        thread.start();
        entityManager = gameModel.getGameMap().getEntityManager();
    }

    /**
     *
     * Reads and processes the user's input
     */

    public void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean pPressed = Gdx.input.isKeyPressed(Input.Keys.P);
        boolean ePressed = Gdx.input.isKeyPressed(Input.Keys.E);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        if(sPressed) {
            entityManager.getPlayer().setInteracting();
        }
        if (pPressed)
            game.setState(HackerGame.STATE.PAUSE);

        if (ePressed)
            game.setState(HackerGame.STATE.EXIT);

        if (lPressed || rPressed || uPressed || dPressed) {
            Direction dir = getDirection(lPressed,rPressed,dPressed,uPressed);
            entityManager.getPlayer().tryToMove(dir);
        }

    }

    /**
     *
     * Creates a new direction based on the user's input
     */

    private Direction getDirection(boolean lPressed, boolean rPressed, boolean dPressed, boolean uPressed) {
        if(rPressed && uPressed)
            return new Direction(Direction.DOWN_RIGHT);
        else if(lPressed && uPressed)
            return new Direction(Direction.DOWN_LEFT);
        else if(rPressed && dPressed)
            return new Direction(Direction.UP_RIGHT);
        else if(lPressed && dPressed)
            return new Direction(Direction.UP_LEFT);
        else if(lPressed)
            return new Direction(Direction.LEFT);
        else if(rPressed)
            return new Direction(Direction.RIGHT);
        else if(uPressed)
            return new Direction(Direction.DOWN);
        else //if(dPressed)
            return new Direction(Direction.UP);
    }

    /**
     *
     * Is in charge of making the entities tick and of setting up the next level
     * if the user passes the current one
     * It also asks the GameModel all the time whether or not the player has lost the game
     * If he has, it sets the state to "Game Over"
     *
     */

    public void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                gameModel.tick();
                if(gameModel.gameOver()) {
                    game.setState(HackerGame.STATE.GAME_OVER);
                }
                else if (gameModel.passedLevel() && gameModel.hasNextLevel()) {
                    gameModel.nextLevel();
                }
                else if (gameModel.passedLevel() && !gameModel.hasNextLevel()){
                    game.setState(HackerGame.STATE.WON);
                }
            }
        };
        timer.schedule(task, 0, 5);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public HackerGame getGame() {return game;}

    public EntityManager getEntityManager() {return gameModel.getGameMap().getEntityManager();}

}
