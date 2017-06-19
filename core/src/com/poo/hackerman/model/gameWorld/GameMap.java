package com.poo.hackerman.model.gameWorld;

import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Entity;

import java.util.List;

public class GameMap {

    public static final int WIDTH = 736;
    public static final int HEIGHT = (WIDTH * 3) / 4;
    public static int CELL_SIZE = 32;

    private Grid grid;

    private EntityManager entityManager;

    public GameMap(EntityManager entityManager) throws OccupiedCellException{
        this.entityManager = entityManager;
        List<Entity> entities = entityManager.getEntities();
        grid = new Grid();
        grid.add(entities);                                     //puede tirar OccupedCellException
        entityManager.setGrid(grid);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Grid getGrid() {
        return grid;
    }

}
