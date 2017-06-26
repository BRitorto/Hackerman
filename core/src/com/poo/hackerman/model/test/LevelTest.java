package com.poo.hackerman.model.test;

import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.gameWorld.Level;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by aatar on 26/06/2017.
 */
public class LevelTest {
    private Level level;

    @Before
    public void createLevel() {
        level = new Level("../assets/levels/level1.txt");
    }

    @Test
    public void readsHacker() {
        assertEquals(new Position(928,928),level.getEntityManager().getPlayer().getPosition());
        assertEquals(2,level.getEntityManager().getPlayer().getDirection().getCode());
    }

    @Test
    public void readsDoor()
    {
        assertEquals(new Position(736,32),level.getEntityManager().getDoor().getPosition());
        assertEquals(0,level.getEntityManager().getDoor().getDirection().getCode());
    }

}
