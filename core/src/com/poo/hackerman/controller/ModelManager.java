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
    private Manager manager;
    private Thread thread;

    public ModelManager(Manager manager) {
        gameModel=new GameModel();              //no le falta instanciar un GameModel y llamar a nextLevel()? (Sebas)
        entityManager = gameModel.getGameMap().getEntityManager();
        this.manager = manager;

    }

    public void initialize() {
        thread = new Thread(this, "Model manager thread");

        thread.start();
    }

    public void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        Direction dir = null;
        if(lPressed) dir = new Direction(Direction.LEFT);
        if(rPressed) dir = new Direction(Direction.RIGHT);
        if(uPressed) dir = new Direction(Direction.UP);
        if(dPressed) dir = new Direction(Direction.DOWN);

        if (lPressed || rPressed || uPressed || dPressed) {
            entityManager.getPlayer().tryToMove(dir);
        }

    }

    public void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                gameModel.tick();
                if(gameModel.gameOver()) {
                    manager.stateManager(Manager.STATE.GAME_OVER);
                }
                else if (gameModel.passedLevel() && gameModel.hasNextLevel()) {
                    gameModel.nextLevel();
                }
                else if (gameModel.passedLevel() && !gameModel.hasNextLevel()){
                    manager.stateManager(Manager.STATE.WON);
                }
            }
        };
        timer.schedule(task, 0, 5);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public Manager getManager() {return manager;}

    public EntityManager getEntityManager() {return entityManager;}

}
