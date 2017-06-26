package com.poo.hackerman.model.test;


import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import com.poo.hackerman.model.gameWorld.GameMap;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertTrue;


/**
 * Created by aatar on 08/06/2017.
 */

public class LightTest {
    private PlayerCharacter player;
    private Guard guard;
    private List<EnemyCharacter> enemies;
    private Door door;
    private Computer computer;
    private List<Computer> computers;
    private EntityManager entityManager;
    private GameMap gameMap;

    @Before
    public void createEntities() {
        player = new PlayerCharacter(new Position(112,80), new Direction(Direction.UP), 10);
        guard = new Guard(new Position(16,48), new Direction(Direction.DOWN_RIGHT), 10, 4);

        enemies = new ArrayList<EnemyCharacter>();
        enemies.add(guard);

        door = new Door(new Position(100,200), new Direction(Direction.UP));

        computer = new Computer(new Position(300,200), new Direction(Direction.UP),10);
        computers = new ArrayList<Computer>();
        computers.add(computer);

        entityManager = new EntityManager(player, door, enemies, computers, null);
        gameMap = new GameMap(entityManager);
    }

    @Test
    public void shouldntCollision() {
        player = new PlayerCharacter(new Position(112,80), new Direction(Direction.UP), 10);
        guard = new Guard(new Position(16,48), new Direction(Direction.DOWN_RIGHT), 10, 4);

        assertFalse("The guard's light didn't find the player",guard.getMylight().collision(guard.getPosition(), guard.getDirection(), gameMap.getGrid()));
    }


    @Test
    public void shouldCollision() {
        player = new PlayerCharacter(new Position(16,100), new Direction(Direction.UP), 10);
        guard = new Guard(new Position(16,60), new Direction(Direction.UP), 10, 4);

        assertTrue("The guard's light found the player",guard.getMylight().collision(guard.getPosition(), guard.getDirection(), gameMap.getGrid()));
    }

}