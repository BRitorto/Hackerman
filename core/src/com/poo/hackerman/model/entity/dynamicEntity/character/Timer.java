package com.poo.hackerman.model.entity.dynamicEntity.character;

import com.poo.hackerman.model.entity.Direction;

import java.io.Serializable;

/**
 * Created by franciscosanguineti on 30/5/17.
 */
public class Timer implements Serializable {
    private static final long serialVersionUID = 1L;

    // Cooldown times for characters
    private static final long MOVE_COOLDOWN_BASE = 15; // in ms

    // Time attributes for characters
    private long lastMoveTime;
    private long moveCooldown;
    private long lastRotateTime;
    private long rotateCooldown;

    // State
    private int state;

    private static final int NODIAGONAL = 0;
    private static final int DIAGONAL = 1;

    public Timer(int velocity) {
        lastMoveTime = 0;
        if (velocity == 0) {
            moveCooldown = Long.MAX_VALUE;
        }
        else {
            moveCooldown = MOVE_COOLDOWN_BASE / velocity;
        }
        state = NODIAGONAL;
    }

    /**
     * Gets the time for the last movement
     * @return the time for the last movement
     */

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    /**
     * Sets the time for last movement
     * @param nowTime time to update the last movement's time
     * @param direction update the state for diagonal or not diagonal direction
     */

    public void updateLastMoveTime(long nowTime, Direction direction) {
        this.lastMoveTime = nowTime;
        if(direction.isDiagonal()){
            this.state = DIAGONAL;
        }
        else {
            this.state = NODIAGONAL;
        }
    }

    public void updateLastMoveTime(long nowTime) {
        this.lastMoveTime = nowTime;
    }

    /**
     * Compares movement time elapsed with the default cooldown or a cooldown for diagonal direcction
     * A cooldown for diagonal direction is 14 / 10 times bigger than default cooldown
     * @param nowTime time to compare
     * @return true if elapsed time overpass the cooldown and false otherwise.
     */

    public boolean moveTimePassed(long nowTime) {
        return (nowTime - this.getLastMoveTime() >= moveCooldown + ((float)(state * moveCooldown * 4) / (float)10));
    }

    public long getLastRotateTime() {
        return lastRotateTime;
    }

    public void updateLastRotateTime(long nowTime, long rotateCooldown) {
        this.rotateCooldown = rotateCooldown;
        this.lastRotateTime = nowTime;
    }

    public void updateLastRotateTime(long nowTime) {
        this.lastRotateTime = nowTime;
    }

    public boolean rotateTimePassed(long nowTime) {
        return (nowTime - this.getLastRotateTime() >= rotateCooldown);
    }
}
