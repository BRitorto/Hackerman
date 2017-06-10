package com.poo.hackerman.model.test;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by brothers on 08/06/2017.
 */
public class PositionTest {
    Direction dir;


    @Test
    public void testDirectionBetween() throws Exception {
        assertEquals("Direction: [0,-1]", Direction.directionBetween(new Position(3,4), new Position(3,2)).toString());
        assertEquals("Direction: [0,1]", Direction.directionBetween(new Position(3,4), new Position(3,8)).toString());
    }
}
