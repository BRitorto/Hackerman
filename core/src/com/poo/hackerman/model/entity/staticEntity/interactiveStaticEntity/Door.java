package com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;

/**
 * Created by franciscosanguineti on 25/5/17.
 */
public class Door extends InteractiveStaticEntity{

    private boolean isOpen = false;
    private boolean passed = false;

    /**
     * All doors are closed by default. isOpen gets set to true when the
     * hacker hacks all the computers in the game.
     * A door is passed when the hacker presses the space bar in front of it after it
     * has been opened
     */

    public void interact() {
        if(isOpen) {
            passed = true;
        }
    }

    public Door(Position position, Direction direction) {
        super(position,direction);
    }

    public void setOpen() {
        isOpen = true;
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean hasBeenPassed() {
        return passed;
    }

}
