package com.poo.hackerman.model.entity.dynamicEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.gameWorld.Grid;

/**
 * An entity that can move in any way (i.e. rotate, walk)
 */
public abstract class DynamicEntity extends Entity {

    private static final long serialVersionUID = 1L;

    public int state = IDLE;
    public static final int MOVING = 1;
    public static final int ROTATING = 2;

    private int velocity;
    protected Grid grid;

    protected DynamicEntity(Position position, Direction direction, int velocity) {
        super(position, direction);
        this.velocity = velocity;
    }
    
    public int getState() {
        return state;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    protected void rotate(Direction direction) {
        super.setDirection(direction);
    }

    public abstract void tick();

    public abstract void tryToMove(Direction direction);

    protected abstract void move();

}
