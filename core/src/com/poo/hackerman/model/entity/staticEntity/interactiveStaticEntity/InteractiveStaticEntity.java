package com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.staticEntity.StaticEntity;

/**
 * A static entity the player can interact with
 */
public abstract class InteractiveStaticEntity extends StaticEntity {

    protected InteractiveStaticEntity(Position position, Direction direction) {
        super(position, direction);
    }

    public abstract void interact();
}
