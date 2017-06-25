package com.poo.hackerman.model.gameWorld;

import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;

import java.util.Collection;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Grid {

    private Cell[][] matrix;
    private int rows, cols;

    /**
     * Creates the game's matrix
     */

    public Grid() {
        this.rows = GameMap.HEIGHT / GameMap.CELL_SIZE;
        this.cols = GameMap.WIDTH / GameMap.CELL_SIZE;
        matrix = new Cell[cols][rows];
        initializeMatrix();
    }

    /**
     * @param destination
     * @param entity
     * Adds the entity to its destination n the matrix
     */

    public void add(Entity entity, Position destination) throws OccupiedCellException {
        int i = destination.getX() / GameMap.CELL_SIZE;            //para entidades que estan moviendose a esta posicion
        int j = destination.getY() / GameMap.CELL_SIZE;
        matrix[i][j].add(entity);               //puede tirar exception
    }

    public void add(Entity entity) throws OccupiedCellException {
        int i = entity.getPosition().getX() / GameMap.CELL_SIZE;
        int j = entity.getPosition().getY() / GameMap.CELL_SIZE;
        matrix[i][j].add(entity);               //puede tirar exception
    }

    public void add(Collection<? extends Entity> entities) throws OccupiedCellException {       //chequear el comodin
        for(Entity entity: entities) {
            add(entity);
        }
    }

    /**
     * @param position
     * @return Cell
     * Recieves a position and returns its corresponding position on the grid
     */

    public Cell getCell(Position position) {
        if(position.withinBoundaries()) {
            Position positionGrid = position.toGridIndexes();
            return matrix[positionGrid.getX()][positionGrid.getY()];
        }
        return null;
    }

    /**
     * @param position
     * Frees the position on the matrix
     */

    public void freePosition(Position position) {
        if(position.withinBoundaries()) {
            Position positionGrid = position.toGridIndexes();
            matrix[positionGrid.getX()][positionGrid.getY()].free();
        }
    }

    /**
     * @param position
     * @return booleam
     * Checks if an entity can be added to the specified position (checks if it is not yet occupied)
     */

    public boolean isPossibleAdd(Position position) {
        if(position.withinBoundaries()) {
            Position positionGrid = position.toGridIndexes();
            return matrix[positionGrid.getX()][positionGrid.getY()].isEmpty();
        }
        return false;
    }

    private void initializeMatrix() {
        for(int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }
}
