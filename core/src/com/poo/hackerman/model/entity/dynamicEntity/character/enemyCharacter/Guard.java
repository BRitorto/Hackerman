package com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.gameWorld.GameMap;

import java.util.ArrayList;
import java.util.List;

import static com.poo.hackerman.model.entity.Entity.IDLE;


/**
 * An Enemy that moves
 */
public class Guard extends EnemyCharacter {

    private List<Position> instructions;
    private int currentPosition;
    private static final int NORMAL_ORIENTATION = 1;        //recorre en el sentido en que se guardan las posiciones
    private static final int INVERSE_ORIENTATION = -1;
    private int orientation = NORMAL_ORIENTATION;
    private boolean playerDetected;

    public Guard(Position position, Direction direction, int velocity, int range) {
        super(position, direction, velocity, range);
        instructions = new ArrayList<Position>();
        instructions.add(new Position(position.getX(), position.getY()));
        currentPosition = 0;
        playerDetected = false;
    }

    public Guard(Position position, Direction direction, int velocity, int range, List<Position> instructions) {
        super(position, direction, velocity, range);
        this.instructions = instructions;
        currentPosition = 0;
        playerDetected = false;
    }

    /**
     * @param position Position to add to the instructions
     */
    public void addInstruction(Position position) {
        instructions.add(position);
    }

    /**
     *
     * @param index Index of the position to add
     * @param position Position to add to the instructions
     */
    public void addInstruction(int index, Position position) {
        instructions.add(index, position);
    }

    /**
     *
     * Checks if each individual guard has detected the player or not
     * If the guard is idle, it sets it moving
     *
     */
    public void tick() {
        if(getMylight().collision(position, direction, grid)) {
            playerDetected = true;
        }
        if(instructions == null) {
            return;
        }
        if(getState() == IDLE) {
            updateCurrentPosition();
            Direction direction = nextDirection();
            tryToMove(direction);
        }
        move();
        updateStatus();
    }

    public boolean hackerDetected() {
        return playerDetected;
    }

    /**
     *
     * @return the next direction in which de guard has to move
     */
    private Direction nextDirection() {
        if(!isCycle()) {
            updateOrientation();
        }
        return Direction.directionBetween(getPosition(), instructions.get(currentPosition));

    }

    private void updateCurrentPosition() {
        if(getPosition().sameGridIndex(instructions.get(currentPosition))) {       //si mi posicion es una de la lista
            currentPosition = Math.floorMod(currentPosition + orientation, instructions.size());        //x+y % size
        }
    }

    private void updateOrientation() {
        if(getPosition().sameGridIndex(instructions.get(0))) {               //si estoy en la primer direccion
            orientation = NORMAL_ORIENTATION;
        }
        else if(getPosition().sameGridIndex(instructions.get(instructions.size() - 1))) {          //si estoy en la ultima posision
            orientation = INVERSE_ORIENTATION;
        }
    }

    /**
     *
     * @return True if the guard´s journey ends in the position it began
     */
    private boolean isCycle() {
        return instructions.get(0).sameGridIndex(instructions.get(instructions.size() - 1));
    }

}

