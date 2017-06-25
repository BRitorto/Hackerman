package com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;

import java.util.ArrayList;
import java.util.List;

import static com.poo.hackerman.model.entity.Entity.IDLE;

/**
 * An Enemy that can´t move
 * Same functionalities of a guard but without changing position on the grid
 */

public class CameraGuard extends EnemyCharacter {

    private List<Direction> instructions;

    private int currentDirection;

    private int orientation;
    private static final int NORMAL_ORIENTATION = 1;
    private static final int INVERSE_ORIENTATION = -1;

    private boolean playerDetected;

    private static final int CAMERA_TIME_ROTATING = 2000;

    public CameraGuard(Position position, Direction direction, int range) {
        super(position, direction, 1, range);
        instructions = new ArrayList<Direction>();
        instructions.add(new Direction(direction.getCode()));
        currentDirection = 0;
        playerDetected = false;
        getTimer().updateLastRotateTime(System.currentTimeMillis(), CAMERA_TIME_ROTATING);
        state = ROTATING;
        orientation = NORMAL_ORIENTATION;
    }

    public CameraGuard(Position position, Direction direction, int range, List<Direction> instructions) {
        this(position,direction,range);
        this.instructions.addAll(instructions);
    }

    public void tick() {
        if(getMylight().collision(position, direction, grid)) {
            playerDetected = true;
        }
        if(instructions == null) {
            return;
        }
        if(getState() == IDLE) {
            currentDirection =  Math.floorMod(currentDirection + orientation, instructions.size());
            getTimer().updateLastRotateTime(System.currentTimeMillis(), CAMERA_TIME_ROTATING);
            state = ROTATING;
            rotate(nextDirection());
        }
        updateStatus();
    }

    private Direction nextDirection() {
        if(!isCycle()) {
            updateOrientation();
        }
        return instructions.get(currentDirection);
    }

    private void updateOrientation() {
        if(currentDirection == 0) {
            orientation = NORMAL_ORIENTATION;
        }
        else if(currentDirection == (instructions.size() - 1)) {
            orientation = INVERSE_ORIENTATION;
        }
    }

    private boolean isCycle() {
        return instructions.get(0).equals(instructions.get(instructions.size() - 1));
    }

    public void addInstruction(Direction direction) {
        instructions.add(direction);
    }

    public void addInstruction(Direction direction, int index) {
        instructions.add(index, direction);
    }

    public boolean hackerDetected() {
        return playerDetected;
    }
}

