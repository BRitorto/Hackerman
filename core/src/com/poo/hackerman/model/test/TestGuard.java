package com.poo.hackerman.model.test;

import com.poo.hackerman.controller.ModelManager;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscosanguineti on 7/6/17.
 */
public class TestGuard {

    public static void main(String[] args) {
        PlayerCharacter player = new PlayerCharacter(new Position(80,16), new Direction(Direction.UP), 10);
        Guard guard = new Guard(new Position(80,300), new Direction(Direction.DOWN_RIGHT), 10, 4);

        guard.addInstruction(new Position(80,16));

        List<EnemyCharacter> enemies = new ArrayList<EnemyCharacter>();
        enemies.add(guard);

        Door door = new Door(new Position(200,200), new Direction(Direction.UP));

        Computer computer = new Computer(new Position(300,200), new Direction(Direction.UP),10);
        List<Computer> computers = new ArrayList<Computer>();
        computers.add(computer);


        EntityManager entityManager = new EntityManager(player, door, enemies, computers, null);
        /*ModelManager modelManager = new ModelManager();

        modelManager.getGameModel().nextLevel(entityManager);

        modelManager.initialize();*/
    }
}

