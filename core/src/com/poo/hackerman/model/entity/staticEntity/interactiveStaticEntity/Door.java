package com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;

/**
 * Created by franciscosanguineti on 25/5/17.
 */
public class Door extends InteractiveStaticEntity{

    private boolean isOpen = false;
    private boolean passed = false;

    public Door(Position position, Direction direction) {
        super(position,direction);
    }

    public void setOpen() {
        isOpen = true;
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    public boolean hasBeenPassed() {
        return passed;
    }

    public void interact() {
        if(isOpen) {
            passed = true;
        }
    }





}
