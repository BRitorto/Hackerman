package com.poo.hackerman.model.gameWorld;

import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Entity;

import java.util.List;

public class GameMap {

    public static int CELL_SIZE = 64;
    public static final int WIDTH = 23*CELL_SIZE;
    public static final int HEIGHT = 17*CELL_SIZE;

    private Grid grid;

    private EntityManager entityManager;

    public GameMap(EntityManager entityManager) throws OccupiedCellException{
        this.entityManager = entityManager;
        List<Entity> entities = entityManager.getEntities();
        grid = new Grid();
        grid.add(entities);
        entityManager.setGrid(grid);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Grid getGrid() {
        return grid;
    }

}
