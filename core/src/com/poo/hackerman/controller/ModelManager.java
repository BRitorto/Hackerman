package com.poo.hackerman.controller;



import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.Managers.GameModel;

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

        entityManager = gameModel.getMap().getEntityManager();
        manager = manager;
        gameModel = new GameModel();

    }

    public void initialize() {
        thread = new Thread(this, "Model manager thread");

        thread.start();
    }

    public void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                gameModel.tick();
                if(gameModel.gameOver()) {
                    manager.gameOver();
                }
                if (gameModel.passedLevel() && gameModel.hasNextLevel()) {
                    manager.passedLevel();                              //screen de next kevel
                }
                else {
                    manager.gameWon();
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
