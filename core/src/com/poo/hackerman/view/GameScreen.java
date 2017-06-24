package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.CameraGuard;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import com.poo.hackerman.model.gameWorld.GameMap;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameScreen extends ScreenAdapter {

    private static final float WORLD_WIDTH = 23*GameMap.CELL_SIZE;
    private static final float WORLD_HEIGHT = 17*GameMap.CELL_SIZE;

    private Viewport viewport;
    private OrthographicCamera camera;
    private EntityManager entityManager;
    private SpriteBatch batch;
    private List<EnemyCharacter> enemiesO;

    private UIEntity hacker;
    private UIStaticEntity door;
    private UIEntity[] enemies;
    private UIStaticEntity[] computers, obstacles, hearts, cameras;
    private List<CameraGuard> camerasO;
    private List<Computer> computersO;
    private Texture doorT, computersT, computerHackedT, wallT, deskT, fakeCompT, heartT;
    private Texture hackerT, guardT, cameraT;
    private Texture background;
    private HackerGame game;
    private ShapeRenderer shapeRenderer;
    //private Music music = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));


    public GameScreen(HackerGame game) {
        //music.play();
        this.game = game;
        batch = game.getBatch();
    }
    @Override
    public void show() {
        super.show();
        shapeRenderer = new ShapeRenderer();
        entityManager = game.getModelManager().getEntityManager();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        PlayerCharacter player = entityManager.getPlayer();
        Door doorO = entityManager.getDoor();
        enemiesO = entityManager.getEnemies();
        //cameras = new UIStaticEntity[enemiesO.size()];
        camerasO = new ArrayList<CameraGuard>();
        for(EnemyCharacter enemyCharacter: enemiesO) {
            if(enemyCharacter instanceof CameraGuard) {
                camerasO.add((CameraGuard)enemyCharacter);
                enemiesO.remove(enemyCharacter);
            }
        }
        computersO = entityManager.getComputers();
        List<Obstacle> obstaclesO = entityManager.getObstacles();


        hackerT = new Texture("hacker.png");
        guardT = new Texture("guard.png");
        cameraT = new Texture("cameraT.png");
        doorT = new Texture("door.png");
        computersT = new Texture("computer2.png");
        computerHackedT = new Texture("computer2Hacked.png");
        fakeCompT = new Texture("fakeCompT.png");
        deskT = new Texture("desk.png");
        wallT = new Texture("wall2.png");
        background = new Texture("bg.png");
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
        //music.dispose();
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
        cameras = new UIStaticEntity[camerasO.size()];
        for(int i = 0; i < enemiesO.size(); i++) {
            if(enemiesO.get(i) instanceof Guard) {
                enemies[i] = new UIEntity(guardT, enemiesO.get(i));
            }
        }
        for (int i= 0; i < camerasO.size(); i++){
            cameras[i] = new UIStaticEntity(cameraT, camerasO.get(i));
            cameras[i].setPosition(camerasO.get(i).getPosition().getX(),camerasO.get(i).getPosition().getY());
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
            computers[i].rotate(computersO.get(i).getDirection());
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
        drawBackground();
        drawObstacles();
        drawCameras();
        drawComputers();
        drawLives();
        drawEnemies();
        door.draw(batch);
        hacker.draw(batch);
        //drawGrid();
        batch.end();
        drawLights();
    }

    private void drawCameras() {
        for (UIStaticEntity cameraGuard : cameras) {
            if(cameraGuard!=null) {
                cameraGuard.draw(batch);
            }
        }
    }

    private void drawBackground() {
        for(int i = 0; i <= GameMap.WIDTH/ background.getWidth(); i++) {
            for(int j = 0 ; j <= GameMap.HEIGHT/ background.getHeight() ; j++) {
                batch.draw(background, i* background.getWidth(), j*background.getHeight());
            }
        }
    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < Gdx.graphics.getWidth(); x += GameMap.CELL_SIZE) {
            for (int y = 0; y < Gdx.graphics.getHeight(); y += GameMap.CELL_SIZE) {
                shapeRenderer.rect(x,y, GameMap.CELL_SIZE, GameMap.CELL_SIZE);
            }
        }
        shapeRenderer.end();
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
            else if(!computersO.get(i).isOn()) {
                computers[i].setTexture(fakeCompT);
            }
            else {
                computers[i].setTexture(computersT);
            }
            //computers[i].rotate(computersO.get(i).getDirection());
            computers[i].draw(batch);
        }
    }

    private void drawEnemies() {
        for(UIEntity enemy : enemies) {
            if(enemy!=null){
                enemy.draw(batch);
            }
        }
    }

    private void drawLights() {

        for(int i = 0; i < enemies.length; i++) {
            if(enemies[i]!=null){
                drawLight(enemies[i], enemiesO.get(i).getMylight().getRange());
            }
        }

        for (int i = 0; i < cameras.length; i++) {
            if(cameras[i]!=null) {
                drawLight(cameras[i],camerasO.get(i).getMylight().getRange());
            }
        }
    }

    private void drawLight(UIStaticEntity mycamera, float range) {
        camera.update();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        int[] dir = mycamera.getDirection().getDir();
        float x1 = mycamera.getX() + cameraT.getWidth()/2;
        float y1 = mycamera.getY() + cameraT.getHeight()/2;
        int start = 45 - mycamera.getDirection().getCode() * 45;

        shapeRenderer.setColor(new Color(Color.RED.r, Color.RED.g, Color.RED.b, 0.5f));
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.arc(x1,y1,range,start,90);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawLight(UIEntity enemy, float range) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        int[] dir = enemy.getDirection().getDir();
        float x1 = enemy.getX() + (dir[0]==-1?-1:1) * cameraT.getWidth()/2;
        float y1 = enemy.getY() + cameraT.getHeight()/2;
        int start = 45 - enemy.getDirection().getCode()*45;
        shapeRenderer.setColor(new Color(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b, 0.5f));
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.arc(x1,y1,range,start,90);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

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