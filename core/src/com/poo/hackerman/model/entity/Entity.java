package com.poo.hackerman.model.entity;

import java.io.Serializable;

/**
 * Entities are objects in the game. They occupy a position on the grid
 */

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int IDLE = 0;
    protected static final boolean PASSABLE = true;

    protected Position position;
    protected Direction direction;

    protected Entity(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public boolean equals(Object object){
        if(object==null || getClass().equals(object.getClass())) {
            return false;
        }
        Entity aux = (Entity) object;
        return this.getPosition().equals(aux.getPosition());
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public abstract boolean isPassable();


}
