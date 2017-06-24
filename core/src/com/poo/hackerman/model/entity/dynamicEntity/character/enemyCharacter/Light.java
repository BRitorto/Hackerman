package com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.GameCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.gameWorld.GameMap;
import com.poo.hackerman.model.gameWorld.Grid;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Light {

    private int range;
    private float uiRange;

    public Light(int range) {
        this.range = range;
        this.uiRange = range * GameMap.CELL_SIZE;
    }

    public float getRange() {
        return uiRange;
    }

    /**
     *
     * @param guardPosition
     * @param guardDirection
     * @param grid The entities game matrix
     * @return True if detects the player
     */
    public boolean collision(Position guardPosition, Direction guardDirection, Grid grid) {

        int[] guardDir = guardDirection.getDir(); //Me devuelve vector [-1 0 1, -1 0 1] dependiendo de a donde se dirija el guardia

        Position p1 = new Position(guardPosition.getX(), guardPosition.getY());
        Direction dirRight = guardDirection.getRight();
        Direction dirLeft = guardDirection.getLeft();

        for(int i = 0; i < range; i++) {
            if (!grid.isPossibleAdd(p1) && !(grid.getCell(p1) != null && grid.getCell(p1).getEntity() instanceof GameCharacter)) {
                return false;
            }
            boolean detected = checkDirection(p1, dirRight, range-i, grid) || checkDirection(p1, dirLeft, range-i, grid);
            if(detected) {
                return true;
            }
            p1.incrementPosition(guardDir[0] * GameMap.CELL_SIZE, guardDir[1] * GameMap.CELL_SIZE);
            if(grid.isPossibleAdd(p1) || (grid.getCell(p1) != null && grid.getCell(p1).getEntity() instanceof GameCharacter)) {
                uiRange = guardPosition.distanceOf(p1);
            }
        }
        return false;
    }

    private boolean checkDirection(Position currentPosition, Direction currentDirection, int currentRange, Grid grid) {
        int[] dir = currentDirection.getDir();

        Position posCopy = new Position(currentPosition.getX(), currentPosition.getY());

        for (int i = 0; i < currentRange; i++) {
            //devuelve true si encuentra al hacker, false si se va del mapa o si encuentra un obstaculo para la linterna
            if(!posCopy.withinBoundaries()) {
                return false;
            }
            if(checkPlayer(posCopy, grid)) {
                return true;
            }
            if(!grid.isPossibleAdd(posCopy) && !(grid.getCell(posCopy).getEntity() instanceof GameCharacter)) {
                return false;
            }
            posCopy.incrementPosition(dir[0] * GameMap.CELL_SIZE, dir[1] * GameMap.CELL_SIZE);
        }
        return false;
    }

    private boolean checkPlayer(Position position, Grid grid) {
        Entity entity = grid.getCell(position).getEntity();

        if(entity == null) {
            return false;
        }
        return entity.getClass().equals(PlayerCharacter.class);
    }

}
