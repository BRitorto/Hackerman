package com.poo.hackerman.model.entity.staticEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;

/**
 * An entity that can´t move.
 */
public abstract class StaticEntity extends Entity {

    protected StaticEntity(Position position, Direction direction) {
        super(position, direction);
    }

}
