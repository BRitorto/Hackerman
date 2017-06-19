package com.poo.hackerman.model.gameWorld;

import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.CameraGuard;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Created by Sebastian on 7/6/17.
 */
public class Level {

    private EntityManager entityManager;

    public Level(String filename){
        this.entityManager = setEntityManager(filename);
    }

    private EntityManager setEntityManager(String filename) { //el ultimo elemento de cada array es basura para ese elemento
        //ej: guards[guards.lenght-1] no es un guard
        String s = readFile(filename);
        String[] guards = s.split(" GUARDS\n");
        System.out.println(guards.length);
        String[] cameraguards = guards[guards.length - 1].split(" CAMERAGUARDS\n");
        String[] computers = cameraguards[cameraguards.length - 1].split(" COMPUTERS\n");
        String[] doors = computers[computers.length - 1].split(" DOORS\n");
        String[] desks = doors[doors.length - 1].split(" DESKS\n");
        String[] hackers = desks[desks.length - 1].split(" HACKER\n");
        String[] maps = hackers[hackers.length - 1].split(" MAP");
        String map = maps[0];
        String[] mapRows = map.split("/\n");
        LinkedList<Obstacle> obstacleList = new LinkedList<Obstacle>();
        LinkedList<Computer> computerList = new LinkedList<Computer>();
        LinkedList<EnemyCharacter> enemyList = new LinkedList<EnemyCharacter>();
        PlayerCharacter hacker = null;
        Door door = null;
        int guardIndex = 0, cameraIndex = 0, computerIndex = 0, doorIndex = 0, deskIndex = 0;
        int rowNumber = 0;
        for (String row : mapRows) {
            String[] cells = row.split(",");
            int cellNumber = 0;
            for (String cell : cells) {
                Position position = new Position(rowNumber * GameMap.CELL_SIZE, cellNumber * GameMap.CELL_SIZE);
                if(cell.equals("WALL")) {
                    Direction direction = new Direction(0);
                    obstacleList.add(new Obstacle(position, direction, Obstacle.obstacleType.WALL));
                }
                if(cell.equals("DOOR")) {
                    String[] properties = doors[doorIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    door = new Door(position, direction);
                }
                if(cell.equals("GUARD")) {
                    String[] properties = guards[guardIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int velocity = Integer.valueOf(properties[1]);
                    int range = Integer.valueOf(properties[2]);
                    if (properties.length > 3) {
                        ArrayList<Position> instructions = new ArrayList<Position>();
                        for (int i = 3; i < properties.length; i += 2) {
                            instructions.add(new Position(i, i + 1));
                        }
                        enemyList.add(new Guard(position, direction, velocity, range, instructions));
                    } else {
                        enemyList.add(new Guard(position, direction, velocity, range));
                    }
                }
                if(cell.equals("CAMERAGUARD")) {
                    String[] properties = cameraguards[cameraIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    Integer range = Integer.valueOf(properties[1]);
                    if (properties.length > 2) {
                        ArrayList<Direction> instructions = new ArrayList<Direction>();
                        for (int i = 2; i < properties.length; i++) {
                            instructions.add(new Direction(i));
                        }
                        enemyList.add(new CameraGuard(position, direction, range, instructions));
                    } else {
                        enemyList.add(new CameraGuard(position, direction, range));
                    }
                }
                if(cell.equals("PLAYER")) {
                    String[] properties = hackers[0].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    Integer velocity = Integer.valueOf(properties[1]);
                    hacker = new PlayerCharacter(position, direction, velocity);
                }
                if(cell.equals("COMPUTER")) {
                    String[] properties = computers[computerIndex].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int consecutiveHacks = Integer.valueOf(properties[1]);
                    computerList.add(new Computer(position, direction, consecutiveHacks));
                }
                if(cell.equals("WALL")) {
                    String[] properties = desks[deskIndex].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    obstacleList.add(new Obstacle(position, direction, Obstacle.obstacleType.DESK));
                }
                /*public enum obj {WALL,DOOR,GUARD,CAMERAGUARD,HACKER,COMPUTER,DESK}
                switch (cell){
                    case "WALL":
                        Direction direction = new Direction(0);
                        obstacleList.add(new Obstacle(position, direction, Obstacle.obstacleType.WALL));
                        break;
                    case "DOOR":
                        String[] properties = doors[doorIndex++].split(",");
                        direction = new Direction(Integer.valueOf(properties[0]));
                        door = new Door(position, direction);
                        break;
                    case "GUARD":
                        properties = guards[guardIndex++].split(",");
                        direction = new Direction(Integer.valueOf(properties[0]));
                        int velocity = Integer.valueOf(properties[1]);
                        int range = Integer.valueOf(properties[2]);
                        if(properties.length>3){
                            ArrayList<Position> instructions = new ArrayList<Position>();
                            for(int i=3;i<properties.length;i+=2){
                                instructions.add(new Position(i,i+1));
                            }
                            enemyList.add(new Guard(position, direction, velocity, range,instructions));
                        }
                        else {
                            enemyList.add(new Guard(position, direction, velocity, range));
                        }
                        break;
                    case "CAMERAGUARD":
                        properties = cameraguards[cameraIndex++].split(",");
                        direction = new Direction(Integer.valueOf(properties[0]));
                        range = Integer.valueOf(properties[1]);
                        if(properties.length>2){
                            ArrayList<Direction> instructions = new ArrayList<Direction>();
                            for(int i=2;i<properties.length;i++){
                                instructions.add(new Direction(i));
                            }
                            enemyList.add(new CameraGuard(position, direction, range,instructions));
                        }
                        else {
                            enemyList.add(new CameraGuard(position, direction, range));
                        }
                        break;
                    case "HACKER":
                        properties = hackers[0].split(",");
                        direction = new Direction(Integer.valueOf(properties[0]));
                        velocity = Integer.valueOf(properties[1]);
                        hacker = new PlayerCharacter(position, direction, velocity);
                        break;
                    case "COMPUTER":
                        properties = computers[computerIndex].split(",");
                        direction = new Direction(Integer.valueOf(properties[0]));
                        int consecutiveHacks = Integer.valueOf(properties[1]);
                        computerList.add(new Computer(position, direction, consecutiveHacks));
                        break;
                    case "DESK":
                        properties = desks[deskIndex].split(",");
                        direction = new Direction(Integer.valueOf(properties[0]));
                        obstacleList.add(new Obstacle(position, direction, Obstacle.obstacleType.DESK));
                        break;*/
                cellNumber++;
            }
            rowNumber++;
        }
        return new EntityManager(hacker,door,enemyList,computerList,obstacleList);
    }

    private String readFile(String filename) {
        File f = new File(filename);
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            return new String(bytes,"UTF-8");

            /*String content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
            System.out.println(content);*/ //OPCION 2

            /*FileInputStream fisTargetFile = new FileInputStream(new File("test.txt"));
            String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");*/ //OPCION 3
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    /*
    private EntityManager setEntityManager(LinkedList<LinkedList<Entity>> level) {

        LinkedList<Obstacle> obstacles = new LinkedList<>();
        LinkedList<Computer> computers = new LinkedList<>();
        LinkedList<EnemyCharacter> enemies = new LinkedList<>();
        PlayerCharacter hacker = null;
        Door door = null;
        for(LinkedList<Entity> row:level){
            for(Entity entity:row){
                if(entity instanceof Computer)
                    computers.add((Computer)entity);
                else if(entity instanceof Obstacle)
                    obstacles.add((Obstacle)entity);
                else if(entity instanceof EnemyCharacter)
                    enemies.add((EnemyCharacter)entity);
                else if(entity instanceof Door)
                    door=(Door) entity;
                else if(entity instanceof PlayerCharacter)
                    hacker=(PlayerCharacter)entity;
            }
        }
        return new EntityManager(hacker,door,enemies,computers,obstacles);
    }

    public LinkedList<LinkedList<Entity>> getGrid() { return grid; }
    */

}