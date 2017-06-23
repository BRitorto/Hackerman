package com.poo.hackerman.model.entity.dynamicEntity.character;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.InteractiveStaticEntity;
import com.poo.hackerman.model.gameWorld.GameMap;

/**
 *
 */
public class PlayerCharacter extends GameCharacter {

    private boolean interacting;

    public PlayerCharacter(Position position, Direction direction, int velocity) {
        super(position, direction, velocity);
        interacting = false;
    }

    public void tick() {
        if(interacting) {
            interact();
        }
        move();
        updateStatus();
        interacting = false;
    }

    public void setInteracting() {
        interacting = true;
    }

    public void interact() {
        int[] dir = direction.getDir();

        Position objective = new Position(getPosition().getX() + dir[0] * GameMap.CELL_SIZE, getPosition().getY() + dir[1] * GameMap.CELL_SIZE);
        if(!objective.withinBoundaries()) {
            return;
        }
        if(grid.getCell(objective).getEntity() != null && (grid.getCell(objective).getEntity() instanceof InteractiveStaticEntity)) {
            InteractiveStaticEntity entity = (InteractiveStaticEntity) grid.getCell(objective).getEntity();
            entity.interact();
        }
    }
}
