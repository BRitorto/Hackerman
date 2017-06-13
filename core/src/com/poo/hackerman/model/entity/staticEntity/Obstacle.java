package com.poo.hackerman.model.entity.staticEntity;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;

/**
 *
 */
public class Obstacle extends StaticEntity {

    public enum obstacleType {DESK, WALL, FAKECOMPUTER};

    private obstacleType type;

    public Obstacle(Position position, Direction direction, obstacleType type) {
        super(position,direction);
        this.type = type;
    }

    public obstacleType getObstacleType() {
        return type;
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

}
