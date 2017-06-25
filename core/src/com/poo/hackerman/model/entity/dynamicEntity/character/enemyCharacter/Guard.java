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

    /**
     * @return the next direction in which the guard has to move
     */

    private Direction nextDirection() {
        if(!isCycle()) {
            updateOrientation();
        }
        return Direction.directionBetween(getPosition(), instructions.get(currentPosition));

    }

    /**
     * Updates the guard's current position depending on the instructions it has been handed
     */

    private void updateCurrentPosition() {
        if(getPosition().sameGridIndex(instructions.get(currentPosition))) {
            currentPosition = Math.floorMod(currentPosition + orientation, instructions.size());
        }
    }

    /**
     * Turns the guard around if the next instruction is for it to move backwards
     */

    private void updateOrientation() {
        if(getPosition().sameGridIndex(instructions.get(0))) {
            orientation = NORMAL_ORIENTATION;
        }
        else if(getPosition().sameGridIndex(instructions.get(instructions.size() - 1))) {
            orientation = INVERSE_ORIENTATION;
        }
    }

    /**
     *
     * @return true if the guard´s journey ends in the position it began
     */

    private boolean isCycle() {
        return instructions.get(0).sameGridIndex(instructions.get(instructions.size() - 1));
    }

    public boolean hackerDetected() {
        return playerDetected;
    }

    /**
     * @param position position to add to the instructions
     */

    public void addInstruction(Position position) {
        instructions.add(position);
    }

    /**
     *
     * @param index index of the position to add
     * @param position position to add to the instructions
     */

    public void addInstruction(int index, Position position) {
        instructions.add(index, position);
    }

    /**
     * Checks if each individual guard has detected the player or not
     * If the guard is idle, it sets it moving
     */

}

