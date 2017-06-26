package com.poo.hackerman.model.test;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.gameWorld.Cell;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by aatar on 26/06/2017.
 */
public class CellTest {
    private Entity entity;
    private Cell cell;

    @Before
    public void createEntities() {
        entity = new PlayerCharacter(new Position(112,80), new Direction(Direction.UP), 10);
        cell = new Cell();
    }

    @Test
    public void shoudBeEmpty() {
        assertTrue(cell.isEmpty());

        cell.add(entity);
        cell.free();
        assertTrue(cell.isEmpty());
    }

    @Test
    public void shouldntBeEmpty() throws Exception {
        cell.add(entity);
        assertFalse(cell.isEmpty());
    }

}
