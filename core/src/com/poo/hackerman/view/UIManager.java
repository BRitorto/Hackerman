package com.poo.hackerman.view;


import com.poo.hackerman.controller.Manager;
import com.poo.hackerman.controller.ModelManager;
import com.poo.hackerman.model.Managers.EntityManager;

/**
 * Created by Bianca on 07/06/2017.
 */
public class UIManager {

    private ModelManager modelManager;
    private EntityManager entityManager;
    private HackerGame game;

    public UIManager (ModelManager modelManager) {
        this.modelManager = modelManager;
        entityManager = modelManager.getEntityManager();
        game = new HackerGame(this, modelManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public HackerGame getGame() {
        return this.game;
    }

    public void setState (Manager.STATE state) {
        modelManager.getManager().stateManager(state);
    }




}
