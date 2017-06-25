package com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;

/**
 *
 */
public class Computer extends InteractiveStaticEntity {

    private ComputerTimer timer;
    private int consecutiveHacks;
    private int currentConsecutiveHacks;
    private boolean isOn;
    private boolean isHacked;

    public Computer(Position position, Direction direction, int consecutiveHacks) {
        super(position, direction);
        timer = new ComputerTimer();
        this.consecutiveHacks = consecutiveHacks;
        this.currentConsecutiveHacks = 0;
        isOn = false;
        isHacked = false;
    }

    /**
     *
     * This function is called when a PlayerCharacter wants to interact with a computer
     * (i.e. presses the space bar in front of it).
     * It checks whether or not the player has pressed the space bar long enough to "hack"
     * the computer. Each computer has a different "hack time" determined by the variable
     * "consecutiveHacks".
     * If the player manages to hack the computer, isHacked gets set to "true"
     *
     */

    public void interact() {
        if(isOn) {
            long nowTime = System.currentTimeMillis();
            if (timer.hackTimePassed(nowTime)) {
                timer.updateLastHackTime(nowTime);
                currentConsecutiveHacks = 1;
                return;
            }

            timer.updateLastHackTime(nowTime);
            currentConsecutiveHacks++;

            if(currentConsecutiveHacks >= consecutiveHacks) {
                isHacked = true;
            }
        }
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    public void setOn() {
        isOn = true;
    }

    public void setOff() {
        isOn = false;
    }

    public boolean isHacked() {
        return isHacked;
    }

    public boolean isOn() {
        return isOn;
    }
}
