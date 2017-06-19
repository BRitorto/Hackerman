package com.poo.hackerman.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g3d.Model;
import com.poo.hackerman.view.HackerGame;
import com.badlogic.gdx.Gdx;

/**
 * Created by Hackerman
 */
public class Manager {

    // Managers to control
    private ModelManager modelManager;
    private HackerGame game;

    public enum STATE {CREATED, INITIALIZE, EXIT, EXIT_YES, PAUSE, RESUME, GAME_OVER, WON}
    private STATE state = STATE.CREATED;


    public Manager(HackerGame game) {
        modelManager = new ModelManager(this);
        this.game = game;
        //stateManager(state);
    }

    public void stateManager (STATE state) {
        switch (state) {
            case CREATED: {

            } break;

            case INITIALIZE: {
                System.out.print(state);
                modelManager.initialize();
                game.createGameScreen(game);
                game.setScreen(game.getGameScreen());
            } break;

            case EXIT: {
                game.setScreen(game.getExitScreen());
            } break;

            case EXIT_YES: {
                Gdx.app.exit();
            }

            case PAUSE: {
                modelManager.getGameModel().setPause();
                game.setScreen(game.getPausedScreen());
            }break;

            case RESUME: {
                modelManager.getGameModel().resume();
                game.setScreen(game.getGameScreen());
            }

            case GAME_OVER: {
                modelManager.getGameModel().setPause();
                game.setScreen(game.getGameOverScreen());
            } break;

            case WON: {
                game.setScreen(game.getWonScreen());
                Gdx.app.exit();
            }
        }
    }

    public void checkGameOver() {
        if (modelManager.getGameModel().gameOver()) {
            stateManager(STATE.GAME_OVER);
        }
    }

    public ModelManager getModelManager() { return this.modelManager; }

        /**
         * Sets the current entityManager to the one specified. This method should be
         * called if and only if the game is to be loaded.
         *
         * @param entityManager The loaded entityManager.
         */

        /**
         * Start the game flow
         * @param args default params
         */
        /*public static void main(String[] args) {
            new Manager();
        }*/

}
