package com.poo.hackerman.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.poo.hackerman.view.UIManager;
import com.badlogic.gdx.Gdx;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by Hackerman
 */
public class Manager extends ApplicationAdapter {

    // Managers to control
    private ModelManager modelManager;
    private UIManager uiManager;

    public enum STATE {INITIALIZE, EXIT, EXIT_YES, PAUSE, RESUME, GAME_OVER, WON}
    private STATE state = STATE.INITIALIZE;


    public Manager() {
        modelManager = new ModelManager(this);
        uiManager = new UIManager(modelManager);
    }

    public void stateManager (STATE state) {
        switch (state) {
            case INITIALIZE: {
                modelManager.initialize();
                System.out.println("creo la MenuScreen");
                uiManager.getGame().setMainMenuScreen(uiManager.getGame());
            } break;

            case EXIT: {
                uiManager.getGame().setExitScreen(uiManager.getGame());
            } break;

            case EXIT_YES: {
                Gdx.app.exit();
            }

            case PAUSE: {
                modelManager.getGameModel().setPause();
                uiManager.getGame().setPausedScreen(uiManager.getGame());
            }break;

            case RESUME: { //QUE PONGO ACA?
                modelManager.getGameModel().resume();
                uiManager.getGame().setScreen(uiManager.getGame().getGameScreen());
            }

            case GAME_OVER: {
                modelManager.getGameModel().setPause();
                uiManager.getGame().setGameOverScreen(uiManager.getGame());
            } break;

            case WON: {
                uiManager.getGame().setWonScreen(uiManager.getGame());
                Gdx.app.exit();
            }
        }
    }

    public void checkGameOver() {
        if (modelManager.getGameModel().gameOver()) {
            stateManager(STATE.GAME_OVER);
        }
    }

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
