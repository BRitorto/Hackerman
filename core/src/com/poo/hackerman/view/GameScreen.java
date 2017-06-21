package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import com.poo.hackerman.model.gameWorld.GameMap;

import java.util.Collection;
import java.util.List;

public class GameScreen extends ScreenAdapter {

    public static final float WORLD_WIDTH = 23*32;
    public static final float WORLD_HEIGHT = 17*32;
    private Viewport viewport;
    private OrthographicCamera camera;
    private EntityManager entityManager;
    private SpriteBatch batch;

    private UIEntity hacker;
    private UIStaticEntity door;
    private UIEntity[] enemies;
    private UIStaticEntity[] computers, obstacles, hearts;
    private List<Computer> computersO;
    private Texture doorT, computersT, computerHackedT, wallT, deskT, fakeCompT, heartT;
    private Texture hackerT, guardT;
    private Texture background;
    private HackerGame game;

    public GameScreen(HackerGame game) {
        this.game = game;
        batch = game.getBatch();
    }
    @Override
    public void show() {
        entityManager = game.getModelManager().getEntityManager();
        super.show();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        PlayerCharacter player = entityManager.getPlayer();
        Door doorO = entityManager.getDoor();
        List<EnemyCharacter> enemiesO = entityManager.getEnemies();
        computersO = entityManager.getComputers();
        List<Obstacle> obstaclesO = entityManager.getObstacles();

        hackerT = new Texture("hacker.png");
        guardT = new Texture("guard.png");
        doorT = new Texture("door.png");
        computersT = new Texture("computersT.png");
        computerHackedT = new Texture("computersHacked.png");
        fakeCompT = new Texture("fakeCompT.png");
        deskT = new Texture("desk.png");
        wallT = new Texture("wall2.png");
        background = new Texture("wall.png");
        heartT = new Texture("heart.png");

        hacker = new UIEntity(hackerT, player);
        door = new UIStaticEntity(doorT);
        door.setPosition(doorO.getPosition().getX(), doorO.getPosition().getY());

        createEnemies(enemiesO);
        createObstacles(obstaclesO);
        createComputers(computersO);
        createLives();

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
        entityManager = game.getModelManager().getEntityManager();
        game.getModelManager().queryInput();
        clearScreen();
        draw();
    }

    private void createEnemies(List<EnemyCharacter> enemiesO) {
        enemies = new UIEntity[enemiesO.size()];
        for(int i = 0; i < enemiesO.size() ; i++) {
            enemies[i] = new UIEntity(guardT, enemiesO.get(i));
        }
    }

    private void createObstacles(List<Obstacle> obstaclesO) {
        obstacles = new UIStaticEntity[obstaclesO.size()];
        for(int i = 0; i < obstaclesO.size() ; i++) {
            if(obstaclesO.get(i).getObstacleType() == Obstacle.obstacleType.DESK) {
                obstacles[i] = new UIStaticEntity(deskT);
            }
            else if(obstaclesO.get(i).getObstacleType() == Obstacle.obstacleType.WALL) {
                obstacles[i] = new UIStaticEntity(wallT);
            }
            else {
                obstacles[i] = new UIStaticEntity(fakeCompT);
            }
            obstacles[i].setPosition(obstaclesO.get(i).getPosition().getX(),obstaclesO.get(i).getPosition().getY());
        }
    }

    private void createComputers(List<Computer> computersO) {
        computers = new UIStaticEntity[computersO.size()];
        for(int i = 0; i < computersO.size(); i++) {
            computers[i] = new UIStaticEntity(computersT);
            computers[i].setPosition(computersO.get(i).getPosition().getX(),computersO.get(i).getPosition().getY());
        }
    }

    private void createLives() {
        hearts = new UIStaticEntity[3];
        for(int i = 0; i < 3; i++) {
            hearts[i] = new UIStaticEntity(heartT);
            hearts[i].setPosition(GameMap.WIDTH - (i+1)*34,GameMap.HEIGHT - 64);
        }
    }

    private void draw() {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        //batch.draw(background, 0, 0);

        drawObstacles();
        drawEnemies();
        drawComputers();
        drawLives();
        hacker.draw(batch);
        batch.end();
    }

    private void drawObstacles() {
        for(UIStaticEntity s : obstacles) {
            s.draw(batch);
        }
    }

    private void drawComputers() {
        for(int i = 0; i< computers.length; i++) {
            if(computersO.get(i).isHacked()){
                computers[i].setTexture(computerHackedT);
            }
            computers[i].draw(batch);
        }
    }

    private void drawEnemies() {
        for(UIEntity enemy : enemies) {
            enemy.draw(batch);
        }
    }

    private void drawLives() {

        for(int i = 0; i < game.getModelManager().getGameModel().getLives(); i++) {
            hearts[i].draw(batch);
        }
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