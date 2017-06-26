package com.poo.hackerman.model.test;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
//import org.junit.Test;

//import static org.junit.Assert.assertEquals;

/**
 * Created by aatar on 08/06/2017.
 */
public class DirectionTest {
    private Direction dir;


    @Test
    public void DirectionBetween() {
        assertEquals("Direction: [0,-1]", Direction.directionBetween(new Position(3,4), new Position(3,2)).toString());
        assertEquals("Direction: [0,1]", Direction.directionBetween(new Position(3,4), new Position(3,8)).toString());
        assertEquals("Direction: [1,0]", Direction.directionBetween(new Position(3,4), new Position(6,4)).toString());
        assertEquals("Direction: [-1,0]", Direction.directionBetween(new Position(3,4), new Position(1,4)).toString());
    }

}
