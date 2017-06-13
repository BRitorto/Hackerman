package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poo.hackerman.controller.ModelManager;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import java.util.List;

public class GameScreen extends ScreenAdapter {

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    private Viewport viewport;
    private Camera camera;
    private EntityManager entityManager;
    private SpriteBatch batch;

    private UIEntity hacker;
    private Sprite door;
    private UIEntity[] enemies;
    private Sprite[] computers, obstacles;

    private Texture doorT, computersT, wallT, deskT, fakeCompT;
    private Texture hackerT, guardT;
    private Texture background;
    private HackerGame game;

    public GameScreen(HackerGame game) {
        this.game = game;
        batch = game.batch;
        entityManager = game.getUIManager().getEntityManager();
    }
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        PlayerCharacter player = entityManager.getPlayer();
        Door doorO = entityManager.getDoor();
        List<EnemyCharacter> enemiesO = entityManager.getEnemies();
        List<Computer> computersO = entityManager.getComputers();
        List<Obstacle> obstaclesO = entityManager.getObstacles();

        hackerT = new Texture("core/assets/hacker.png");
        guardT = new Texture("core/assets/guard.png");
        doorT = new Texture("core/assets/heart.png");
        computersT = new Texture("core/assets/computerSheet.png");
        fakeCompT = new Texture("core/assets/fakecomp.png");
        deskT = new Texture("core/assets/floor.png");
        wallT = new Texture("core/assets/floor.png");
        background = new Texture("core/assets/floor2.png");


        hacker = new UIEntity(hackerT, player);
        hacker.setPosition(player.getPosition().getX(),player.getPosition().getY());

        enemies = new UIEntity[enemiesO.size()];
        for(int i = 0; i < enemiesO.size() ; i++) {
            enemies[i] = new UIEntity(guardT, enemiesO.get(i));
            (enemies[i]).setPosition(enemiesO.get(i).getPosition().getX(), enemiesO.get(i).getPosition().getY());
        }

        door = new Sprite(doorT);
        computers = new Sprite[computersO.size()];
        obstacles = new Sprite[obstaclesO.size()];

        door.setX(doorO.getPosition().getX());
        door.setY(doorO.getPosition().getY());


        for(int i = 0; i < computersO.size() ; i++) {
            computers[i] = new Sprite(computersT);
            (computers[i]).setX(computersO.get(i).getPosition().getX());
            (computers[i]).setY(computersO.get(i).getPosition().getY());
        }


        for(int i = 0; i < obstaclesO.size() ; i++) {
            if(obstaclesO.get(i).getObstacleType() == Obstacle.obstacleType.DESK) {
                obstacles[i] = new Sprite(deskT);
            }
            else if(obstaclesO.get(i).getObstacleType() == Obstacle.obstacleType.WALL) {
                obstacles[i] = new Sprite(wallT);
            }
            else {
                obstacles[i] = new Sprite(fakeCompT);
            }
            (obstacles[i]).setX(obstaclesO.get(i).getPosition().getX());
            (obstacles[i]).setY(obstaclesO.get(i).getPosition().getY());
        }


    }



    public void resume() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render(float delta) {
       super.render(delta);
       game.getModelManager().queryInput();
        clearScreen();
        draw();
//        drawDebug();
    }

    private void draw() {
//        batch.totalRenderCalls = 0;
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        batch.draw(background, 0, 0);
        drawObstacles();
        drawEnemies();
        drawComputers();
        hacker.draw(batch);
        //drawScore();
        // drawLives();
        batch.end();
//        System.out.println(batch.totalRenderCalls);
    }

    //drawScore?

    private void drawObstacles() {
        for(Sprite s : obstacles) {
            s.draw(batch);
        }
    }

    private void drawComputers() {
        for(Sprite s : computers) {
            s.draw(batch);
        }
    }

    private void drawEnemies() {
        for(UIEntity enemy : enemies) {
            enemy.draw(batch);
        }
    }


    private void drawLives() {

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}