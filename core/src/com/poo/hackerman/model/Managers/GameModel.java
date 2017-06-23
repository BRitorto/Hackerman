package com.poo.hackerman.model.Managers;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import com.poo.hackerman.model.gameWorld.GameMap;
import com.poo.hackerman.model.gameWorld.Level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by franciscosanguineti on 4/6/17.
 */
public class GameModel {

    private GameMap gameMap;
    private ComputerManager computerManager;

    private boolean paused;
    private List<Level> levels;
    private int currentLevel;

    private int lives;
    private static final int MAX_LIVES = 3;


    public GameModel() {
        levels = new ArrayList<Level>();
        levels.add(new Level("levels/level1.txt"));
        levels.add(new Level("levels/level2.txt"));           //path con el level que quiero levantar
        levels.add(new Level("levels/level3.txt"));
        lives = MAX_LIVES;
        setPause();
        currentLevel = -1;                     //model no inicializado
    }

    /**
     *
     * Sets the next level of the game
     *
     */
    public void nextLevel() {
        currentLevel++;
        gameMap = new GameMap(levels.get(currentLevel).getEntityManager());
        computerManager = new ComputerManager(gameMap.getEntityManager().getDoor(), gameMap.getEntityManager().getComputers());
        resume();
    }

    public void setPause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

    public void tick() {
        if(!paused){
            gameMap.getEntityManager().tick();
            computerManager.updateComputers();
            if(playerCaught()) {
                lives--;
                setPause();
            }
        }
    }

    public boolean gameWon() {         //win
        return (currentLevel == levels.size()) && passedLevel();//
    }

    public boolean passedLevel() {
        if(currentLevel == -1) {       //no fue inicializado el gameModel
            return false;
        }
        return gameMap.getEntityManager().getDoor().hasBeenPassed();
    }

    public int getLives() {
        return lives;
    }

    public boolean gameOver() {
        return lives == 0;
    }

    public boolean hasNextLevel() {
        return currentLevel < levels.size();        //cambiar por levels.size
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public boolean playerCaught() {
        return gameMap.getEntityManager().playerCaught();
    }

    public void retryLevel() {
        currentLevel--;
        nextLevel();            //no aumento el currentLevel
    }
}
