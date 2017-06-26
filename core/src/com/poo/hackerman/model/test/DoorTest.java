package com.poo.hackerman.model.test;

import com.poo.hackerman.model.Managers.ComputerManager;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by aatar on 26/06/2017.
 */
public class DoorTest {
    private Door door;
    private ComputerManager computerManager;
    private List<Computer> computers;

    @Before
    public void createEntities() {
        door = new Door(new Position(0,0), new Direction(0));
        computers = new ArrayList<>();
        computers.add(new Computer(new Position(0,0),new Direction(0),0));
        computerManager = new ComputerManager(door, computers);
    }

    @Test
    public void isNotOpen()
    {
        assertFalse(door.isOpen());
    }


}