package com.poo.hackerman.model.entity.dynamicEntity.character;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.DynamicEntity;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 * Created by franciscosanguineti on 5/6/17.
 */
public abstract class GameCharacter extends DynamicEntity {

    private int movesRemaining;
    private Timer timer;

    private static final long TIME_ROTATING = 40;


    protected GameCharacter(Position position, Direction direction, int velocity) {
        super(position, direction, velocity);
        timer = new Timer(velocity);
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    protected Timer getTimer() {
        return timer;
    }

    /**
     *
     * @param direction Direction to move. Tries to move the character in that direction.
     */
    public void tryToMove(Direction direction) {
        if (state != IDLE || direction == null) {
            return;
        }
        if(!this.direction.equals(direction)) {
            timer.updateLastRotateTime(System.currentTimeMillis(), TIME_ROTATING);
            state = ROTATING;
            rotate(direction);
            return;
        }
        int[] dir = direction.getDir();
                                                                                //dividido 4
        Position destination = new Position(getPosition().getX() + dir[0] * (GameMap.CELL_SIZE/4), getPosition().getY() + dir[1] * (GameMap.CELL_SIZE/4));

        if (goodDestination(destination)) {
            state = MOVING;
            movesRemaining = GameMap.CELL_SIZE/4;
            timer.updateLastMoveTime(System.currentTimeMillis(), direction);
            if(!destination.sameGridIndex(getPosition())) {        //prueba
                grid.freePosition(getPosition());
                grid.add(this, destination);
            }
        }
    }

    /**
     * Moves the game character a cell unit
     */
    protected void move() {
        if (movesRemaining <= 0) {
            updateStatus();
            return;
        }

        long nowTime = System.currentTimeMillis();

        if (timer.moveTimePassed(nowTime)) {
            timer.updateLastMoveTime(nowTime);

            movesRemaining--;

            int[] dir = direction.getDir();
            Position aux = new Position(getPosition().getX(), getPosition().getY());               //prueba
            getPosition().incrementPosition(dir[0], dir[1]);
        }
    }

    protected void updateStatus() {
        if (state == MOVING && movesRemaining <= 0) {
            state = IDLE;
        }
        if (state == ROTATING && timer.rotateTimePassed(System.currentTimeMillis())) {
            state = IDLE;
        }
    }

    // Check destination is within the borders of the map, and its a valid destination.
    private boolean goodDestination(Position destination) {
        return grid.getCell(destination).getEntity() == this || (destination.withinBoundaries() && grid.isPossibleAdd(destination));
    }
}
